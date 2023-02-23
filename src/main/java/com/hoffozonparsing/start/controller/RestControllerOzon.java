package com.hoffozonparsing.start.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.hoffozonparsing.start.model.AnrexProduct;
import com.hoffozonparsing.start.model.OzonProduct;
import com.hoffozonparsing.start.service.OzonService;

@Controller
public class RestControllerOzon {

	final static private String URL_OZON = "https://api-seller.ozon.ru/v2/product/info";
	final static private String URL_OZON_ALL_PRODUCT = "https://api-seller.ozon.ru/v2/product/info/list";
	@Autowired
	OzonService ozonService;

//	@Autowired
//	AnrexService anrexService;

	@GetMapping("/info")
	public String getOzonProduct(Model model) throws URISyntaxException, IOException, InterruptedException {

		OzonProduct ozonProduct = new OzonProduct();
		List<String> anrexIds = new ArrayList<>();
		List<AnrexProduct> anrexProducts = getAnrexProductFromXml();
		
		for (AnrexProduct item : anrexProducts) {
			if (item.getIdAnrex()!=null) {
			anrexIds.add(item.getIdAnrex());
			}
			
		}
		
		RestTemplate restTemplate = new RestTemplate();		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		httpHeaders.add("Client-Id", "14933");
		httpHeaders.add("Api-Key", "4c76975f-9f02-4c80-b6f3-aa2801438f23");
		
		Map<String, Object> map = new HashMap<>();
		map.put("offer_id", anrexIds);
//		map.put("offer_id", "659606");
		
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(map, httpHeaders);

		MultiValueMap<String, String> map2 = new LinkedMultiValueMap<String, String>();
		map2.put("offer_id", anrexIds);

		HttpEntity<Object> httpEntity2 = new HttpEntity<Object>(map2, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI.create(URL_OZON_ALL_PRODUCT), httpEntity2, String.class);
		
		
		
		
		System.out.println("response status = " + responseEntity.getStatusCode());
		System.out.println("response body = " + responseEntity.getBody());

		
		
		
		
		
		Map<OzonProduct, AnrexProduct> mapAO = new HashMap<>();
		Map<String, OzonProduct> ozonProductsMap = ozonService.getAllOzonProducts(responseEntity.getBody(),anrexProducts);
		
		for (AnrexProduct item : anrexProducts) {
			if (ozonProductsMap.containsKey(item.getIdAnrex())) {
				mapAO.put(ozonProductsMap.get(item.getIdAnrex()), item);
			}
		}
		
		model.addAttribute("map", mapAO);

		return "test-page";
	}

	
	
	public List<AnrexProduct> getAnrexProductFromXml() {
		String fileName = "static/xml/export.xml";
		List<AnrexProduct> anrexProducts = new ArrayList<>();
		AnrexProduct anrexProduct = null;

		try {
			URL url = new URL("https://anrex.info/bitrix/catalog_export/export_J4G.xml");
			Resource resource = new ClassPathResource(fileName);
			InputStream inputStream = url.openStream();

			File file = new File(resource.getURI());
			Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

			XMLInputFactory factory = XMLInputFactory.newInstance();

			factory.setProperty(XMLInputFactory.IS_COALESCING, true);
//			factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file.getPath()));

			while (eventReader.hasNext()) {
				XMLEvent nextEvent = eventReader.nextEvent();
				if (nextEvent.isStartElement()) {
					StartElement startElement = nextEvent.asStartElement();
					switch (startElement.getName().getLocalPart()) {

					case "offer":
						anrexProduct = new AnrexProduct();
						break;
					case "model":

						nextEvent = eventReader.nextEvent();
						anrexProduct.setModel(nextEvent.asCharacters().getData());
						break;

					case "price":
						nextEvent = eventReader.nextEvent();
						anrexProduct.setPrice(nextEvent.asCharacters().getData());
						break;
					case "oldprice":
						nextEvent = eventReader.nextEvent();
						anrexProduct.setOldPrice(nextEvent.asCharacters().getData());
						break;

					case "vendorCode":
						nextEvent = eventReader.nextEvent();
						anrexProduct.setIdAnrex(nextEvent.asCharacters().getData());
						break;

					default:
						break;
					}
				}
				if (nextEvent.isEndElement()) {
					EndElement endElement = nextEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("offer")) {
						anrexProducts.add(anrexProduct);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return anrexProducts;
	}

}

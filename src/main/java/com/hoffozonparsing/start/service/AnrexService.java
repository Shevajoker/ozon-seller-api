package com.hoffozonparsing.start.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Service;

import com.hoffozonparsing.start.dao.AnrexDao;
import com.hoffozonparsing.start.model.AnrexProduct;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnrexService {

	@Autowired
	AnrexDao anrexDao;

	public List<AnrexProduct> getAnrexProductFromXml() {
		String fileName = "static/xml/export.xml";
		List<AnrexProduct> anrexProducts = new ArrayList<>();
		AnrexProduct anrexProduct = null;

		try {
			
			File file = new File(new ClassPathResource("static/xml/").getURL().getPath() + "export.xml");
			file.createNewFile();
			
			URL url = new URL("https://anrex.info/bitrix/catalog_export/export_J4G.xml");
			Resource resource = new ClassPathResource(fileName);
			log.info("--RESOURSE--" + resource.toString());
			InputStream inputStream = url.openStream();
//			log.info("--RESOURS-GET-URL-TO-STRING--" + resource.getURL().toString());
//			log.info("--RESOURS-resource.getURL().getPath()--" + resource.getURL().getPath());
			
			log.info("new ClassPathResource(\"static/xml/\").getPath() --- " + new ClassPathResource("static/xml/").getPath());
			
			
file = new File(resource.getURI());
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
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return anrexProducts;
	}

}

package com.hoffozonparsing.start.controller;


import java.net.URI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hoffozonparsing.start.service.AnrexService;
import com.hoffozonparsing.start.service.OzonService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RestControllerOzon {

	final static private String URL_OZON = "https://api-seller.ozon.ru/v2/product/info";
	final static private String URL_OZON_ALL_PRODUCT = "https://api-seller.ozon.ru/v2/product/info/list";
	@Autowired
	OzonService ozonService;

	@Autowired
	AnrexService anrexService;

	@GetMapping("/info")
	public String getOzonProduct(Model model) {

		List<String> anrexIds = new ArrayList<>();
		List<AnrexProduct> anrexProducts = anrexService.getAnrexProductFromXml();
		
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

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.put("offer_id", anrexIds);

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(map, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI.create(URL_OZON_ALL_PRODUCT), httpEntity, String.class);	
		
		Map<OzonProduct, AnrexProduct> mapAO = new HashMap<>();
		Map<String, OzonProduct> ozonProductsMap = ozonService.getAllOzonProducts(responseEntity.getBody(), anrexProducts);
		
		for (AnrexProduct item : anrexProducts) {
			if (ozonProductsMap.containsKey(item.getIdAnrex())) {
				mapAO.put(ozonProductsMap.get(item.getIdAnrex()), item);
			}
		}
		log.info("--Map<OzonProduct, AnrexProduct>--" + mapAO.toString());
		model.addAttribute("map", mapAO);

		return "test-page";
	}

	
	
	

}

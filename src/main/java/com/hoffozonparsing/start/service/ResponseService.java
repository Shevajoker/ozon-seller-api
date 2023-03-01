package com.hoffozonparsing.start.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hoffozonparsing.start.model.AnrexProduct;
import com.hoffozonparsing.start.model.OzonProduct;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ResponseService {

	final static private String CLIENT_ID = "14933";
	final static private String API_KEY = "4c76975f-9f02-4c80-b6f3-aa2801438f23";
	static RestTemplate rest = new RestTemplate();

	public static ResponseEntity<String> getResponseOzon(String url, String idAnrex) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("Client-Id", CLIENT_ID);
		headers.add("Api-Key", API_KEY);

		Map<String, Object> map = new HashMap<>();
		map.put("offer_id", "");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = null;
		try {
			response = rest.postForEntity(url, entity, String.class);
		} catch (Exception e) {

		}

		return response;
	}

	public static ResponseEntity<String> getResponseAllOzonProducts(String url, List<AnrexProduct> anrexProducts) {

		List<String> anrexIds = new ArrayList<>();

		for (AnrexProduct item : anrexProducts) {
			if (item.getIdAnrex() != null) {
				anrexIds.add("\"" + item.getIdAnrex() + "\"");
			}

		}
		String data = anrexIds.toString();
		log.info("anrexIds - " + data);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("Client-Id", CLIENT_ID);
		headers.add("Api-Key", API_KEY);

		Map<String, Object> map = new HashMap<>();
		map.put("offer_id", data);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		log.info("Map Entity - " + entity);
		ResponseEntity<String> response = null;
		try {
			response = rest.postForObject(url, entity, null, map);
		} catch (Exception e) {

		}
		log.info("RESPONSE-RETURN---- " + response);
		return response;
	}

	@Deprecated
	public static ResponseEntity<String> getResponseAnrex(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		return response;
	}

}

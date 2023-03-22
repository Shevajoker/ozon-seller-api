package com.hoffozonparsing.start.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoffozonparsing.start.model.AnrexProduct;
import com.hoffozonparsing.start.model.OzonProduct;
import com.hoffozonparsing.start.repository.OzonRepository;

@Service
public class OzonService {

	@Autowired
	private OzonRepository ozonDao;

	public OzonProduct getOzonProduct(String responseBody, String idAnrex) {

		OzonProduct ozonProduct = null;

		try {
			Object object = new JSONParser().parse(responseBody);

			JSONObject jsonObject = (JSONObject) object;
			jsonObject = (JSONObject) jsonObject.get("result");

			if (ozonProduct == null) {
				ozonProduct = new OzonProduct();
			}

			ozonProduct.setName((String) jsonObject.get("name"));
			ozonProduct.setIdAnrex((String) jsonObject.get("offer_id"));
			ozonProduct.setMarketingPrice((String) jsonObject.get("marketing_price"));
			ozonProduct.setMinOzonPrice((String) jsonObject.get("min_ozon_price"));
			ozonProduct.setOldPrice((String) jsonObject.get("old_price"));
			ozonProduct.setPremiumPrice((String) jsonObject.get("premium_price"));
			ozonProduct.setPrice((String) jsonObject.get("price"));
			ozonProduct.setRecommendedPrice((String) jsonObject.get("recommended_price"));
			ozonProduct.setMinPrice((String) jsonObject.get("min_price"));

		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}

		return ozonProduct;
	}

	public Map<String, OzonProduct> getAllOzonProducts(String entity, List<AnrexProduct> anrexProducts) {
		OzonProduct ozonProduct;
		Map<String, OzonProduct> ozonProductsMap = new HashMap<>();
		
		try {

			Object object = new JSONParser().parse(entity);

			JSONObject jsonObject0 = (JSONObject) object;
			jsonObject0 = (JSONObject) jsonObject0.get("result");
			JSONArray jsonArray = (JSONArray) jsonObject0.get("items");

			for (Object obj : jsonArray) {
				ozonProduct = new OzonProduct();
				JSONObject jsonObject = (JSONObject) obj;
				ozonProduct.setName((String) jsonObject.get("name"));
				ozonProduct.setIdAnrex((String) jsonObject.get("offer_id"));
				ozonProduct.setMarketingPrice((String) jsonObject.get("marketing_price"));
				ozonProduct.setMinOzonPrice((String) jsonObject.get("min_ozon_price"));
				ozonProduct.setOldPrice((String) jsonObject.get("old_price"));
				ozonProduct.setPremiumPrice((String) jsonObject.get("premium_price"));
				ozonProduct.setPrice((String) jsonObject.get("price"));
				ozonProduct.setRecommendedPrice((String) jsonObject.get("recommended_price"));
				ozonProduct.setMinPrice((String) jsonObject.get("min_price"));

				ozonProductsMap.put(ozonProduct.getIdAnrex(), ozonProduct);
			}

		} catch (ParseException e) {

		}

		return ozonProductsMap;
	}

}

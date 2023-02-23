package com.hoffozonparsing.start.parser;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HoffParser {

	public HoffParser() {

	}

	
	
	public String getContent(String url) {
		try {
			String uniqueSku = "80541765";
			
			String zipCode = "224001";
			Response response = Jsoup.connect(
					//"http://brickseek.com/walmart-inventory-checker/")
					"https://www.ozon.ru/product/zhurnalnyy-stol-anrex-zhurnalnyy-stolik-taurus-110h60h50-sm-485933373/?sh=zw8qzkS8jA")
	                .data("store_type","3", "sku", uniqueSku  , "zip" , String.valueOf(zipCode) , "sort" , "distance")
	                .userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.121 Safari/535.2")
	                .method(Method.GET)
	                .timeout(0).ignoreHttpErrors(true)
	                .execute();
			
			
			System.out.println(response);
			System.out.println("---------------------------");
			System.out.println(response.parse());
			System.out.println("---------------------------");
//			System.out.println(response.parse().select("h1"));
			
			
			
			
			
			
			
//			Document document = Jsoup.connect(
					
//					"https://hoff.ru")
//					.referrer("https://hoff.ru/search/page2/?fromSearch=direct&search=anrex")
//					//.cookie("__Secure-1PSIDCC", "AFvIBn8iNNNnao61XQx12-Hqo7KjjjOp2Y-HPg_BFjhu-rPLIwZhKsNMsr2IGCq6pmd2uG4JtmC9")
////					.data("query", "Java")
//					.userAgent("Mozilla/5.0")
//					.cookie("auth", "token")
//					.timeout(5000)
//					.get();
			
			
//			Element elem = document.body();
			
			
//			System.out.println(document.select("h1").first());
//			System.out.println("-----------------------------");
//			System.out.println(document.select("h1"));
//			System.out.println("-----------------------------");
//			System.out.println(elem);
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return "NAME-HOFF";
	}
	
	
	

}

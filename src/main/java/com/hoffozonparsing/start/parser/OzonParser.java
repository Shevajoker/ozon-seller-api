package com.hoffozonparsing.start.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class OzonParser {

	
	public OzonParser() {
	
	}
	
	
	
	public String getContent(String url) {
		try {
			Document document = Jsoup.connect(
					"https://www.ozon.ru/product/zhurnalnyy-stol-anrex-zhurnalnyy-stolik-taurus-110h60h50-sm-485933373/?sh=zw8qzkS8jA")
					.header("Host", "brickseek.com")
	                .header("Connection", "keep-alive")
//	              .header("Content-Length", ""+c.request().requestBody().length())
	                .header("Cache-Control", "max-age=0")
	                .header("Origin", "https://brickseek.com/")
	                .header("Upgrade-Insecure-Requests", "1")
	                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36")
	                .header("Content-Type", "application/x-www-form-urlencoded")
	                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
	                .referrer("http://brickseek.com/walmart-inventory-checker/")
	                .header("Accept-Encoding", "gzip, deflate, br")
	                .header("Accept-Language", "en-US,en;q=0.8")
//					.referrer("https://hoff.ru/search/page2/?fromSearch=direct&search=anrex")
//					.cookie("__Secure-1PSIDCC", "AFvIBn8iNNNnao61XQx12-Hqo7KjjjOp2Y-HPg_BFjhu-rPLIwZhKsNMsr2IGCq6pmd2uG4JtmC9")
//					.data("query", "Java")
//					.userAgent("Mozilla/5.0")
//					.cookie("auth", "token")
					.timeout(5000)
					.get();
			
			
			Element elem = document.body();
			
			
			System.out.println(document.select("h1").first());
			System.out.println("-----------------------------");
			System.out.println(elem);
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return "NAME-OZON";
	}
	
	
}

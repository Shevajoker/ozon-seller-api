package com.hoffozonparsing.start;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HoffOzonParsingApplication.class);
	}

	
//	 @Override
//	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//	        return builder.sources( HoffOzonParsingApplication.class);
//	    }
}

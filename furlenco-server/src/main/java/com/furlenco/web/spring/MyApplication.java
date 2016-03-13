package com.furlenco.web.spring;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.furlenco.service.impl.FurlencoResourceImpl;
import com.wordnik.swagger.jersey.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider;
import com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider;

public class MyApplication extends ResourceConfig{
	public MyApplication(){
		packages(true);
		register(FurlencoResourceImpl.class);
		register(RequestContextFilter.class);
		register(JacksonJaxbJsonProvider.class);
		register(MultiPartFeature.class);
		register(ApiListingResourceJSON.class);
		register(JerseyApiDeclarationProvider.class);
		register(JerseyResourceListingProvider.class);
		
	}
}


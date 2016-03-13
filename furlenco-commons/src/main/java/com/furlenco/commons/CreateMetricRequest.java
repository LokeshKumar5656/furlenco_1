package com.furlenco.commons;

import java.io.Serializable;
import java.util.List;

public class CreateMetricRequest implements Serializable{

	private static final long serialVersionUID = 2821054521194662509L;
	
	private List<SiteMetric> request;

	public List<SiteMetric> getRequest() {
		return request;
	}

	public void setRequest(List<SiteMetric> request) {
		this.request = request;
	}
	
}

package com.furlenco.commons;

import java.util.List;

public class AnalyticsSiteResponse {

	private List<EventMetrics> eventMetrics;
	private String info;

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<EventMetrics> getEventMetrics() {
		return eventMetrics;
	}
	public void setEventMetrics(List<EventMetrics> eventMetrics) {
		this.eventMetrics = eventMetrics;
	}
	
	
}

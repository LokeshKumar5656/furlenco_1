package com.furlenco.commons;

public class EventMetrics {

	private Long total;
	private String eventName;
	
	public EventMetrics(String eventName, Long total) {
		super();
		this.total = total;
		this.eventName = eventName;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}

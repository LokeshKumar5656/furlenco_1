package com.furlenco.commons;

import java.io.Serializable;


public class SiteMetric implements Serializable {

	private static final long serialVersionUID = 4559660892925694569L;
	private Integer id;
	private String login;
	private Integer adminId;
	private String metricName;
	private String metricValue;
	private Long actionTime;
	private String userAgent;
	private String latlng;
	private String city;
	private String state;
	private String country;
	private String language;
	
	

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getMetricValue() {
		return metricValue;
	}
	public void setMetricValue(String matricValue) {
		this.metricValue = matricValue;
	}
	public Long getActionTime() {
		return actionTime;
	}
	public void setActionTime(Long actionTime) {
		this.actionTime = actionTime;
	}
}

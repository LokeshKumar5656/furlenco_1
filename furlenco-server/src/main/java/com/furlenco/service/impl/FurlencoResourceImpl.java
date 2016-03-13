package com.furlenco.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furlenco.commons.AddressDetails;
import com.furlenco.commons.AnalyticsSiteResponse;
import com.furlenco.commons.CreateMetricRequest;
import com.furlenco.commons.EventMetrics;
import com.furlenco.commons.FurlencoResponse;
import com.furlenco.commons.MapResponse;
import com.furlenco.commons.SiteMetric;
import com.furlenco.commons.entity.AdminUser;
import com.furlenco.commons.entity.SiteUser;
import com.furlenco.core.repo.AdminUserRepo;
import com.furlenco.core.repo.SiteUserRepo;
import com.furlenco.service.FurlencoResource;
@Service
public class FurlencoResourceImpl implements FurlencoResource{

	@Autowired
	AdminUserRepo adminUserRepo;
	
	@Autowired
	SiteUserRepo siteUserRepo;
	
	// Test
	@Override
	public String getMessage() {
		return "Lokesh Kumar";
	}

	@Override
	public List<AdminUser> getAllAdminDetails() {
		List<AdminUser> allAdminUsers = adminUserRepo.findAll();
		if (null != allAdminUsers) {
			System.out.println("Users not null");
		}
		return allAdminUsers;
	}

	@Override
	public List<SiteMetric> getAllAnalyticsForSite(Integer siteId) {
		List<SiteUser> siteUserAnalytics = siteUserRepo.findByAdminId(siteId);
		List<SiteMetric> converSiteUserListToSiteUserRequestList = ConverSiteUserListToSiteUserRequestList(siteUserAnalytics);
		return converSiteUserListToSiteUserRequestList;
    }

	@Override
	public Response createMetric(CreateMetricRequest request,javax.ws.rs.core.HttpHeaders headers ) {
		
		String userAgent = headers.getRequestHeader("user-agent").get(0);
		System.out.println(userAgent);
		
		List<SiteMetric> siteUserRequestList = request.getRequest();
		
		for (SiteMetric siteUserr : siteUserRequestList) {
			SiteUser metric = new SiteUser();
			metric.setAdminId(siteUserr.getAdminId());
			metric.setLogin(siteUserr.getLogin());
			metric.setMetricName(siteUserr.getMetricName());
		/*	if (null != siteUserr.getLatlng()) {
				try {
					invokeGoogleApi(siteUserr.getLatlng());
				} catch (IOException e) {
					System.out.println("ERROR");
				}
			}*/
			metric.setMetricValue(siteUserr.getMetricValue());
			metric.setActionTime(new Timestamp(siteUserr.getActionTime()));
			metric.setUserAgent(userAgent);
			if (null != userAgent && userAgent.contains("Mobile")) {
				// updating default source to mobile based on the above condition
				metric.setSource("mobile");
			}
			siteUserRepo.save(metric);
		}
		FurlencoResponse response = new FurlencoResponse();
		response.setMessage("Successfully Added");
		return Response.ok(response).build();
	}

	private void invokeGoogleApi(String latlng) throws IOException {
		if (null != latlng) {
			String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="+latlng+"&sensor=true";
			CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	 
	        System.out.println("GET Response Status:: "
	                + httpResponse.getStatusLine().getStatusCode());
	 
	        BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
				        httpResponse.getEntity().getContent()));
			} catch (UnsupportedOperationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	        //AddressDetails details = new AddressDetails();
	        String mapResponse = response.toString();
	        ObjectMapper mapper = new ObjectMapper();
	        MapResponse obj = mapper.readValue(mapResponse, MapResponse.class);
	        httpClient.close();
		}
		
	}

	@Override
	public List<SiteMetric> getUserMetricsForSite(Integer siteId, String login) {
		List<SiteUser> userSpecificSiteMetrics = siteUserRepo.findByAdminIdAndLogin(siteId, login);
		List<SiteMetric> converSiteUserListToSiteUserRequestList = ConverSiteUserListToSiteUserRequestList(userSpecificSiteMetrics);
		return converSiteUserListToSiteUserRequestList;
	}

	private List<SiteMetric> ConverSiteUserListToSiteUserRequestList(List<SiteUser> userSpecificSiteMetricsList) {
		List<SiteMetric> siteUserRequestList = new ArrayList<SiteMetric>();
		
		for (SiteUser siteMetric : userSpecificSiteMetricsList) {
			SiteMetric request = new SiteMetric();
			
			request.setId(siteMetric.getId());
			request.setAdminId(siteMetric.getAdminId());
			request.setLogin(siteMetric.getLogin());
			request.setMetricName(siteMetric.getMetricName());
			request.setMetricValue(siteMetric.getMetricValue());
			request.setUserAgent(siteMetric.getUserAgent());
			if (null != siteMetric.getActionTime())
			request.setActionTime(siteMetric.getActionTime().getTime());
			
			if (null != siteMetric.getLanguage() && !siteMetric.getLanguage().isEmpty())
			 request.setLanguage(siteMetric.getLanguage());
			else 
				request.setLanguage("English");
			if (null != siteMetric.getLanguage() && !siteMetric.getLanguage().isEmpty())
			  request.setCountry(siteMetric.getCountry());
			else 
		      request.setCountry("India");
			if (null != siteMetric.getLanguage() && !siteMetric.getLanguage().isEmpty())
			  request.setState(siteMetric.getState());
			else
			  request.setState("Karnataka");
			if (null != siteMetric.getCity() && !siteMetric.getLanguage().isEmpty())	
			  request.setCity(siteMetric.getCity());
			else 
			  request.setCity("Bangalore");
			siteUserRequestList.add(request);
		}
		 
		return siteUserRequestList;
	}

	@Override
	public List<SiteMetric> getAnalyticsForSiteAndFilter(Integer siteId, Long timeFromCurrent, int days) {
		Long currentTime = System.currentTimeMillis();
		Long actualTime = currentTime - timeFromCurrent;
		
		// Default shud retrieve all if query param is not present
		if (timeFromCurrent == 0) {
			actualTime = 1000L;
		}
		// days property overrides timefromCurrent
		if (days != 0) {
			actualTime = currentTime - (86400 * days * 1000);
		}
		
		Date dateTime = new Date(actualTime);
		List<SiteUser> findAnalyticsForSiteFromSpecifiedTime = siteUserRepo.findAnalyticsForSiteFromSpecifiedTime(siteId, dateTime);
		
		List<SiteMetric> converSiteUserListToSiteUserRequestList = ConverSiteUserListToSiteUserRequestList(findAnalyticsForSiteFromSpecifiedTime);
		return converSiteUserListToSiteUserRequestList;
	}

	@Override
	public AnalyticsSiteResponse retriveAllAnalayticsForSite(Integer siteId,Long timeFromCurrent, int days) {
		Long currentTime = System.currentTimeMillis();
		Long actualTime = currentTime - timeFromCurrent;
		// Default shud retrieve all if query param is not present
		if (timeFromCurrent == 0) {
			actualTime = 1000L;
		}
		// days property overrides timefromCurrent
		if (days != 0) {
			actualTime = currentTime - (86400 * days * 1000);
		}
		
		Date dateTime = new Date(actualTime);
		
		List<EventMetrics> findBySiteIdGroupByEvent = siteUserRepo.findBySiteIdGroupByEvent(siteId);

		AnalyticsSiteResponse response = new AnalyticsSiteResponse();
		response.setEventMetrics(findBySiteIdGroupByEvent);
		List<SiteMetric> analyticsForSiteAndFilter = getAnalyticsForSiteAndFilter(siteId, 0L, 0);
		
		StringBuilder decision = new StringBuilder();
		String userAgent = null;
		int mobileCount = 0;
		int webCount = 0;
		for (SiteMetric report : analyticsForSiteAndFilter ) {
			 userAgent = report.getUserAgent();
			 if (null != userAgent && userAgent.contains("Mobile")) {
				 mobileCount++;
			 } else if (null != userAgent) {
				 webCount++;
			 }
		}
		if (webCount >= mobileCount) {
			decision.append("Your site traffic is leading from WEB ");
		} else {
			decision.append("Your site traffic is leading from MOBILE ");
		}
		
		response.setInfo(decision.toString());
		
		return response;
	}

	@Override
	public List<SiteMetric> retrieveAnalyticsByEventForSite(Integer siteId, String eventName) {
		List<SiteUser> metrics = siteUserRepo.findByAdminIdAndMetricName(siteId, eventName);
		List<SiteMetric> converSiteUserListToSiteUserRequestList = ConverSiteUserListToSiteUserRequestList(metrics);
		return converSiteUserListToSiteUserRequestList;
	}

	@Override
	public Response triggerEmailForOrder() {
		String url = "https://maker.ifttt.com/trigger/FCO_HACKATHON/with/key/eO_Y2v2K6jAiHNkx8qDYd4Jx1lgyjSVT-Kj9rC7aezO";
		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
            System.out.println("GET Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());
        httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FurlencoResponse response = new FurlencoResponse();
		response.setMessage("Successfully Sent");
		return Response.ok(response).build();
	}

	@Override
	public Integer retrieveAnalyticsRelatedBasedOnId(Integer siteId,String htmlId, Long timeFromCurrent, int days) {
		Long currentTime = System.currentTimeMillis();
		Long actualTime = currentTime - timeFromCurrent;
		// Default shud retrieve all if query param is not present
		if (timeFromCurrent == 0) {
			actualTime = 1000L;
		}
		// days property overrides timefromCurrent
		if (days != 0) {
			actualTime = currentTime - (86400 * days * 1000);
		}
		
		
		Date dateTime = new Date(actualTime);
		
		List<SiteUser> metrics = siteUserRepo.findByAdminIdAndMetricValue(siteId, htmlId, dateTime);
		
		if (null != metrics)
			return metrics.size();
		return 0;
	}

}

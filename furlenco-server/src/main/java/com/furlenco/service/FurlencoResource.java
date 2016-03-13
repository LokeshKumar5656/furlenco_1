package com.furlenco.service;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;

import com.furlenco.commons.AnalyticsSiteResponse;
import com.furlenco.commons.CreateMetricRequest;
import com.furlenco.commons.SiteMetric;
import com.furlenco.commons.entity.AdminUser;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface FurlencoResource {

	@GET
	@Path("/welcome")
    @Produces(MediaType.APPLICATION_JSON)
	String getMessage();
	
	@GET
	@Path("/allAdminUsers")
    @Produces(MediaType.APPLICATION_JSON)
	List<AdminUser> getAllAdminDetails();
	
	@GET
	@Path("/allAnalyticsForSite/{siteId}")
    @Produces(MediaType.APPLICATION_JSON)
	List<SiteMetric> getAllAnalyticsForSite(@PathParam("siteId") Integer siteId);
	
	@POST
	@Path("/addMetric")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response createMetric(@RequestBody CreateMetricRequest request, @Context HttpHeaders headers);
	
	@GET
	@Path("/userMetrics/{siteId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
	List<SiteMetric> getUserMetricsForSite(@PathParam("siteId") Integer siteId, @PathParam("userId") String login);
	
	@GET
	@Path("/allAnalyticsForSite/filter/{siteId}")
    @Produces(MediaType.APPLICATION_JSON)
	List<SiteMetric> getAnalyticsForSiteAndFilter(@PathParam("siteId") Integer siteId, @DefaultValue("0") @QueryParam("filterTime") Long timeFromCurrent, @DefaultValue("0") @QueryParam("filterDay") int days);
		
	@GET
	@Path("/retrieveTotalAnalytics/filter/{siteId}")
    @Produces(MediaType.APPLICATION_JSON)
	AnalyticsSiteResponse retriveAllAnalayticsForSite(@PathParam("siteId") Integer siteId, @DefaultValue("0") @QueryParam("filterTime") Long timeFromCurrent, @DefaultValue("0") @QueryParam("filterDay") int days);
	
	@GET
	@Path("/retrieveByEventForSite/{siteId}/{eventName}")
    @Produces(MediaType.APPLICATION_JSON)
	List<SiteMetric> retrieveAnalyticsByEventForSite(@PathParam("siteId") Integer siteId, @PathParam("eventName") String eventName);
	
	
	@GET
	@Path("/retrieveByClickedId/{siteId}/{htmlId}")
    @Produces(MediaType.APPLICATION_JSON)
	Integer retrieveAnalyticsRelatedBasedOnId(@PathParam("siteId") Integer siteId, @PathParam("htmlId") String htmlId, @DefaultValue("0") @QueryParam("filterTime") Long timeFromCurrent, @DefaultValue("0") @QueryParam("filterDay") int days);
	
	@GET
	@Path("/triggerEmail")
    @Produces(MediaType.APPLICATION_JSON)
	Response triggerEmailForOrder();
}

package com.furlenco.core.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.furlenco.commons.EventMetrics;
import com.furlenco.commons.entity.SiteUser;

public interface SiteUserRepo extends CrudRepository<SiteUser, Integer> {
	public List<SiteUser> findAll();
	public List<SiteUser> findByAdminId(Integer siteId);
	public List<SiteUser> findByAdminIdAndLogin(Integer siteId, String login);
	
    @Query(value = "select p from SiteUser p where p.actionTime > :timeFromCurrent and p.adminId = :siteId")
	public List<SiteUser> findAnalyticsForSiteFromSpecifiedTime(@Param("siteId") Integer siteId, @Param("timeFromCurrent") Date timeFromCurrent);
	
    public List<SiteUser> findByAdminIdAndMetricName(Integer siteId, String eventName);
	
    @Query(value = "select new com.furlenco.commons.EventMetrics(p.metricName, count(*))from SiteUser p where p.adminId = :siteId group by p.metricName")
    public List<EventMetrics> findBySiteIdGroupByEvent( @Param("siteId") Integer siteId);
	
    @Query(value = "select p from SiteUser p where p.actionTime > :timeFromCurrent and p.adminId = :siteId and p.metricValue = :htmlId")
    public List<SiteUser> findByAdminIdAndMetricValue(@Param("siteId") Integer siteId, @Param("htmlId") String htmlId, @Param("timeFromCurrent") Date timeFromCurrent);
}

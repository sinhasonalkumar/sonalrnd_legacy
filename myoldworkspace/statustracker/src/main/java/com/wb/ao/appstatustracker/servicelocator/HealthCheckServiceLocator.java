package com.wb.ao.appstatustracker.servicelocator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wb.ao.appstatustracker.services.iface.HealthStatusFinder;

@Component
public class HealthCheckServiceLocator {
	
	@Autowired
	@Qualifier("uvgsHealthStatusFinder")
	private HealthStatusFinder uvgsHealthStatusFinder;
	
	@Autowired
	@Qualifier("uvdcHealthStatusFinder")
	private HealthStatusFinder uvdcHealthStatusFinder;
	
	@Autowired
	@Qualifier("publisherHealthStatusFinder")
	private HealthStatusFinder publisherHealthStatusFinder;
	
	public HealthStatusFinder getUvgsHealthStatusFinder() {
		return uvgsHealthStatusFinder;
	}
	public void setUvgsHealthStatusFinder(HealthStatusFinder uvgsHealthStatusFinder) {
		this.uvgsHealthStatusFinder = uvgsHealthStatusFinder;
	}
	public HealthStatusFinder getUvdcHealthStatusFinder() {
		return uvdcHealthStatusFinder;
	}
	public void setUvdcHealthStatusFinder(HealthStatusFinder uvdcHealthStatusFinder) {
		this.uvdcHealthStatusFinder = uvdcHealthStatusFinder;
	}
	public HealthStatusFinder getPublisherHealthStatusFinder() {
		return publisherHealthStatusFinder;
	}
	public void setPublisherHealthStatusFinder(HealthStatusFinder publisherHealthStatusFinder) {
		this.publisherHealthStatusFinder = publisherHealthStatusFinder;
	}
	
	
}

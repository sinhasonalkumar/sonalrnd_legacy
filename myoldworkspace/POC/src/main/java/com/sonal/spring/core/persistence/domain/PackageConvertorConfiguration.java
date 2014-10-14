package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class PackageConvertorConfiguration {

	@Value("#{ dot['widevine.packaging.dot2.base.url'] }")
	private String dotBaseUrl;
	
	@Value("#{ dot['widevine.packaging.source.url'] }")
	private String sourceUrl;
	
	@Value("#{ dot['widevine.packaging.target.url'] }")
	private String targetUrl;	
	
	@Value("#{ dot['widevine.packaging.package.status.url'] }")
	private String statusTo;
	
	@Value("#{ dot['widevine.packaging.wvn'] }")
	private String widevine;
	
	@Value("#{ dot['widevine.packaging.request.success.message1'] }")
	private String successMessage1;
	
	@Value("#{ dot['widevine.packaging.request.success.message2'] }")
	private String successMessage2;
	
	@Value("#{ dot['packaging.mail.inprogress.message.prefix'] }")
	private String inprogressMsgPrefix;
	
	@Value("#{ dot['packaging.mail.inprogress.message.suffix'] }")
	private String inprogressMsgSuffix;

	@Value("#{ dot['widevine.packaging.mp4.fasttrickplay.value'] }")
	private String fasttrickplay;
	
	@Value("#{ dot['packaging.flash.service.enable'] }")
	private String flashServiceEnable;
}

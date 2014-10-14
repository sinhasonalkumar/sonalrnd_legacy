package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class FlashPackagerConfiguration {
	
	@Value("#{ dot['copy.unprotected.src.files'] }")
	private String fileLocationSource;
	
	@Value("#{ dot['app.server.unprotected.dest.path'] }")
	private String fileLocationDestination;
	
	@Value("#{ dot['csv.file.generation.type'] }")
	private String attchFileType;
	
	@Value("#{ dot['widevine.packaging.mail.success.subject'] }")
	private String successMailSubject;
	
	@Value("#{ dot['widevine.packaging.mail.success.message'] }")
	private String successMailMessage;
	
	@Value("#{ dot['widevine.packaging.mail.summary.message'] }")
	private String summaryMessage;
	
	@Value("#{ dot['flash.packaging.mail.success.subject'] }")
	private String flashPackageSuccessSub;
	
	@Value("#{ dot['flash.packaging.mail.success.message'] }")
	private String flashPackageSuccessMessage;
	
	@Value("#{ dot['widevine.packaging.mail.next.update.message'] }")
	private String nextUpdateMessage;
	
	
	@Value("#{ dot['adobe.pckgr.jar.name.and.loc'] }")
	private String adobePckgrJarNameAndLoc;
		
	@Value("#{ dot['adobe.pckgr.os'] }")
	private String adobePckgrOS;
	
	@Value("#{ dot['adobe.pckgr.jar.name.and.loc.removal.prefix'] }")
	private String adobePckgrJarNameAndLocRemovalPrefix;
	
	@Value("#{ dot['manifest.unprotected.media.url.live.folder'] }")
	private String manifestUnProtectedMediaUrlLiveFolder;
	
	@Value("#{ dot['manifest.file.name.prefix'] }")
	private String manifestFileNamePrefix;
	
	@Value("#{ dot['manifest.file.name.suffix'] }")
	private String manifestFileNameSuffix;
	
	@Value("#{ dot['optimization.ftp.host.name'] }")
	private String optimizationFtpServer;
	
	@Value("#{ dot['optimization.ftp.login'] }")
	private String optimizationFtpLogin;
	
	@Value("#{ dot['optimization.ftp.password'] }")
	private String optimizationFtpPassword;
	
	@Value("#{ dot['destpath.optimization.ftp'] }")
	private String destPathOptimizationFtp;
	
	@Value("#{ dot['media.url.live.folder'] }")
	private String faxsFilesUrlLocation;

}

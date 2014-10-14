package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class PackagerConfiguration {
	
	@Value("#{ dot['widevine.packaging.ftp.enable'] }")
	private String ftpEnable;
	
	@Value("#{ dot['widevine.packaging.ftp.server'] }")
	private String ftpServer;
	
	@Value("#{ dot['widevine.packaging.ftp.username'] }")
	private String ftpUsername;
	
	@Value("#{ dot['widevine.packaging.ftp.password'] }")
	private String ftpPassword;
	
	@Value("#{ dot['widevine.packaging.ftp.parent.directory'] }")
	private String ftpParentDirectory;	
	
	@Value("#{ dot['widevine.packaging.wvn'] }")
	private String widevine;
	
	@Value("#{ dot['widevine.packaging.mail.success.subject'] }")
	private String successMailSubject;
	
	@Value("#{ dot['widevine.packaging.mail.success.message'] }")
	private String successMailMessage;
	
	@Value("#{ dot['widevine.packaging.mail.fail.subject'] }")
	private String failMailSubject;	
		
	@Value("#{ dot['widevine.packaging.mail.fail.message'] }")
	private String failMailMessage;
	
	@Value("#{ dot['widevine.packaging.mail.summary.message'] }")
	private String summaryMessage;
	
	@Value("#{ dot['widevine.packaging.mail.package.notify.success.subject'] }")
	private String packageNotifySuccessSub;
	
	@Value("#{ dot['widevine.packaging.mail.package.notify.success.message'] }")
	private String packageNotifySuccessMessage;
	
	@Value("#{ dot['widevine.packaging.mail.next.update.message'] }")
	private String nextUpdateMessage;	
	
	@Value("#{ dot['widevine.packaging.mail.package.notify.fail.message'] }")
	private String packageNotifyFailMessage;
	
	@Value("#{ dot['csv.file.generation.type'] }")
	private String attchFileType;	
	
	@Value("#{ dot['packaging.register.content.service.enable'] }")
	private String registerContentEnable;	
	
	@Value("#{ dot['packaging.flash.service.enable'] }")
	private String flashServiceEnable;
	
	@Value("#{ dot['no.packaging.record.exist'] }")
	private String noPackagingRecordExist;
	
	@Value("#{ dot['bit.rate.profile1'] }")
	private String adaptiveBitRate;	
	
	@Value("#{ dot['packaging.mail.inprogress.subject'] }")
	private String inprogressMsgSub;
	
	@Value("#{ dot['packaging.mail.inprogress.message.prefix'] }")
	private String inprogressMsgPrefix;
	
	@Value("#{ dot['packaging.mail.inprogress.message.suffix'] }")
	private String inprogressMsgSuffix;
	
	@Value("#{ dot['widevine.packaging.stager.service.enable'] }")
	private String stagerServiceEnable;
	
	
	@Value("#{ dot['packaging.file.name.length'] }")
	private String packagingFileNameLength;
	
	@Value("#{ dot['ftp.cdn.pdl.server'] }")
	private String ftpCdnPdlServer;
	
	@Value("#{ dot['ftp.cdn.pdl.username'] }")
	private String ftpCdnPdlUsername;
	
	@Value("#{ dot['ftp.cdn.pdl.password'] }")
	private String ftpCdnPdlPassword;
	
	@Value("#{ dot['ftp.cdn.pdl.parent.dir'] }")
	private String ftpCdnPdlParentDir;
	
	@Value("#{ dot['ftp.cdn.level3.server'] }")
	private String ftpCdnLevel3Server;	
		
	@Value("#{ dot['ftp.cdn.level3.username'] }")
	private String ftpCdnLevel3Username;
	
	@Value("#{ dot['ftp.cdn.level3.password'] }")
	private String ftpCdnLevel3Password;
			
	@Value("#{ dot['ftp.cdn.level3.parent.dir'] }")
	private String ftpCdnLevel3ParentDir;
	
	//Package sleep time
	@Value("#{ dot['sleep.time.milli.sec'] }")	
	private String packageSleepTime;	
	
	//US
	@Value("#{ dot['us.generic.root.url'] }")	
	private String usPdlBaseUrl;	
	
	@Value("#{ dot['us.rtmpe.root.url'] }")	
	private String usRtmpeBaseUrl;
		
	//UK
	@Value("#{ dot['uk.generic.root.url'] }")	
	private String ukPdlBaseUrl;	
	
	@Value("#{ dot['uk.rtmpe.root.url'] }")	
	private String ukRtmpeBaseUrl;	

}

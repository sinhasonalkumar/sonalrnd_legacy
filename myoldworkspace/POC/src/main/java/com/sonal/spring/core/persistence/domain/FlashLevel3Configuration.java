package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class FlashLevel3Configuration {

	@Value("#{ dot['ftp.faxs.file.backup'] }")
	private String ftpFaxsFileBackup;

	@Value("#{ dot['ftp.os'] }")
	private String ftpOS;
	
	@Value("#{ dot['ftp.host.name'] }")
	private String ftpServer;
	
	@Value("#{ dot['ftp.login'] }")
	private String ftpUserName;
	
	@Value("#{ dot['ftp.password'] }")
	private String ftpPassword;
	
	@Value("#{ dot['dest.path.level3'] }")
	private String destPathLevel3;
		
}

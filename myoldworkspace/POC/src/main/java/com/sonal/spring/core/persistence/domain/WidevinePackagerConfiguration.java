package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class WidevinePackagerConfiguration {
	
	@Value("#{ dot['widevine.packaging.package.notify.url'] }")
	private String packageNotifyUrl;
	
	@Value("#{ dot['widevine.packaging.url'] }")
	private String url;
	
	@Value("#{ dot['widevine.packaging.wvn'] }")
	private String widevine;
	
	@Value("#{ dot['widevine.packaging.mail.package.notify.fail.message'] }")
	private String packageNotifyFailMessage;
	
	@Value("#{ dot['widevine.packaging.file.source.location'] }")
	private String fileSourceLocation;
	
	@Value("#{ dot['widevine.subtitles.file.extension'] }")
	private String widevineSubtitlesFileExtn;	
	
	@Value("#{ dot['widevine.packaging.subtitles.local.dest.path'] }")
	private String widevineSubtitlesDestPath;
	
	@Value("#{ dot['clas.packaging.adaptive.profile_id'] }")
	private String clasProfileId;
	
	@Value("#{ dot['widevine.subtitles.profile'] }")
	private String profile;
	
	@Value("#{ dot['widevine.subtitles.bitrate.profile'] }")
	private String bitrateProfile;
	
	@Value("#{ dot['widevine.subtitles.bitrate'] }")
	private String bitrate;	
	
	@Value("#{ dot['widevine.packaging.ftp.server'] }")
	private String ftpServer;
	
	@Value("#{ dot['widevine.packaging.ftp.username'] }")
	private String ftpUsername;
	
	@Value("#{ dot['widevine.packaging.ftp.password'] }")
	private String ftpPassword;
	
	@Value("#{ dot['widevine.packaging.ftp.parent.directory'] }")
	private String ftpParentDirectory;

}

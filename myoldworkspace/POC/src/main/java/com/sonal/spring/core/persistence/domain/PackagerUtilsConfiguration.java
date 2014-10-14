package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class PackagerUtilsConfiguration {
	
	@Value("#{ dot['mail.subject.exception.occurred'] }")
	private String exceptionSubject;
	
	@Value("#{ dot['widevine.packaging.file.source.location'] }")
	private String fileSourceLocation;
	
	@Value("#{ dot['widevine.packaging.xls.file.location'] }")
	private String xlsFileLocation;	
	
	@Value("#{ dot['widevine.packaging.xls.header.sno'] }")
	private String srNoHeader;
	
	@Value("#{ dot['widevine.packaging.xls.header.package.name'] }")
	private String packageNameHeader;

	@Value("#{ dot['widevine.packaging.xls.header.package.type'] }")
	private String packageTypeHeader;

	@Value("#{ dot['widevine.packaging.xls.header.media.url'] }")
	private String mediaUrlHeader;
	
	@Value("#{ dot['widevine.packaging.xls.header.status'] }")
	private String statusHeader;
	
	@Value("#{ dot['widevine.packaging.xls.header.error'] }")
	private String errorHeader;
	
	@Value("#{ dot['widevine.packaging.xls.header.assetId'] }")
	private String assetId;
	
	@Value("#{ dot['widevine.packaging.xls.header.register.contentId'] }")
	private String registerContentId;	
	
	@Value("#{ dot['widevine.packaging.xls.header.register.accessId'] }")
	private String registerAccessId;
	
	@Value("#{ dot['package.file.extension'] }")
	private String packageFileExtn;
	
	@Value("#{ dot['package.file.separator'] }")
	private String packageFileSeparator ;
	
	@Value("#{ dot['flash.subtitles.file.extension'] }")
	private String flashSubtitlesFileExtn;
	
	@Value("#{ dot['widevine.subtitles.file.extension'] }")
	private String widevineSubtitlesFileExtn;
	
	@Value("#{ dot['widevine.packaging.sfolder.destination.path'] }")
	private String sfolderDestPath;
}

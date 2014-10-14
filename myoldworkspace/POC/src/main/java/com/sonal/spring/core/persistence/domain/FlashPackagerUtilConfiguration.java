package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class FlashPackagerUtilConfiguration {
	
	@Value("#{ dot['dest.path.packger'] }")
	private String destPathPackger;

	@Value("#{ dot['dest.ftp.bkup.path'] }")
	private String destFTPBkupPath;

	@Value("#{ dot['app.server.unprotected.dest.path'] }")
	private String appServerUnprotectedDestPath;

	@Value("#{ dot['media.url.sfolder'] }")
	private String mediaUrlSFolder;

	@Value("#{ dot['media.url.element.faxs'] }")
	private String mediaUrlElementFaxs;

	@Value("#{ dot['media.url.live.folder'] }")
	private String mediaUrlLiveFolder;

	@Value("#{ dot['destpath.optimization.local'] }")
	private String destPathOptimizationLocal;

	@Value("#{ dot['manifest.unprotected.media.url.live.folder'] }")
	private String manifestUnProtectedMediaUrlLiveFolder;

}

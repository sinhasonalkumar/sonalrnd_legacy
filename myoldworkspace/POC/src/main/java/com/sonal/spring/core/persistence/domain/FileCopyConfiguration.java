package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class FileCopyConfiguration {

	@Value("#{ dot['package.file.extension'] }")
	private String packageFileExtn;
	
	@Value("#{ dot['flash.subtitles.file.extension'] }")
	private String flashSubtitlesFileExtn;
}

package com.sonal.spring.core.persistence.domain;

import org.springframework.beans.factory.annotation.Value;

public class PackagerCsvGenerationConfiguration {

	@Value("#{ dot['csv.file.generation.path'] }")
	private String csvFileLocation;	
	
	@Value("#{ dot['bit.rate.profile1'] }")
	private String adaptiveBitRate;	
	
	@Value("#{ dot['bit.rate.profile2'] }")
	private String fixedBitRate;	
	
	@Value("#{ dot['media.transfer1'] }")
	private String streamingMediaTransfer;	
	
	@Value("#{ dot['media.transfer2'] }")
	private String downloadMediaTransfer;	
}

package com.sonal.spring.facade;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.sonal.spring.facade.locator.FactoryLocator;
import com.sonal.spring.poc.configurationstore.configurations.Configuration;
import com.sonal.spring.poc.configurationstore.configurations.PackagingConfiguration;
import com.sonal.spring.poc.filemanager.filegrabber.IFileGrabber;
import com.sonal.spring.poc.filemanager.filepublisher.IFilePublisher;
import com.sonal.spring.poc.fileprocessor.packagers.IPackager;
import com.sonal.spring.poc.util.ApplicationContextUtil;

@Service
public class PackagerServiceFacade implements IPackagerServiceFacade {

	// @Transactional
	@Override
	public Map<String, Boolean> packageAndPublish(Long appId) {
	
		Map<IPackager, List<File>> mediaToPublish = new HashedMap();
		Map<String, Boolean> mediaPublishedStatus = new HashedMap();
		Configuration configuration = null;
		List<PackagingConfiguration> packageConfigurations = null;

		configuration = this.getConfiguration(appId);
		packageConfigurations = configuration.getPackageConfigurations();

		for (PackagingConfiguration curPackagingConfiguration : packageConfigurations) {
			startPackagingNPublishJob(curPackagingConfiguration, mediaToPublish, mediaPublishedStatus);
		}

		return mediaPublishedStatus;
	}

	private void startPackagingNPublishJob(PackagingConfiguration curPackagingConfiguration, Map<IPackager, List<File>> mediaToPublish, Map<String, Boolean> mediaPublishedStatus) {
		String currentSourcePath = null;
		String currentTargetPath = null;
		String currentPackager = null;

		currentSourcePath = curPackagingConfiguration.getSourceFileLocaction();
		currentTargetPath = curPackagingConfiguration.getTargetLocationToPublish();
		currentPackager = curPackagingConfiguration.getPackager();

		List<File> sourceFiles = this.getSourceFiles(currentSourcePath);
		List<File> packagedMedia = this.getPackagedMedia(currentPackager, sourceFiles, mediaToPublish);
		boolean hasPublishContent = this.publishPackagedMedia(packagedMedia, currentTargetPath);

		mediaPublishedStatus.put(currentPackager, hasPublishContent);
	}

	private Configuration getConfiguration(Long appId) {
		FactoryLocator factoryLocator = getFactoryLocator();
		Configuration configuration = factoryLocator.getConfigurationFactory().getConfiguration(appId);
		return configuration;
	}

	private List<File> getSourceFiles(String currentSourcePath) {
		FactoryLocator factoryLocator = getFactoryLocator();
		IFileGrabber fileGrabber = factoryLocator.getFileGrabberFactory().getFileGrabber("burfiler");
		List<File> sourceFiles = fileGrabber.getFiles(currentSourcePath);
		return sourceFiles;
	}

	private List<File> getPackagedMedia(String currentPackager, List<File> sourceFiles, Map<IPackager, List<File>> mediaToPublish) {
		FactoryLocator factoryLocator = getFactoryLocator();
		IPackager packager = factoryLocator.getPackagerFactory().getPackager(currentPackager);
		List<File> packagedMedia = packager.packageMedia(sourceFiles);
		mediaToPublish.put(packager, packagedMedia);
		return packagedMedia;
	}

	private boolean publishPackagedMedia(List<File> packagedMedia, String currentTargetPath) {
		FactoryLocator factoryLocator = getFactoryLocator();
		IFilePublisher filePublisher = factoryLocator.getFilePublisherFactory().getFilePublisher("stagePublisher");
		boolean hasPublishContent = filePublisher.publishContent(packagedMedia, currentTargetPath);
		return hasPublishContent;
	}

	private FactoryLocator getFactoryLocator() {
		ApplicationContext ctx = ApplicationContextUtil.getInstance().getApplicationContext();
		FactoryLocator factoryLocator = (FactoryLocator) ctx.getBean("factoryLocator");
		return factoryLocator;

	}

}

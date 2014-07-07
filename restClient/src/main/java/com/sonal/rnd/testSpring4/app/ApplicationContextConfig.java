package com.sonal.rnd.testSpring4.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "com.sonal.rnd.testSpring4")
@Configuration
@EnableAspectJAutoProxy
public class ApplicationContextConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		
		final MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

        final XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
		
		messageConverters.add(jsonConverter);
		messageConverters.add(xmlConverter);
		
		restTemplate.setMessageConverters(messageConverters);
		
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory getClientHttpRequestFactory() {

		int restClientConnectionTimeoutMillis = 300;
		int restClientReadTimeoutMillis = 300;

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(getHttpClient());
		factory.setConnectTimeout(restClientConnectionTimeoutMillis);
		factory.setReadTimeout(restClientReadTimeoutMillis);

		return factory;

	}

	@Bean
	public HttpClient getHttpClient() {

		int restClientMaxConnectionsPerHost = 2;
		int restClientMaxTotalConnections = 2;

		final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultMaxPerRoute(restClientMaxConnectionsPerHost);
		connectionManager.setMaxTotal(restClientMaxTotalConnections);
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();

		return httpClient;

	}

	/*
	 * public static void main(String[] args) { ApplicationContext
	 * applicationContext = new
	 * AnnotationConfigApplicationContext(ApplicationContextConfig.class);
	 * 
	 * }
	 */

}

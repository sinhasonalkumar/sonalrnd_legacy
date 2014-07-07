package com.sonal.rnd.testSpring4.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Throwables;

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
	public AsyncRestTemplate getAsyncRestTemplate() {
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(getAsyncHttpRequestFactory());
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		final MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		final XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		messageConverters.add(jsonConverter);
		messageConverters.add(xmlConverter);

		asyncRestTemplate.setMessageConverters(messageConverters);

		return asyncRestTemplate;
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

	@Bean
	public AsyncClientHttpRequestFactory getAsyncHttpRequestFactory() {
		return new HttpComponentsAsyncClientHttpRequestFactory(getAsyncHttpClient());
	}

	@Bean
	public CloseableHttpAsyncClient getAsyncHttpClient() {
		int DEFAULT_MAX_TOTAL_CONNECTIONS = 2;
		int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 2;
		int DEFAULT_READ_TIMEOUT_MILLISECONDS = 300;

		try {
			PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT));
			connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
			connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
			//connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("facebook.com")), 20);
			//connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("twitter.com")), 20);
			//connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("linkedin.com")), 20);
			RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).build();

			CloseableHttpAsyncClient httpclient = HttpAsyncClientBuilder.create().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
			return httpclient;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	/*
	 * public static void main(String[] args) { ApplicationContext
	 * applicationContext = new
	 * AnnotationConfigApplicationContext(ApplicationContextConfig.class);
	 * 
	 * }
	 */

}

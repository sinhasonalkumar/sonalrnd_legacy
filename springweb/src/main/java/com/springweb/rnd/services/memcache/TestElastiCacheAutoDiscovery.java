package com.springweb.rnd.services.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;

import net.spy.memcached.MemcachedClient;

@Component
public class TestElastiCacheAutoDiscovery {

	public void addToElastiCacheAutoDiscovery() throws IOException {
		String configEndpoint = "ElastiCache END POINT";
		Integer clusterPort = 11211;
		MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort));
		client.set("testKey", 3600, "Testing Auto Discovery");
	}
	
	public void removeFromElastiCacheAutoDiscovery() throws IOException {
		String configEndpoint = "ElastiCache END POINT";
		Integer clusterPort = 11211;
		MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort));
		client.delete("testKey");
	}
	
	public String getFromElastiCacheAutoDiscovery() throws IOException {
		String configEndpoint = "ElastiCache END POINT";
		Integer clusterPort = 11211;
		MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort));
		return (String) client.get("testKey");
	}

}

package com.sonal.java.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class TestElastiCacheAutoDiscovery {

	public static void main(String[] args) throws IOException {
		String configEndpoint = "mycluster.fnjyzo.cfg.use1.cache.amazonaws.com";
		Integer clusterPort = 11211;
		MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort));
		// The client will connect to the other cache nodes automatically
		// Store a data item for an hour. The client will decide which cache
		// host will store this item.
		client.set("theKey", 3600, "This is the data value");
	}

}

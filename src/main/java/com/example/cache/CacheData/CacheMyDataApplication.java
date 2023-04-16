package com.example.cache.CacheData;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class CacheMyDataApplication extends CachingConfigurerSupport
{

	public static void main(String[] args) {
		SpringApplication.run(CacheMyDataApplication.class, args);
	}



	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager()
	{
		CacheConfiguration leastTimeCache = new CacheConfiguration();
		leastTimeCache.setName("Least-Time-Cache");
		leastTimeCache.setMemoryStoreEvictionPolicy("LRU");
		leastTimeCache.setMaxEntriesLocalHeap(1000);
		leastTimeCache.setTimeToIdleSeconds(10);

		CacheConfiguration maxTimeCache = new CacheConfiguration();
		maxTimeCache.setName("Max-Time-Cache");
		maxTimeCache.setMemoryStoreEvictionPolicy("LRU");
		maxTimeCache.setMaxEntriesLocalHeap(1000);
		maxTimeCache.setTimeToIdleSeconds(20);

		net.sf.ehcache.config.Configuration  configuration = new net.sf.ehcache.config.Configuration();
		configuration.addCache(leastTimeCache);
		configuration.addCache(maxTimeCache);

		return net.sf.ehcache.CacheManager.newInstance(configuration);
	}

	@Bean
	@Override
	public CacheManager cacheManager()
	{
		return new EhCacheCacheManager(ehCacheManager());
	}



}

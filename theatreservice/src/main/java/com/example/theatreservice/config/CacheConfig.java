package com.example.theatreservice.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;






public class CacheConfig extends CachingConfigurerSupport {

	@Bean
	@Override
	public CacheManager cacheManager() {
		return super.cacheManager();
	}

	/*
	 * @Bean public net.sf.ehcache.CacheManager ehCacheMaanager() {
	 * 
	 * CacheConfiguration config= new CacheConfiguration(); //config. }
	 */

}

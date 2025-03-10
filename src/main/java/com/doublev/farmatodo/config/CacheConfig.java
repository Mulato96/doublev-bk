package com.doublev.farmatodo.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public com.github.benmanes.caffeine.cache.Cache<String, Object> cache() {
		return Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
				.maximumSize(1000)
				.build();
	}
}

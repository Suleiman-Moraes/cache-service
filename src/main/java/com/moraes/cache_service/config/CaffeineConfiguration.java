package com.moraes.cache_service.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.benmanes.caffeine.cache.Caffeine;

@Profile("caffeine")
@SuppressWarnings({ "rawtypes", "unchecked" })
@Configuration
public class CaffeineConfiguration {

    @Bean
    Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}

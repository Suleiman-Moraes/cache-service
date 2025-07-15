package com.moraes.cache_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.moraes.cache_service.api.dto.StudentDTO;

@Profile("redis")
@Configuration
public class RedisConfiguration {

    @Bean
    RedisTemplate<String, StudentDTO> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, StudentDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
package com.biubiu.client.config;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author: roc
 * @description: spring instance factory
 */
@Configuration
public class SpringBeanFactory {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean("scheduledExecutorService")
	public ScheduledExecutorService buildSchedule() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("scheduled-%d").setDaemon(true).build();
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, threadFactory);
		return scheduledExecutorService;
	}
}

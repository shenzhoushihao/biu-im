package com.biubiu.server.config;

import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import okhttp3.OkHttpClient;

/**
 * @author: roc
 * @description: spring instance factory
 */
@Configuration
public class SpringBeanFactory {

	@Autowired
	private IMServerConfiguration iMServerConfig;

	@Bean
	public ZkClient zkClient() {
		return new ZkClient(iMServerConfig.getZkAddr(), iMServerConfig.getZkConnectTimeout());
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public OkHttpClient okHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true);
		return builder.build();
	}
}

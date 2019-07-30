package com.biubiu.route.config;

import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.biubiu.common.util.IMConstants;
import com.biubiu.route.handler.RouteHandler;

import okhttp3.OkHttpClient;

/**
 * @author: roc
 * @description: spring instance factory
 */
@Configuration
public class SpringBeanFactory {

	@Autowired
	private IMRouteConfiguration iMRouteConfig;

	@Bean
	public ZkClient zkClient() {
		return new ZkClient(iMRouteConfig.getZkAddr(), iMRouteConfig.getZkConnectTimeout());
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

	/**
	 * load route strategy
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Bean
	public RouteHandler routeHandler() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String routeStrategy = iMRouteConfig.getRouteStrategy();
		RouteHandler routeHandler;
		if (IMConstants.RouteType.LOOP.equalsIgnoreCase(routeStrategy)) {
			routeHandler = (RouteHandler) Class.forName(IMConstants.RouteType.LOOP_CLASS).newInstance();
		} else {
			routeHandler = (RouteHandler) Class.forName(IMConstants.RouteType.RANDOM_CLASS).newInstance();
		}
		return routeHandler;
	}
}

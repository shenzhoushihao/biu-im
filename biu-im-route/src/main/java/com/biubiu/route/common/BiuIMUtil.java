package com.biubiu.route.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import com.biubiu.common.exception.BiuException;
import com.biubiu.common.util.ErrorDefinition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BiuIMUtil {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 获取符合条件的key
	 * 
	 * @param pattern 表达式
	 * @return
	 */
	public Set<String> scan(String pattern) {
		Set<String> keys = new HashSet<>();
		this.scan(pattern, item -> {
			// 符合条件的key
			String key = new String(item, StandardCharsets.UTF_8);
			keys.add(key);
		});
		return keys;
	}

	private void scan(String pattern, Consumer<byte[]> consumer) {
		this.redisTemplate.executeWithStickyConnection((RedisConnection connection) -> {
			try (Cursor<byte[]> cursor = connection
					.scan(ScanOptions.scanOptions().count(Integer.MAX_VALUE).match(pattern).build())) {
				cursor.forEachRemaining(consumer);
				return null;
			} catch (IOException e) {
				log.error("redis scan keys error,exception:", e);
				throw new BiuException(ErrorDefinition.REDIS_SCAN_FAILURE);
			}
		});
	}
}

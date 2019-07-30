package com.biubiu.route.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.biubiu.common.data.IMUserInfo;
import com.biubiu.common.util.IMConstants;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: roc
 * @description: user cache center
 */
@Slf4j
@Component
public class UserInfoCache {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private final LoadingCache<String, IMUserInfo> userInfoCache = CacheBuilder.newBuilder()
			// set 50 for concurrency level，which refers to the number of threads that can
			// write cache at the same time.
			.concurrencyLevel(50)
			// set the initial capacity of the cache container to 500
			.initialCapacity(500)
			// set the max capacity of the cache container to 1000，LRU
			.maximumSize(1000)
			// n seconds expired after setting write cache
			.expireAfterWrite(20, TimeUnit.SECONDS)
			// set the read and write cache to expire in n seconds
			.expireAfterAccess(20, TimeUnit.SECONDS)
			// only block the current data loading thread, other threads return the old
			// value
			.refreshAfterWrite(20, TimeUnit.SECONDS)
			// set cache remove notifications
			.removalListener(notification -> log.info("remove key:[{}],value:[{}] from userCacheInfoCenter,because:{}",
					notification.getKey(), notification.getValue(), notification.getCause()))
			// CacheLoader，implement automatic load caching
			.build(new UserInfoCacheLoader());

	public IMUserInfo getImUserInfo(String userId) {
		return userInfoCache.getIfPresent(userId);
	}

	/**
	 * save user login status
	 * 
	 * @param userId
	 * @return
	 */
	public boolean saveUserLoginStatus(String userId) {
		Long addStatus = redisTemplate.boundSetOps(IMConstants.LOGIN_STATUS).add(userId);
		return (addStatus == 0);
	}

	public void removeLoginStatus(String userId) {
		redisTemplate.boundSetOps(IMConstants.LOGIN_STATUS).remove(userId);
	}

	public Set<IMUserInfo> onlineUser() {
		Set<IMUserInfo> userSet = new HashSet<>(128);
		Set<String> members = redisTemplate.boundSetOps(IMConstants.LOGIN_STATUS).members();
		for (String member : members) {
			IMUserInfo imUserInfo = getImUserInfo(member);
			userSet.add(imUserInfo);
		}
		return userSet;
	}

	private class UserInfoCacheLoader extends CacheLoader<String, IMUserInfo> {
		@Override
		public IMUserInfo load(String key) throws Exception {
			String username = redisTemplate.boundValueOps(String.format(IMConstants.ACCOUNT_PREFIX, key)).get();
			if (StringUtils.isNotBlank(username)) {
				return new IMUserInfo(key, username);
			}
			return null;
		}
	}
}

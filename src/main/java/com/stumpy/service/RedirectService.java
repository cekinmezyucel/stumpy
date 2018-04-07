package com.stumpy.service;

import static com.stumpy.config.RedisConfigurationConstants.ID_FULL_URL_INDEX_KEY;
import static com.stumpy.util.UrlGeneratorUtil.decode;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public String getLongUrl(String shortUrl) {
		Object object = redisTemplate.opsForHash().get(ID_FULL_URL_INDEX_KEY, decode(shortUrl));
		if (Objects.isNull(object)) {
			throw new RuntimeException();
		} else {
			return (String) object;
		}
	}

}

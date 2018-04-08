package com.stumpy.service;

import static com.stumpy.config.RedisConfigurationConstants.FULL_URL_ID_INDEX_KEY;
import static com.stumpy.config.RedisConfigurationConstants.ID_FULL_URL_INDEX_KEY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.stumpy.util.UrlGeneratorUtil;

@Service
public class ShortenerService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public String getShortUrl(String longUrl) {
		validateLongUrl(longUrl);
		Boolean putIfAbsent = redisTemplate.opsForHash().putIfAbsent(FULL_URL_ID_INDEX_KEY, longUrl, generateNewId());
		Long id = (Long) redisTemplate.opsForHash().get(FULL_URL_ID_INDEX_KEY, longUrl);
		redisTemplate.opsForHash().putIfAbsent(ID_FULL_URL_INDEX_KEY, id, longUrl);
		return UrlGeneratorUtil.encode(id);
	}

	private void validateLongUrl(String longUrl) {
		// TODO : add some validations.
	}

	private Long generateNewId() {
		return 1002L;
	}

}

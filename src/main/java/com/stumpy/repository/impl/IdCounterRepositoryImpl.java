package com.stumpy.repository.impl;

import static com.stumpy.config.RedisConfigurationConstants.ID_COUNTER_KEY;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import com.stumpy.repository.IdCounterRepository;

@Repository
public class IdCounterRepositoryImpl implements IdCounterRepository {

  private RedisTemplate<String, Long> redisCounterTemplate;

  private RedisAtomicLong redisAtomicLong;

  @Autowired
  public IdCounterRepositoryImpl(RedisTemplate<String, Long> redisCounterTemplate) {
    this.redisCounterTemplate = redisCounterTemplate;
  }

  @PostConstruct
  private void init() {
    this.redisAtomicLong = new RedisAtomicLong(ID_COUNTER_KEY, redisCounterTemplate);
  }

  @Override
  public Long getAndIncrement() {
    return redisAtomicLong.getAndIncrement();
  }

}

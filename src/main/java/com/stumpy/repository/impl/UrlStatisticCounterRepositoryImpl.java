package com.stumpy.repository.impl;

import static com.stumpy.config.RedisConfigurationConstants.URL_STATISTIC_COUNTER_KEY;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.stumpy.model.UrlStatisticCounterModel;
import com.stumpy.repository.UrlStatisticCounterRepository;

public class UrlStatisticCounterRepositoryImpl implements UrlStatisticCounterRepository {

  private RedisTemplate<String, Object> redisTemplate;

  @SuppressWarnings("rawtypes")
  private HashOperations hashOperations;

  @Autowired
  public UrlStatisticCounterRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<Long, UrlStatisticCounterModel> findAllEntities() {
    return hashOperations.entries(URL_STATISTIC_COUNTER_KEY);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void add(UrlStatisticCounterModel entity) {
    hashOperations.put(URL_STATISTIC_COUNTER_KEY, entity.getId(), entity);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void delete(Long id) {
    hashOperations.delete(URL_STATISTIC_COUNTER_KEY, id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public UrlStatisticCounterModel findEntity(Long id) {
    return (UrlStatisticCounterModel) hashOperations.get(URL_STATISTIC_COUNTER_KEY, id);
  }

}

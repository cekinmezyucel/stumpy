package com.stumpy.repository.impl;

import static com.stumpy.config.RedisConfigurationConstants.URL_MODEL_KEY;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.stumpy.model.UrlModel;
import com.stumpy.repository.UrlModelRepository;


@Repository
public class UrlModelRepositoryImpl implements UrlModelRepository {

  private RedisTemplate<String, Object> redisTemplate;

  @SuppressWarnings("rawtypes")
  private HashOperations hashOperations;

  @Autowired
  public UrlModelRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<Object, Object> findAllEntities() {
    return hashOperations.entries(URL_MODEL_KEY);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void add(final UrlModel urlModel) {
    hashOperations.put(URL_MODEL_KEY, urlModel.getId(), urlModel);

  }

  @SuppressWarnings("unchecked")
  @Override
  public void delete(Long id) {
    hashOperations.delete(URL_MODEL_KEY, id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public UrlModel findEntity(Long id) {
    return (UrlModel) hashOperations.get(URL_MODEL_KEY, id);
  }

}

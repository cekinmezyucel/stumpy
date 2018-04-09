package com.stumpy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Bean
  JedisConnectionFactory getJedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(new RedisStandaloneConfiguration());
    return jedisConnectionFactory;
  }

  /**
   * Redis template generator.
   * 
   * @return {@link RedisTemplate}.
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(getJedisConnectionFactory());
    return template;
  }

  /**
   * Redis counter template generator.
   * 
   * @return {@link RedisTemplate}.
   */
  @Bean
  public RedisTemplate<String, Long> redisCounterTemplate() {
    RedisTemplate<String, Long> template = new RedisTemplate<>();
    template.setConnectionFactory(getJedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
    return template;
  }

}

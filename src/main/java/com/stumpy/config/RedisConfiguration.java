package com.stumpy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Value("${redis.hostName}")
  private String hostName;

  @Value("${redis.port}")
  private int port;

  @Bean
  JedisConnectionFactory getJedisConnectionFactory() {
    return new JedisConnectionFactory(new RedisStandaloneConfiguration(hostName, port));
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

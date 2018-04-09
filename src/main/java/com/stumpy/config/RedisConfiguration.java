package com.stumpy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

  @Bean
  JedisConnectionFactory getJedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName("localhost");
    jedisConnectionFactory.setPort(6379);
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

}

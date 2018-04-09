package com.stumpy.service;

import static com.stumpy.util.UrlGeneratorUtil.decode;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stumpy.model.UrlModel;
import com.stumpy.repository.UrlModelRepository;

@Service
public class RedirectService {

  @Autowired
  UrlModelRepository redisRepository;

  /**
   * Retrieve longUrl for given shortUrl from Redis.
   * 
   * @param shortUrl.
   * @return {@link String}.
   */
  public String getLongUrl(String shortUrl) {
    UrlModel urlModel = redisRepository.findUrlModel(decode(shortUrl));

    if (Objects.nonNull(urlModel)) {
      return urlModel.getLongUrl();
    } else {
      throw new RuntimeException();
    }
  }

}

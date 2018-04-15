package com.stumpy.service;

import static com.stumpy.util.UrlGeneratorUtil.decode;
import static java.util.Objects.nonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stumpy.exception.model.StumpyException;
import com.stumpy.model.UrlModel;
import com.stumpy.repository.UrlModelRepository;

@Service
public class RedirectService {

  private static final Logger LOG = LoggerFactory.getLogger(RedirectService.class);

  @Autowired
  UrlModelRepository redisRepository;

  /**
   * Retrieve longUrl for given shortUrl from Redis.
   * 
   * @param shortUrl.
   * @return {@link String}.
   */
  public String getLongUrl(String shortUrl) {
    UrlModel urlModel = redisRepository.findEntity(decode(shortUrl));

    if (nonNull(urlModel)) {
      LOG.info("Long url found:" + urlModel.toString());
      return urlModel.getLongUrl();
    } else {
      throw new StumpyException("STUMPY003");
    }
  }

}

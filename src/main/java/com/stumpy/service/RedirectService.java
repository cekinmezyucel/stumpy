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
  private UrlModelRepository urlModelRepository;

  @Autowired
  private StatisticService statisticService;

  /**
   * Retrieve longUrl for given shortUrl from Redis.
   * 
   * @param shortUrl.
   * @return {@link String}.
   */
  public String getLongUrl(String shortUrl) {
    Long id = decode(shortUrl);
    UrlModel urlModel = urlModelRepository.findEntity(id);

    if (nonNull(urlModel)) {
      LOG.info("Long url found:" + urlModel.toString());
      statisticService.executeStatisticOperations(id);
      return urlModel.getLongUrl();
    } else {
      throw new StumpyException("STUMPY003");
    }
  }

}

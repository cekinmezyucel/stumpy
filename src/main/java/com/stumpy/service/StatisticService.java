package com.stumpy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

  private static final Logger LOG = LoggerFactory.getLogger(StatisticService.class);

  public Long getHitCount(String shortUrl) {
    // TODO impl.
    return 1L;
  }


}

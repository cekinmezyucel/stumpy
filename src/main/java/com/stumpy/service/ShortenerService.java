package com.stumpy.service;

import static com.stumpy.util.UrlGeneratorUtil.encode;

import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stumpy.model.UrlModel;
import com.stumpy.repository.IdCounterRepository;
import com.stumpy.repository.UrlModelRepository;

@Service
public class ShortenerService {

  private static final Logger LOG = LoggerFactory.getLogger(ShortenerService.class);

  @Autowired
  private UrlModelRepository urlModelRepository;

  @Autowired
  private IdCounterRepository idCounterRepository;

  /**
   * Generate shortUrl and put in Redis. If the longUrl exist in the database, existing id will return
   * with shortUrl encoding.
   * 
   * @param longUrl.
   * @return {@link String}.
   */
  public String getShortUrl(String longUrl) {
    Optional<Long> id = urlModelRepository.findAllEntities().entrySet().stream()
        .filter(m -> m.getValue().getLongUrl().equals(longUrl)).map(Entry::getKey).findAny();
    return encode(id.orElseGet(() -> addNewLongUrl(longUrl)));
  }

  private Long addNewLongUrl(String longUrl) {
    Long id = idCounterRepository.getAndIncrement();
    urlModelRepository.add(new UrlModel(id, longUrl));
    LOG.info("Given url is shortened. Short URL: " + encode(id) + ", Long URL: " + longUrl);
    return id;
  }



}

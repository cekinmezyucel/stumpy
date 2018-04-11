package com.stumpy.service;

import static com.stumpy.util.UrlGeneratorUtil.encode;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stumpy.exception.StumpyException;
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

    validateLongUrl(longUrl);

    Optional<Entry<Object, Object>> findAny = urlModelRepository.findAllUrlModels().entrySet().stream()
        .filter(m -> ((UrlModel) m.getValue()).getLongUrl().equals(longUrl)).findAny();

    return findAny.isPresent() ? encode((Long) findAny.get().getKey()) : addNewLongUrl(longUrl);
  }

  private String addNewLongUrl(String longUrl) {
    Long id = idCounterRepository.getAndIncrement();
    urlModelRepository.add(new UrlModel(id, longUrl));
    String shortUrl = encode(id);
    LOG.info("Given url is shortened. Short URL: " + shortUrl + ", Long URL: " + longUrl);
    return shortUrl;
  }

  private void validateLongUrl(String longUrl) {
    if (!isValidURL(longUrl)) {
      throw new StumpyException("STUMPY002", longUrl);
    }
  }

  private static boolean isValidURL(String longUrl) {
    URL url = null;
    try {
      url = new URL(longUrl);
    } catch (MalformedURLException exception) {
      return false;
    }
    try {
      url.toURI();
    } catch (URISyntaxException exception) {
      return false;
    }
    return true;
  }

}

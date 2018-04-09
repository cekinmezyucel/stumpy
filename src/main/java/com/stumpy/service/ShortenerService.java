package com.stumpy.service;

import static com.stumpy.util.UrlGeneratorUtil.encode;

import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stumpy.model.UrlModel;
import com.stumpy.repository.IdCounterRepository;
import com.stumpy.repository.UrlModelRepository;

@Service
public class ShortenerService {

  @Autowired
  private UrlModelRepository urlModelRepository;

  @Autowired
  private IdCounterRepository idCounterRepository;

  /**
   * Generate shortUrl and put in Redis. If the longUrl exist in the databse, existing id will return
   * with shortUrl encoding.
   * 
   * @param longUrl.
   * @return {@link String}.
   */
  public String getShortUrl(String longUrl) {
    Optional<Entry<Object, Object>> findAny = urlModelRepository.findAllUrlModels().entrySet().stream()
        .filter(m -> ((UrlModel) m.getValue()).getLongUrl().equals(longUrl)).findAny();

    return findAny.isPresent() ? encode((Long) findAny.get().getKey()) : addNewLongUrl(longUrl);
  }

  private String addNewLongUrl(String longUrl) {
    Long id = idCounterRepository.getAndIncrement();
    urlModelRepository.add(new UrlModel(id, longUrl));
    return encode(id);
  }

  private void validateLongUrl(String longUrl) {
    // TODO : add some validations.
  }

}

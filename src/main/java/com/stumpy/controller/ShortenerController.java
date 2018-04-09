package com.stumpy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.model.UrlModel;
import com.stumpy.service.ShortenerService;

@RestController
public class ShortenerController {


  @Autowired
  ShortenerService shortenerService;

  /**
   * Url shortener method.
   * 
   * @param longUrl.
   * @return {@link UrlModel}.
   */
  @RequestMapping("/shortener")
  public UrlModel getUrlModel(@RequestParam(value = "longUrl") String longUrl) {
    String shortUrl = shortenerService.getShortUrl(longUrl);
    return new UrlModel(1L, shortUrl, longUrl);
  }

}

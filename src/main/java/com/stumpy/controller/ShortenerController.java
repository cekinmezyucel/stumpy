package com.stumpy.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.model.LongUrlModel;
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
  @RequestMapping(path = "/shortener", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, method = POST)
  public String getShortUrl(@RequestBody LongUrlModel longUrlModel) {
    return shortenerService.getShortUrl(longUrlModel.getLongUrl());
  }

}

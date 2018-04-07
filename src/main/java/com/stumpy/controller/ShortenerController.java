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

	@RequestMapping("/shortener")
	public UrlModel getUrlModel(@RequestParam(value = "url") String url) {
		String shortUrl = shortenerService.getShortUrl(url);
		return new UrlModel(1L, shortUrl, url);
	}

}

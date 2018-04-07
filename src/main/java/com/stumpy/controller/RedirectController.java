package com.stumpy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.model.RedirectModel;
import com.stumpy.service.RedirectService;

@RestController
public class RedirectController {

	@Autowired
	RedirectService redirectService;

	@RequestMapping(value = "/{shortUrl}")
	public RedirectModel redirect(@PathVariable String shortUrl) {
		String longUrl = redirectService.getLongUrl(shortUrl);
		return new RedirectModel(longUrl);
	}

}

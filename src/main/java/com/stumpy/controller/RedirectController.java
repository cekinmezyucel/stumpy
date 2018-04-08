package com.stumpy.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.service.RedirectService;

@RestController
public class RedirectController {

	@Autowired
	RedirectService redirectService;

	@GET
	@RequestMapping(value = "/{shortUrl}")
	public void redirect(@PathVariable String shortUrl, @Context HttpServletResponse httpServletResponse) {
		String longUrl = redirectService.getLongUrl(shortUrl);
		URI uri = UriBuilder.fromUri("https://translate.google.com").build();
		httpServletResponse.setHeader("Location", longUrl);
		httpServletResponse.setStatus(301);
	}

}

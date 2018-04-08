package com.stumpy.controller;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
	public Response redirect(@PathVariable String shortUrl) {
		String longUrl = redirectService.getLongUrl(shortUrl);
		URI uri = UriBuilder.fromUri("https://translate.google.com/").build();
		return Response.status(Status.MOVED_PERMANENTLY).header("Location", "https://translate.google.com/").build();
	}

}

package com.stumpy.controller;

import static org.springframework.http.HttpStatus.TEMPORARY_REDIRECT;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.service.RedirectService;

@RestController
public class RedirectController {

  @Autowired
  private RedirectService redirectService;

  /**
   * Redirects short URL to LongURL.
   * 
   * <p>
   * Main redirect method. Retrieve shortUrl and redirect with TEMPORARY_REDIRECT(307).
   * TEMPORARY_REDIRECT important for statistics. It prevents browser cache redirects.
   * </p>
   * 
   * @param shortUrl.
   * @param httpServletResponse.
   */
  @ResponseStatus(TEMPORARY_REDIRECT)
  @GetMapping(value = "/{shortUrl}")
  public void redirect(@PathVariable String shortUrl, @Context HttpServletResponse httpServletResponse) {
    String longUrl = redirectService.getLongUrl(shortUrl);
    httpServletResponse.setHeader("Location", longUrl);
  }

}

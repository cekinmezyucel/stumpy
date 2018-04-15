package com.stumpy.controller;

import static org.springframework.http.HttpStatus.TEMPORARY_REDIRECT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.service.RedirectService;

@RestController
public class RedirectController {

  @Autowired
  private RedirectService redirectService;

  /**
   * Redirect Service.
   * 
   * <p>
   * Main redirect method. Retrieve shortUrl and redirect with TEMPORARY_REDIRECT(307).
   * </p>
   * 
   * @param shortUrl.
   * @param httpServletResponse.
   */
  @ResponseStatus(TEMPORARY_REDIRECT)
  @RequestMapping(value = "/{shortUrl}", method = GET)
  public void redirect(@PathVariable String shortUrl, @Context HttpServletResponse httpServletResponse) {
    String longUrl = redirectService.getLongUrl(shortUrl);
    httpServletResponse.setHeader("Location", longUrl);
  }

}

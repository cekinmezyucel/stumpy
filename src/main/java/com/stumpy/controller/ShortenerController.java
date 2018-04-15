package com.stumpy.controller;

import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GENERATE_DESCRIPTION;
import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GENERATE_NAME;
import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GENERATE_PATH;
import static com.stumpy.localization.SwaggerLocalizations.STANDART_PROD_CONS;
import static com.stumpy.util.UrlGeneratorUtil.buildFullPath;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.controller.request.ShortenerRequest;
import com.stumpy.controller.response.ShortenerResponse;
import com.stumpy.service.ShortenerService;

@RestController
@RequestMapping("/shortener")
@Api(value = "shortener", produces = STANDART_PROD_CONS, protocols = "https", consumes = STANDART_PROD_CONS,
    tags = {"shortener"})
public class ShortenerController {

  @Autowired
  private ShortenerService shortenerService;

  /**
   * Url shortener method.
   * 
   * @param shortenerRequest.
   * @return {@link ShortenerResponse}.
   */
  @ResponseStatus(OK)
  @RequestMapping(path = "/generate", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, method = POST)
  @ApiOperation(nickname = SHORTENER_GENERATE_PATH, produces = STANDART_PROD_CONS, consumes = STANDART_PROD_CONS,
      value = SHORTENER_GENERATE_NAME, notes = SHORTENER_GENERATE_DESCRIPTION, response = ShortenerResponse.class)
  public ShortenerResponse generateShortUrl(@Valid @RequestBody ShortenerRequest shortenerRequest,
      @Context HttpServletRequest httpServletRequest) {
    return new ShortenerResponse(
        buildFullPath(httpServletRequest, shortenerService.getShortUrl(shortenerRequest.getLongUrl())));
  }

}

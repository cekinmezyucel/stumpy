package com.stumpy.controller;

import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GET_DESCRIPTION;
import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GET_NAME;
import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_GET_PATH;
import static com.stumpy.localization.SwaggerLocalizations.STANDART_PROD_CONS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.controller.base.BaseController;
import com.stumpy.controller.request.ShortenerRequest;
import com.stumpy.controller.response.ShortenerResponse;
import com.stumpy.model.UrlModel;
import com.stumpy.service.ShortenerService;

@RestController
public class ShortenerController extends BaseController {


  @Autowired
  ShortenerService shortenerService;

  /**
   * Url shortener method.
   * 
   * @param longUrl.
   * @return {@link UrlModel}.
   */
  @RequestMapping(path = "/shortener", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
      produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, method = POST)
  @ApiOperation(nickname = SHORTENER_GET_PATH, produces = STANDART_PROD_CONS, consumes = STANDART_PROD_CONS,
      value = SHORTENER_GET_NAME, notes = SHORTENER_GET_DESCRIPTION, response = String.class)
  public ShortenerResponse getShortUrl(@Valid @RequestBody ShortenerRequest shortenerRequest) {
    return new ShortenerResponse(shortenerService.getShortUrl(shortenerRequest.getLongUrl()));
  }

}

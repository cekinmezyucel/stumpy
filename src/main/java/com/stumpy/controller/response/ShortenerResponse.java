package com.stumpy.controller.response;

import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_RESPONSE;
import static com.stumpy.localization.SwaggerLocalizations.SHORT_URL;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.stumpy.model.api.ResponseBody;

@ApiModel(description = SHORTENER_RESPONSE)
public class ShortenerResponse implements ResponseBody {

  private static final long serialVersionUID = -4228689146795847454L;

  @ApiModelProperty(value = SHORT_URL, required = true)
  private String shortUrl;

  public ShortenerResponse() {
    super();
  }

  public ShortenerResponse(String shortUrl) {
    super();
    this.shortUrl = shortUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

}

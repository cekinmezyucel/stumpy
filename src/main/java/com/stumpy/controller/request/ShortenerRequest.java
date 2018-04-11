package com.stumpy.controller.request;

import static com.stumpy.localization.SwaggerLocalizations.LONG_URL;
import static com.stumpy.localization.SwaggerLocalizations.SHORTENER_REQUEST;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.stumpy.model.api.RequestBody;

@ApiModel(description = SHORTENER_REQUEST)
public class ShortenerRequest implements RequestBody {

  private static final long serialVersionUID = -7673548390118846860L;

  @NotNull(message = "{error.validation.notnull.longUrl}")
  @ApiModelProperty(value = LONG_URL, required = true)
  private String longUrl;

  public ShortenerRequest() {
    super();
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

}

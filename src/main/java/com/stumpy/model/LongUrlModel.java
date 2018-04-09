package com.stumpy.model;

import java.io.Serializable;

public class LongUrlModel implements Serializable {

  private static final long serialVersionUID = 6012607193836897563L;

  private String longUrl;

  public LongUrlModel() {
    super();
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

}

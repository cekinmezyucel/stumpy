package com.stumpy.model;

import java.io.Serializable;
import java.util.Objects;

public class UrlModel implements Serializable {

  private static final long serialVersionUID = 8005933239246279968L;

  private Long id;
  private String longUrl;

  /**
   * Constructor using all fields.
   * 
   * @param id.
   * @param longUrl.
   */
  public UrlModel(Long id, String longUrl) {
    super();
    this.id = id;
    this.longUrl = longUrl;
  }

  public UrlModel() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UrlModel other = (UrlModel) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  // TODO: add to string method.

}

package com.stumpy.util;

import java.util.Base64;

public class UrlGeneratorUtil {

  private UrlGeneratorUtil() {
    super();
  }

  public static String encode(Long id) {
    return Base64.getUrlEncoder().withoutPadding().encodeToString(String.valueOf(id).getBytes());
  }

  public static Long decode(String shortUrl) {
    return Long.valueOf(new String(Base64.getUrlDecoder().decode(shortUrl)));
  }

}

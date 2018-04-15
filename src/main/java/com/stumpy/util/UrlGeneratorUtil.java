package com.stumpy.util;

import java.net.URL;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

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

  /**
   * Generate a full path {@link URL} with given {@link HttpServletRequest} and shortUrl for
   * redirection.
   * 
   * @param request.
   * @param shortUrl.
   * @return {@link String}.
   */
  public static String buildFullPath(HttpServletRequest request, String shortUrl) {
    return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName()).append(":")
        .append(request.getServerPort()).append("/").append(shortUrl).toString();

  }

}

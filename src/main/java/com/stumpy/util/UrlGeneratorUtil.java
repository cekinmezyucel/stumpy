package com.stumpy.util;

import java.net.URL;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stumpy.exception.StumpyException;

public class UrlGeneratorUtil {

  private static final Logger LOG = LoggerFactory.getLogger(UrlGeneratorUtil.class);

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
    try {
      return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName()).append(":")
          .append(request.getServerPort()).append("/").append(shortUrl).toString();
    } catch (Exception exception) {
      LOG.error("Error occured when building full path for short url", exception);
      throw new StumpyException("STUMPY001");
    }
  }

}

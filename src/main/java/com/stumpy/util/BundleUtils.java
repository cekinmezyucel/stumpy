package com.stumpy.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(BundleUtils.class);

  private BundleUtils() {
    super();
  }

  public static String getFromStumpyBundle(String messageKey, Object... params) {
    return getFrom("com.stumpy.i18.exception-bundle", messageKey, params);
  }

  private static String getFrom(String bundleFullName, String messageKey, Object... params) {
    ClassLoader loader = getClassLoader();

    if (messageKey.indexOf('.') == -1) {
      return messageKey;
    }

    // Default Locale is US.
    Locale locale = Locale.US;

    ResourceBundle bundle = ResourceBundle.getBundle(bundleFullName, locale, loader);

    String fromBundle = "";

    try {
      fromBundle = bundle.getString(messageKey);
    } catch (MissingResourceException exception) {
      fromBundle = messageKey;
    }

    if (Objects.isNull(params) || params.length == 0) {
      return fromBundle;
    }

    MessageFormat formatter = new MessageFormat(fromBundle, locale);
    try {
      return formatter.format(params);
    } catch (Exception exception) {
      LOGGER.error("Could not format " + messageKey, exception);
    }
    return messageKey;
  }

  private static ClassLoader getClassLoader() {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    if (loader == null) {
      loader = ClassLoader.getSystemClassLoader();
    }
    return loader;
  }

}

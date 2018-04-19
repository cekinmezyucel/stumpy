package com.stumpy.validation.validator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.stumpy.validation.Url;

public class UrlValidator implements ConstraintValidator<Url, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext arg1) {
    return isValidURL(value);
  }

  private static boolean isValidURL(String longUrl) {
    URL url = null;
    try {
      url = new URL(longUrl);
    } catch (MalformedURLException exception) {
      return false;
    }
    try {
      url.toURI();
    } catch (URISyntaxException exception) {
      return false;
    }
    return true;
  }

}

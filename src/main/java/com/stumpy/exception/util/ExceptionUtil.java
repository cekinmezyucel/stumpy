package com.stumpy.exception.util;

import com.stumpy.exception.base.BaseException;
import com.stumpy.exception.model.ExceptionRestResult;

public class ExceptionUtil {

  private ExceptionUtil() {
    super();
  }

  public static ExceptionRestResult convertException(BaseException exception) {
    return new ExceptionRestResult(exception.getCode(), exception.getHttpStatus(), exception.getErrorMessage());
  }

}

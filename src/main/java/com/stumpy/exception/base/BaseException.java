package com.stumpy.exception.base;

import com.stumpy.util.BundleUtils;

public abstract class BaseException extends RuntimeException {

  private static final long serialVersionUID = -7713838846698258984L;

  private final String code;
  private final int httpStatus;
  private final transient Object[] parameters;
  private final String errorMessage;


  /**
   * Constructor.
   * 
   * @param code.
   * @param parameters.
   */
  public BaseException(String code, Object... parameters) {
    super();
    this.code = code;
    this.parameters = parameters;
    this.errorMessage = BundleUtils.getFromStumpyBundle("error." + code.toUpperCase() + ".text", parameters);
    this.httpStatus =
        Integer.parseInt(BundleUtils.getFromStumpyBundle("error." + code.toUpperCase() + ".httpstatus", parameters));
  }

  public String getCode() {
    return code;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public Object[] getParameters() {
    return parameters;
  }

  public String getErrorMessage() {
    return errorMessage;
  }



}

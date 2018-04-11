package com.stumpy.exception.model;

import java.io.Serializable;

public class ExceptionRestResult implements Serializable {

  private static final long serialVersionUID = 5329371860978318741L;

  private String code;
  private int httpStatus;
  private String userMessage;

  public ExceptionRestResult() {
    super();
  }

  /**
   * Constructor with all fields.
   * 
   * @param code.
   * @param httpStatus.
   * @param userMessage.
   */
  public ExceptionRestResult(String code, int httpStatus, String userMessage) {
    super();
    this.code = code;
    this.httpStatus = httpStatus;
    this.userMessage = userMessage;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(int httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

}

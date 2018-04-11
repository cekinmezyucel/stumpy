package com.stumpy.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.stumpy.exception.StumpyException;
import com.stumpy.exception.base.BaseException;
import com.stumpy.exception.model.ExceptionRestResult;
import com.stumpy.exception.util.ExceptionUtil;

public class BaseController {

  private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

  /**
   * Exception handler for Application.
   * 
   * @param exception.
   * @param request.
   * @return {@link ResponseEntity}.
   */
  @ExceptionHandler({BaseException.class})
  public ResponseEntity<Object> handleAll(BaseException exception, WebRequest request) {

    LOG.error("An error occured", exception);

    ExceptionRestResult exceptionRestResult = null;

    if (exception instanceof BaseException) {
      if (exception instanceof StumpyException) {
        exceptionRestResult = ExceptionUtil.convertException(exception);
      } else {
        // This section will extend for future exception types.
      }
    }

    return new ResponseEntity<>(exceptionRestResult, new HttpHeaders(),
        HttpStatus.valueOf(exceptionRestResult.getHttpStatus()));
  }

}

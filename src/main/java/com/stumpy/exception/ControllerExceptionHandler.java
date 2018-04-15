package com.stumpy.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stumpy.exception.base.BaseException;
import com.stumpy.exception.base.BaseExceptionHandler;
import com.stumpy.exception.model.ExceptionRestResult;

@ControllerAdvice
public class ControllerExceptionHandler extends BaseExceptionHandler {

  /**
   * Exception handler for Exceptions.
   * 
   * @param exception.
   * @return {@link ResponseEntity}.
   */
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ResponseBody
  @ExceptionHandler({Throwable.class})
  public ExceptionRestResult handleException(Throwable exception) {
    return handle(exception);
  }

  /**
   * Exception handler for Business Exceptions.
   * 
   * @param exception.
   * @return {@link ResponseEntity}.
   */
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ResponseBody
  @ExceptionHandler({BaseException.class})
  public ExceptionRestResult handleBaseException(BaseException exception) {
    return handle(exception);
  }


  /**
   * Exception handler for bad requests.
   * 
   * @param exception.
   * @return {@link ExceptionRestResult}.
   */
  @ResponseStatus(BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
  public ExceptionRestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    return handle(exception);
  }



}

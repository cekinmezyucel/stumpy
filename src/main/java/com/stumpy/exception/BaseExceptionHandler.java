package com.stumpy.exception;

import static com.stumpy.exception.util.ExceptionUtil.convertException;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.stumpy.exception.base.BaseException;
import com.stumpy.exception.model.ExceptionRestResult;

public abstract class BaseExceptionHandler {

  static final Logger LOG = LoggerFactory.getLogger(BaseExceptionHandler.class);

  /**
   * Handle Internal Exceptions.
   * 
   * @param exception.
   * @return {@link ExceptionRestResult}
   */
  protected ExceptionRestResult handle(Throwable exception) {
    LOG.error("An Exception occured", exception);
    return convertException(new StumpyException("STUMPY001"));
  }

  /**
   * Handle Business Exceptions.
   * 
   * @param exception.
   * @return {@link ExceptionRestResult}.
   */
  protected ExceptionRestResult handle(BaseException exception) {
    LOG.error("A Business Exception occured", exception);
    ExceptionRestResult exceptionRestResult = null;
    if (exception instanceof BaseException) {
      if (exception instanceof StumpyException) {
        exceptionRestResult = convertException(exception);
      } else {
        // This section will extend for future business exception types.
      }
    }
    return exceptionRestResult;
  }

  /**
   * Handle Json Exceptions.
   * 
   * @param exception.
   * @return {@link ExceptionRestResult}.
   */
  protected ExceptionRestResult handle(MethodArgumentNotValidException exception) {
    LOG.error("MethodArgumentNotValidException occured", exception);
    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    StumpyException stumpyException = new StumpyException("STUMPY004", getErrorMessages(fieldErrors));
    return convertException(stumpyException);
  }


  private String getErrorMessages(List<org.springframework.validation.FieldError> fieldErrors) {
    return fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
  }

}

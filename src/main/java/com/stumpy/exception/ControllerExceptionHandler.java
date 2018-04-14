package com.stumpy.exception;

import static com.stumpy.exception.util.ExceptionUtil.convertException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stumpy.exception.base.BaseException;
import com.stumpy.exception.model.ExceptionRestResult;

@ControllerAdvice
public class ControllerExceptionHandler extends BaseExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  /**
   * Exception handler for Exceptions.
   * 
   * @param exception.
   * @return {@link ResponseEntity}.
   */
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ResponseBody
  @ExceptionHandler({Exception.class})
  public ExceptionRestResult handleException(Exception exception) {
    // TODO : implement with baseExceptionHandler
    return new ExceptionRestResult();
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

    LOG.error("An error occured", exception);

    ExceptionRestResult exceptionRestResult = null;

    if (exception instanceof BaseException) {
      if (exception instanceof StumpyException) {
        exceptionRestResult = convertException(exception);
      } else {
        // This section will extend for future exception types.
      }
    }

    return exceptionRestResult;
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
  public ExceptionRestResult methodArgumentNotValidException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    StumpyException stumpyException = new StumpyException("STUMPY004", getErrorMessages(fieldErrors));
    return convertException(stumpyException);
  }

  private String getErrorMessages(List<org.springframework.validation.FieldError> fieldErrors) {
    return fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
  }

}

package com.stumpy.exception;

import com.stumpy.exception.base.BaseException;

public class StumpyException extends BaseException {

  private static final long serialVersionUID = -6981627382670060607L;

  public StumpyException(String code, Object... parameters) {
    super(code, parameters);
  }

}

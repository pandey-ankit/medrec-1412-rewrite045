package com.oracle.medrec.common.core;

import java.io.Serial;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class SystemException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public SystemException() {
  }

  public SystemException(String message) {
    super(message);
  }

  public SystemException(String message, Throwable cause) {
    super(message, cause);
  }

  public SystemException(Throwable cause) {
    super(cause);
  }
}

package com.oracle.medrec;

import java.io.Serial;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class MedRecSystemException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public MedRecSystemException() {
    super();
  }

  public MedRecSystemException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public MedRecSystemException(String message) {
    super(message);
  }

  public MedRecSystemException(Throwable throwable) {
    super(throwable);
  }
}

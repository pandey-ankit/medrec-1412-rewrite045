package com.oracle.medrec;

import java.io.Serial;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class MedRecException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public MedRecException() {
    super();
  }

  public MedRecException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public MedRecException(String message) {
    super(message);
  }

  public MedRecException(Throwable throwable) {
    super(throwable);
  }
}

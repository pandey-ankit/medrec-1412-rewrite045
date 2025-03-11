package com.oracle.medrec.service;

import com.oracle.medrec.MedRecException;
import java.io.Serial;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class DuplicateSsnException extends MedRecException {

  @Serial
  private static final long serialVersionUID = 1L;

  private String ssn;

  public DuplicateSsnException(String ssn) {
    super("The ssn '" + ssn + "' already exists");
    this.ssn = ssn;
  }

  public String getSsn() {
    return ssn;
  }
}

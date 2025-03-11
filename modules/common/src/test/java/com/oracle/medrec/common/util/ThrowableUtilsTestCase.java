package com.oracle.medrec.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * {@link ThrowableUtils} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 13, 2007
 */
public class ThrowableUtilsTestCase {

  @Test
  public void testGetStackTrace() {
    Exception ex = new Exception();
    assertNotNull(ThrowableUtils.getStackTrace(ex));
  }

  @Test
  @DisplayName("throws NullPointerException")
  public void testGetStackTraceWithNullException() {
    assertThrows(NullPointerException.class, () -> ThrowableUtils.getStackTrace(null));
  }
}

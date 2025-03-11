package com.oracle.medrec.common.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * {@link StringUtils} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 13, 2007
 */
public class StringUtilsTestCase {
  
  @Test
  public void isEmptyAndIsNotEmpty() {
    assertTrue(StringUtils.isEmpty(null));
    assertTrue(StringUtils.isEmpty(""));
    assertFalse(StringUtils.isEmpty("foo"));

    assertFalse(StringUtils.isNotEmpty(null));
    assertFalse(StringUtils.isNotEmpty(""));
    assertTrue(StringUtils.isNotEmpty("foo"));
  }
}

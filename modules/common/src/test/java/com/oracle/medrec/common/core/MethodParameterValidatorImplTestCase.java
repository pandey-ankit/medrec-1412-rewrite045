package com.oracle.medrec.common.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class MethodParameterValidatorImplTestCase {

  private MethodParameterValidator methodParameterValidator = new MethodParameterValidatorImpl();

  @Test
  public void validateObjectParameter() throws NoSuchMethodException {
    try {
      methodParameterValidator.validateParameters(String.class.getMethod("equals", Object.class), new Object[]{null});
    } catch (IllegalArgumentException e) {
      String message = "The number 1 parameter of type 'java.lang.Object' in method 'java.lang.String.equals()' is " +
          "null";
      assertEquals(message, e.getMessage());
    }

    try {
      methodParameterValidator.validateParameters(String.class.getMethod("equals", Object.class), new Object[]{"a"});
    } catch (IllegalArgumentException e) {
      fail(e);
    }
  }

  @Test
  public void validatePrimitiveParameter() throws NoSuchMethodException {
    try {
      methodParameterValidator.validateParameters(Object.class.getMethod("wait", Long.TYPE), new Object[]{0});
    } catch (IllegalArgumentException e) {
      fail(e);
    }
  }

  @Test
  public void validateNullableParameter() throws NoSuchMethodException {
    try {
      methodParameterValidator.validateParameters(Foo.class.getMethod("foo", String.class, String.class),
          new Object[]{"", null});
    } catch (IllegalArgumentException e) {
      fail(e);
    }

    try {
      methodParameterValidator.validateParameters(Foo.class.getMethod("foo", String.class, String.class),
          new Object[]{null, ""});
      fail("Fail the case");
    } catch (IllegalArgumentException e) {
    }
  }

  public static class Foo {
    public void foo(String s1, @Nullable String s2) {
    }
  }
}

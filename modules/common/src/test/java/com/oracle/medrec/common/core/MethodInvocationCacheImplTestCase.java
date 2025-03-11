package com.oracle.medrec.common.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class MethodInvocationCacheImplTestCase {

  private MethodInvocationCache methodInvocationCache;
  private String str = "aaa";

  @BeforeEach
  public void setup() throws Exception {
    methodInvocationCache = new MethodInvocationCacheImpl();
    ((MethodInvocationCacheImpl) methodInvocationCache).initDefaultCache();
    methodInvocationCache.addResult(String.class.getMethod("hashCode"), str.hashCode(), new Object[0]);
    methodInvocationCache.addResult(String.class.getMethod("startsWith", String.class), str.startsWith("a"),
        new Object[]{"a"});
  }

  @Test
  public void findResult() throws NoSuchMethodException {
    try {
      int hashCode = (Integer) methodInvocationCache.findResult(String.class.getMethod("hashCode"), new Object[0]);
      assertEquals(str.hashCode(), hashCode);
    } catch (ResultNotCachedException e) {
      fail(e);
    }
    try {
      boolean result = (Boolean) methodInvocationCache.findResult(String.class.getMethod("startsWith", String.class),
          new Object[]{"a"});
      assertEquals(str.startsWith("a"), result);
    } catch (ResultNotCachedException e) {
      fail(e);
    }
  }

  @Test
  public void findNonExistingResult() throws NoSuchMethodException {
    try {
      methodInvocationCache.findResult(String.class.getMethod("endsWith", String.class), new Object[]{"a"});
      fail("Fail the case");
    } catch (ResultNotCachedException e) {
    }
    try {
      methodInvocationCache.findResult(String.class.getMethod("startsWith", String.class), new Object[]{"b"});
      fail("Fail the case");
    } catch (ResultNotCachedException e) {
    }
  }

  @Test
  public void invalidateResultsByMethod() throws NoSuchMethodException {
    methodInvocationCache.invalidateResultsByMethod(String.class.getMethod("startsWith", String.class));

    try {
      methodInvocationCache.findResult(String.class.getMethod("startsWith", String.class), new Object[]{"a"});
      fail("Fail the case");
    } catch (ResultNotCachedException e) {
    }

    try {
      methodInvocationCache.findResult(String.class.getMethod("hashCode"), new Object[0]);
    } catch (ResultNotCachedException e) {
      fail(e);
    }
  }

  @Test
  public void invalidateAllResults() throws NoSuchMethodException {
    methodInvocationCache.invalidateAllResults();

    try {
      methodInvocationCache.findResult(String.class.getMethod("hashCode"), null);
      fail("Fail the case");
    } catch (ResultNotCachedException e) {
    }

    try {
      methodInvocationCache.findResult(String.class.getMethod("startsWith", String.class), new Object[]{"a"});
      fail("Fail the case");
    } catch (ResultNotCachedException e) {
    }
  }
}

package com.oracle.medrec.common.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * {@link ClassUtils} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 13, 2007
 */
public class ClassUtilsTestCase {

  @Test
  @DisplayName("throws ClassCastException")
  public void cast() {
    Object obj = new Child();
    ClassUtils.cast(Parent.class, obj);
    ClassUtils.cast(Child.class, obj);
    assertThrows(ClassCastException.class, () -> ClassUtils.cast(String.class, obj));
  }

  @Test
  @DisplayName("throws Exception")
  public void instantiateClass() {
    assertNotNull(ClassUtils.instantiateClass(Child.class));

    assertThrows(Exception.class, () -> ClassUtils.instantiateClass(Parent.class));
    assertThrows(Exception.class, () -> ClassUtils.instantiateClass(AnotherChild.class));
  }

  public static interface Parent {
  }

  public static class Child implements Parent {
  }

  public static class AnotherChild implements Parent {
    private final String name;

    public AnotherChild(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }
}

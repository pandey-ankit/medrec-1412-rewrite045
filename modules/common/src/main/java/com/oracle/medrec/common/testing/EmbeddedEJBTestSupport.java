package com.oracle.medrec.common.testing;

import java.util.HashMap;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

/**
 * EJB test base class.
 *
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class EmbeddedEJBTestSupport {

  private static EJBContainer ec;
  private static Context ctx;

  @SuppressWarnings({"rawtypes", "unchecked"})
  @BeforeAll
  public static synchronized void initContainer() {
    if (ec == null || ctx == null) {
      HashMap map = new HashMap();
      String[] modules = {"test-classes", "classes", "common"};
      map.put(EJBContainer.MODULES, modules);
      map.put(EJBContainer.APP_NAME, "medrec");
      ec = EJBContainer.createEJBContainer(map);
      ctx = ec.getContext();
    }
  }

  @AfterAll
  public static void cleanup() {
    if (ec != null) {
      ec.close();
    }
  }

  protected static Object getBean(String name) throws NamingException {
    return ctx.lookup("java:global/medrec/" + name);
  }

}

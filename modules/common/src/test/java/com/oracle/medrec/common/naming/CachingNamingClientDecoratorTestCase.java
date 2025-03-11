package com.oracle.medrec.common.naming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * {@link CachingNamingClientDecorator} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 18, 2007
 */
public class CachingNamingClientDecoratorTestCase {
  
  @Test
  public void lookup() {
    NamingClient namingClient = createMock(NamingClient.class);
    CachingNamingClientDecorator decorator = new CachingNamingClientDecorator();
    decorator.setNamingClient(namingClient);

    namingClient.lookup(String.class, "foo");
    expectLastCall().andReturn("foo");

    replay(namingClient);
    assertEquals("foo", decorator.lookup(String.class, "foo"));
    verify(namingClient);
  }
}

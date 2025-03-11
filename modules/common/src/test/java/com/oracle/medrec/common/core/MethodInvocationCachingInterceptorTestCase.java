package com.oracle.medrec.common.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * {@link MethodInvocationCachingInterceptor} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class MethodInvocationCachingInterceptorTestCase {

  private final static String result = "Result";
  private MethodInvocationCache cache;
  private InvocationContext ctx;
  private MethodInvocationCachingInterceptor interceptor;
  
  @BeforeEach
  public void setUp() {
    cache = createMock(MethodInvocationCache.class);
    ctx = new MockInvocationContext(result);
    interceptor = new MethodInvocationCachingInterceptor();
    interceptor.setMethodReturnValueCache(cache);
    cache.isCacheEffective();
    expectLastCall().andReturn(true);
  }

  @Test
  public void checkInCacheWithResult() throws Exception {
    cache.findResult((Method) anyObject(), (Object[]) anyObject());
    expectLastCall().andReturn(result);

    replay(cache);
    assertSame(result, interceptor.checkInCache(ctx));
    verify(cache);
  }

  @Test
  public void checkInCacheWithoutResult() throws Exception {
    cache.findResult((Method) anyObject(), (Object[]) anyObject());
    expectLastCall().andThrow(new ResultNotCachedException());
    cache.addResult((Method) anyObject(), eq(result), (Object[]) anyObject());
    expectLastCall().once();

    replay(cache);
    assertSame(result, interceptor.checkInCache(ctx));
    verify(cache);
  }


}

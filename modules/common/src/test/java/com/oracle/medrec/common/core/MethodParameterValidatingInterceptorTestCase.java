package com.oracle.medrec.common.core;

import org.junit.jupiter.api.Test;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

import static org.easymock.EasyMock.*;

/**
 * {@link MethodParameterValidatingInterceptor} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class MethodParameterValidatingInterceptorTestCase {

  private final static String result = "Result";
  
  @Test
  public void testValidateParameters() throws Exception {
    MethodParameterValidator mpv = createMock(MethodParameterValidator.class);
    InvocationContext ctx = new MockInvocationContext(result);

    mpv.validateParameters((Method) anyObject(), (Object[]) anyObject());
    expectLastCall().once();

    replay(mpv);
    MethodParameterValidatingInterceptor impl = new MethodParameterValidatingInterceptor();
    impl.setMethodParameterValidator(mpv);
    impl.validateParameters(ctx);
    verify(mpv);
  }
}

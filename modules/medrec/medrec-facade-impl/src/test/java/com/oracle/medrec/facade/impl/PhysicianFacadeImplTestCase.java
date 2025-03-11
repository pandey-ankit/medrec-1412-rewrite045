package com.oracle.medrec.facade.impl;

import com.oracle.medrec.service.PhysicianService;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link PhysicianFacadeImpl} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class PhysicianFacadeImplTestCase {

  private PhysicianService ps;
  private PhysicianFacadeImpl impl;
  
  @BeforeEach
  public void setUp() {
    ps = createMock(PhysicianService.class);
    impl = new PhysicianFacadeImpl();
    impl.setPhysicianService(ps);
  }

  @Test
  public void testAuthenticatePhysician() {
    ps.authenticatePhysician("u1", "p1");
    expectLastCall().andReturn(true);
    ps.authenticatePhysician("u2", "p2");
    expectLastCall().andReturn(false);

    replay(ps);
    assertTrue(impl.authenticatePhysician("u1", "p1"));
    assertFalse(impl.authenticatePhysician("u2", "p2"));
    verify(ps);
  }
}

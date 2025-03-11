package com.oracle.medrec.service.impl;

import com.oracle.medrec.common.testing.EntityRepositoryTestCaseSupport;
import com.oracle.medrec.model.Physician;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@link PhysicianServiceImpl} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class PhysicianServiceImplTestCase extends EntityRepositoryTestCaseSupport<PhysicianServiceImpl> {

  private Physician physician;

  @BeforeEach
  public void before() {
    physician = EntitiesPreparation.createPhysician();
    service.getEntityManager().persist(physician);
    service.initBuilder();
  }

  @AfterEach
  public void after() {
    service.getEntityManager().remove(physician);
  }

  @Test
  public void testAuthenticateAndReturnAdministrator() {
    Physician user = service.authenticateAndReturnPhysician(physician.getUsername(), physician.getPassword());
    assertNotNull(user);

    user = service.authenticateAndReturnPhysician("bogus", physician.getPassword());
    assertNull(user);

    user = service.authenticateAndReturnPhysician(physician.getUsername(), "bogus");
    assertNull(user);
  }
}

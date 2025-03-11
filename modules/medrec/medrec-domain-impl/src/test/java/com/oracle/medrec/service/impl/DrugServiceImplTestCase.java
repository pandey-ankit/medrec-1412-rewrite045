package com.oracle.medrec.service.impl;

import com.oracle.medrec.common.testing.EntityRepositoryTestCaseSupport;
import com.oracle.medrec.model.Drug;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link com.oracle.medrec.service.impl.RecordServiceImpl} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class DrugServiceImplTestCase extends EntityRepositoryTestCaseSupport<DrugServiceImpl> {

  private Drug drug, drug1;

  @BeforeEach
  public void before() {
    drug = EntitiesPreparation.createDrug();
    service.getEntityManager().persist(drug);

    drug1 = EntitiesPreparation.createDrug();
    service.getEntityManager().persist(drug1);
  }

  @AfterEach
  public void after() {
    service.getEntityManager().remove(drug);
    service.getEntityManager().remove(drug1);
  }

  @Test
  public void testGetAllDrugs() {
    List<Drug> list = service.getAllDrugsInfo();
    assertEquals(list.size(), 2);
  }

}

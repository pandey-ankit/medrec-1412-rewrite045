package com.oracle.medrec.facade.impl;

import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import com.oracle.medrec.model.Physician;
import com.oracle.medrec.model.Record;
import com.oracle.medrec.service.RecordService;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

/**
 * {@link RecordFacadeImpl} test case.
 *
 * @author Copyright (c) 2007, 2019, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class RecordFacadeImplTestCase {

  private RecordService rs;
  private RecordFacadeImpl impl;

  @BeforeEach
  public void setUp() {
    rs = createMock(RecordService.class);
    impl = new RecordFacadeImpl();
    impl.setRecordService(rs);
  }

  @Test
  public void testCreateRecord() {
    rs.createRecord(isA(Record.class), eq(2L), eq(1L));
    expectLastCall().once();
    replay(rs);
    RecordToCreate rtc = new RecordToCreate();
    rtc.setPhysicianId(2L);
    rtc.setPatientId(1L);
    impl.createRecord(rtc);
    verify(rs);
  }

  @Test
  public void testGetRecordSummaryByPatientId() {
    Record r1 = new Record();
    Physician phy1 = new Physician();
    phy1.setUsername("test1@test.com");
    phy1.setPassword("weblogic");
    phy1.setEmail("test1@test.com");
    r1.setPhysician(phy1);
    Record r2 = new Record();
    Physician phy2 = new Physician();
    phy2.setUsername("test2@test.com");
    phy2.setPassword("weblogic");
    phy2.setEmail("test2@test.com");
    r2.setPhysician(phy2);
    List<Record> records = Arrays.asList(r1, r2);
    rs.getRecordsByPatientId(1L);
    expectLastCall().andReturn(records);
    replay(rs);
    RecordSummary sum = impl.getRecordSummaryByPatientId(1L);
    assertNotNull(sum);
    assertEquals(sum.getPatientId(), (Long) 1L);
    verify(rs);
  }

  @Test
  @DisplayName("throws AssertionError when verify")
  public void testGetRecordDetailWithInvalidId() {
    rs.getRecord(1L);
    expectLastCall().andReturn(null);

    replay(rs);
    assertThrows(AssertionError.class, () -> impl.getRecordDetail(1L));
    verify(rs);
  }

  @Test
  public void testGetRecordDetail() {
    Record record = new Record();
    rs.getRecord(1L);
    expectLastCall().andReturn(record);

    replay(rs);
    RecordDetail rd = impl.getRecordDetail(1L);
    assertNotNull(rd);
    verify(rs);
  }
}

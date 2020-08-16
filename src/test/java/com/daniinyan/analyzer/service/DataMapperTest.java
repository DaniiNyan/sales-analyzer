package com.daniinyan.analyzer.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DataMapperTest {

  private DataMapper dataMapper;

  @Before
  public void setUp() {
    dataMapper = new DataMapper();
  }

  @Test
  public void shouldMapToId() {
    String salesmanData = "001ç1234567891234çDianaç3800";
    String customerData = "002ç2345675434544345çJose da SilvaçRural";
    String saleData = "003ç01ç[1-10-2.5,2-10-1.50,3-15-100]çDiana";

    assertEquals("001", dataMapper.toId(salesmanData));
    assertEquals("002", dataMapper.toId(customerData));
    assertEquals("003", dataMapper.toId(saleData));
  }

}

package com.daniinyan.analyzer.service;

import static org.junit.Assert.assertEquals;

import com.daniinyan.analyzer.domain.Item;
import com.daniinyan.analyzer.domain.Sale;
import java.util.List;
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

  @Test
  public void shouldMapToSale() {
    String saleData = "003ç01ç[1-10-2.5,2-10-1.50,3-15-100]çDiana";
    Sale result = dataMapper.toSale(saleData);

    assertEquals("01", result.getId());
    assertEquals(3, result.getItems().size());
    assertEquals("Diana", result.getSalesmanName());
  }

  @Test
  public void shouldMapItems() {
    String itemsData = "[1-10-2.5,2-10-1.50,3-15-100]";
    List<Item> result = dataMapper.toItems(itemsData);

    assertEquals(3, result.size());

    assertEquals("1", result.get(0).getId());
    assertEquals(10, result.get(0).getQuantity());
    assertEquals(new Double(2.5), result.get(0).getPrice());

    assertEquals("2", result.get(1).getId());
    assertEquals(10, result.get(1).getQuantity());
    assertEquals(new Double(1.5), result.get(1).getPrice());

    assertEquals("3", result.get(2).getId());
    assertEquals(15, result.get(2).getQuantity());
    assertEquals(new Double(100), result.get(2).getPrice());
  }

}

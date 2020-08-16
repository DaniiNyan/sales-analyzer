package com.daniinyan.analyzer.domain;

import java.util.List;

public class Sale {
  private String id;
  private List<Item> items;
  private String salesmanName;

  public Double getTotal() {
    return items
        .stream()
        .mapToDouble(item -> item.getQuantity() * item.getPrice())
        .sum();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public String getSalesmanName() {
    return salesmanName;
  }

  public void setSalesmanName(String salesmanName) {
    this.salesmanName = salesmanName;
  }

  @Override
  public String toString() {
    return "{ sale_id=" + id +
        ", sale_items=" + items +
        ", salesman_name=" + salesmanName + " }";
  }
}

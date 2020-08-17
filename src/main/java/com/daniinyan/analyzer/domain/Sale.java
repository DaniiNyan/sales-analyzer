package com.daniinyan.analyzer.domain;

import java.util.List;

public class Sale {
  private String id;
  private List<Item> items;
  private String sellerName;

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

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
}

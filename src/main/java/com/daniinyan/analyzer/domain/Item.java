package com.daniinyan.analyzer.domain;

public class Item {
  private String id;
  private int quantity;
  private Double price;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

}

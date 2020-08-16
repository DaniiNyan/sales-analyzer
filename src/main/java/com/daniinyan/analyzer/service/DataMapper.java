package com.daniinyan.analyzer.service;

public class DataMapper {

  public String toId(String data) {
    String[] splitData = data.split(getDelimiter(data));
    return splitData[0];
  }

  private String getDelimiter(String data) {
    return data.substring(3, 4);
  }
}

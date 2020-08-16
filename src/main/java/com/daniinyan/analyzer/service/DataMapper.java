package com.daniinyan.analyzer.service;

public class DataMapper {

  public static String getDelimiter(String data) {
    return data.substring(3, 4);
  }

  public String toId(String data) {
    String[] splitData = data.split(getDelimiter(data));
    return splitData[0];
  }
}

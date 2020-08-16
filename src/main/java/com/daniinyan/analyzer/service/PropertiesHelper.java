package com.daniinyan.analyzer.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

  private static final String PATH_TO_PROPERTIES_FILE = "src/main/resources/application.properties";
  private static final String PROPERTY_PATH_TO_DATA = "path.data";
  private static final String PROPERTY_PATH_TO_REPORT = "path.report";
  
  private static Properties props;
  // todo: logger?

  public PropertiesHelper() {
    props = new Properties();
    loadProperties();
  }
  
  public static String getPathToData() {
   return props.getProperty(PROPERTY_PATH_TO_DATA);
  }

  public static String getPathToReport() {
    return props.getProperty(PROPERTY_PATH_TO_REPORT);
  }

  private void loadProperties() {
    try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES_FILE)) {
      Properties props = new Properties();
      props.load(input);
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}

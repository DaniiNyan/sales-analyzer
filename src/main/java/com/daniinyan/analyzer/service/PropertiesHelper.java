package com.daniinyan.analyzer.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesHelper {

  private static final Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);

  private static final String PATH_TO_PROPERTIES_FILE = "src/main/resources/application.properties";
  private static final String PROPERTY_PATH_TO_DATA = "path.data";
  private static final String PROPERTY_PATH_TO_REPORT = "path.report";
  private static final Properties props = new Properties();;

  public static String getPathToData() {
   return props.getProperty(PROPERTY_PATH_TO_DATA);
  }

  public static String getPathToReport() {
    return props.getProperty(PROPERTY_PATH_TO_REPORT);
  }

  public static void loadProperties() {
    try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES_FILE)) {
      props.load(input);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}

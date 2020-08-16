package com.daniinyan.analyzer;

import com.daniinyan.analyzer.service.FolderWatcher;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

  private static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";

  public static void main(String[] args) {

    try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
      Properties props = new Properties();
      props.load(input);

      FolderWatcher folderWatcher = new FolderWatcher(props.getProperty("watcher.path"));
      folderWatcher.run();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

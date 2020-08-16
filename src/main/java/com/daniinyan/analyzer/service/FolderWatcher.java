package com.daniinyan.analyzer.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

public class FolderWatcher extends Thread {

  private static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
  public String pathToWatch;

  public FolderWatcher() {
    getPathToWatchFromPropertiesFile();
  }

  @Override
  public void run() {
    while (true) {
      watchInputFolder();

      try {
        Thread.sleep(100);
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  private void watchInputFolder() {
    try {
      WatchService watchService = FileSystems.getDefault().newWatchService();

      Path path = Paths.get(pathToWatch);
      path.register(watchService,
          StandardWatchEventKinds.ENTRY_CREATE,
          StandardWatchEventKinds.ENTRY_DELETE,
          StandardWatchEventKinds.ENTRY_MODIFY);

      WatchKey key;
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          System.out.println("FOLDER MODIFIED: " + event.kind());
        }
        key.reset();
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void getPathToWatchFromPropertiesFile() {
    try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
      Properties props = new Properties();
      props.load(input);
      pathToWatch = props.getProperty("watcher.path");
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}

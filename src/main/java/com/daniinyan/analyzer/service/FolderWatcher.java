package com.daniinyan.analyzer.service;

import com.daniinyan.analyzer.dao.FilesDAO;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FolderWatcher extends Thread {

  private static final Logger logger = LoggerFactory.getLogger(FolderWatcher.class);

  private final String pathToWatch;
  private final DataService dataService;

  public FolderWatcher() {
    PropertiesHelper.loadProperties();
    pathToWatch = PropertiesHelper.getPathToData();
    dataService = new DataService(new FilesDAO());
  }

  @Override
  public void run() {
    while (true) {
      watchInputFolder();

      try {
        Thread.sleep(100);
      } catch (Exception e) {
        logger.error(e.getMessage());
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
          dataService.updateReport();
        }
        key.reset();
      }

    } catch (IOException | InterruptedException e) {
      logger.error(e.getMessage());
    }
  }
}

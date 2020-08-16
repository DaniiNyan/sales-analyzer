package com.daniinyan.analyzer;

import com.daniinyan.analyzer.service.FolderWatcher;

public class Application {

  public static void main(String[] args) {
    FolderWatcher folderWatcher = new FolderWatcher();
    folderWatcher.run();
  }
}

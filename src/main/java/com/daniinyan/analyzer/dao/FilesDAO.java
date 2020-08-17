package com.daniinyan.analyzer.dao;

import com.daniinyan.analyzer.service.PropertiesHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

public class FilesDAO {

  private static final Logger logger = Logger.getLogger(FilesDAO.class);
  private final String pathToData = PropertiesHelper.getPathToData();

  public List<String> getData() {
    List<String> dataFilesPaths = getDataFilesPaths();
    List<String> dataFromAllFiles = new ArrayList<>();

    dataFilesPaths
        .forEach(filePath ->
            dataFromAllFiles.addAll(getLinesFromFile(filePath)));

    return dataFromAllFiles;
  }

  public void writeReport(List<String> reportLines) {
    try {
      Path reportPath = Paths.get(PropertiesHelper.getPathToReport());
      Files.deleteIfExists(reportPath);
      Files.createFile(reportPath);
      Files.write(reportPath, reportLines, StandardOpenOption.APPEND);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

  public void updateReportField(String fieldName, String value) {
    List<String> updatedLines = getLinesFromFile(PropertiesHelper.getPathToReport())
        .stream()
        .map(line -> line.contains(fieldName) ? (fieldName + value) : line)
        .collect(Collectors.toList());

    writeReport(updatedLines);
  }

  private List<String> getDataFilesPaths() {
    List<String> dataFiles = new ArrayList<>();
    try (Stream<Path> paths = Files.list(Paths.get(pathToData))) {
      dataFiles = paths
          .map(Path::toString)
          .collect(Collectors.toList());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return dataFiles;
  }

  private List<String> getLinesFromFile(String pathToFile) {
    List<String> fileLines = new ArrayList<>();
    try {
      fileLines = Files.readAllLines(Paths.get(pathToFile));
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return fileLines;
  }
}

package org.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

public interface FileService {
    File openDirectory(File currentDirectory, String nameDirectory);

    List<File> listFiles(File currentDir);

    void createFile(File currentDir, String fileName);

    void createDirectory(File currentDir, String directoryName);

    void delete(File currentDir, String fileName);

    void moveTo(File currentDir, String fileName, String to);

    void searchInside(File currentDir, String fileName);

    void searchFileByText(File currentDir, String text);
}

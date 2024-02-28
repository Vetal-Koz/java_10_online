package org.example.service.impl;

import org.example.service.FileService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    public File openDirectory(File currentDirectory, String nameDirectory) {
        if (nameDirectory.contains("/")) {
            return new File(nameDirectory);
        } else {
            File newDirectory = new File(currentDirectory.getAbsolutePath() + "/" + nameDirectory);
            return newDirectory;
        }
    }

    @Override
    public List<File> listFiles(File currentDir) {
        if (currentDir.isDirectory()) {
            List<File> files = List.of(currentDir.listFiles());
            return files;
        } else {
            throw new RuntimeException("This file is not a directory");
        }
    }

    @Override
    public void createFile(File currentDir, String fileName) {
        File newFile;
        if (fileName.contains("/")) {
            newFile = new File(fileName);
        } else {
            newFile = new File(currentDir.getAbsolutePath() + "/" + fileName);
        }
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void createDirectory(File currentDir, String directoryName) {
        File newFile;
        if (directoryName.contains("/")) {
            newFile = new File(directoryName);
        } else {
            newFile = new File(currentDir.getAbsolutePath() + "/" + directoryName);
        }
        newFile.mkdir();
    }

    @Override
    public void delete(File currentDir, String fileName) {
        File newFile;
        if (fileName.contains("/")) {
            newFile = new File(fileName);
        } else {
            newFile = new File(currentDir.getAbsolutePath() + "/" + fileName);
        }
        if (newFile.isDirectory()) {
            deleteDirectory(newFile);
        } else {
            newFile.delete();
        }
    }

    @Override
    public void moveTo(File currentDir, String fileName, String to) {
        File newFile;
        if (fileName.contains("/")) {
            newFile = new File(fileName);
        } else {
            newFile = new File(currentDir.getAbsolutePath() + "/" + fileName);
        }
        newFile.renameTo(new File(to + "/" + fileName));
    }

    @Override
    public void searchInside(File currentDir, String fileName) {
        if (currentDir.isDirectory()) {
            for (File element : currentDir.listFiles()) {
                if (element.getAbsolutePath().toLowerCase().contains("fileName")) {
                    System.out.println(element.getAbsolutePath());
                } else {

                }
            }
        } else {
            System.out.println(currentDir.getAbsolutePath() + "\t file");
        }
    }

    @Override
    public void searchFileByText(File currentDir, String text) {
        if (currentDir.isDirectory()) {
            for (File element : currentDir.listFiles()) {
                if (element.getName().toLowerCase().contains("text")) {
                    if (element.isDirectory()) {
                        System.out.println(element.getAbsolutePath() + " contains folder");
                    } else {
                        System.out.println(element.getAbsolutePath() + " contains");
                    }
                }
                if (element.isDirectory()) {
                    searchFileByText(element, text);
                }

            }
        }
    }

    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            if (Arrays.stream(directory.listFiles()).toList().isEmpty()) {
                directory.delete();
            } else {
                for (File file : directory.listFiles()) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }
}

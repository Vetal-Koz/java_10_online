package org.example.controller;

import org.example.service.impl.FileServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileController {
    private final FileServiceImpl fileService = new FileServiceImpl();
    private File currentDirectory = new File(System.getProperty("user.home"));

    public void start() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String position = "";
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                menu();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want to open a another directory please enter 1");
        System.out.println("If you want to create a directory please enter 2");
        System.out.println("If you want to create a new file please enter 3");
        System.out.println("if you want to see all files and directories in such directory please enter 4");
        System.out.println("if you want to move file or directory to another directory please enter 5");
        System.out.println("if you want to find files or directories in such directory please enter 6");
        System.out.println("if you want to find text in all files and directories in such directories in such directory enter 7");
        System.out.println("if you want to delete file or directory enter 8");
        System.out.println("If you want exit please enter exit");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> openDirectory(this.currentDirectory, reader);
            case "2" -> createNewDirectory(this.currentDirectory, reader);
            case "3" -> createNewFile(this.currentDirectory, reader);
            case "4" -> readDirectory(this.currentDirectory);
            case "5" -> moveTo(this.currentDirectory, reader);
            case "6" -> searchInside(this.currentDirectory, reader);
            case "7" -> searchFileByText(this.currentDirectory, reader);
            case "8" -> deleteFile(this.currentDirectory, reader);
            case "exit" -> System.exit(0);
        }
    }

    public void openDirectory(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter directory name");
        try {
            String nameDirectory = reader.readLine();
            this.currentDirectory = fileService.openDirectory(currentDirectory, nameDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewFile(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter file name");
        try {
            String fileName = reader.readLine();
            fileService.createFile(currentDirectory, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createNewDirectory(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter directory name");
        try {
            String directoryName = reader.readLine();
            fileService.createDirectory(currentDirectory, directoryName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDirectory(File currentDirectory) {
        List<File> files = fileService.listFiles(currentDirectory);
        for (File file : files) {
            System.out.println(file);
        }
    }

    public void moveTo(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter file or directory name which you want to move");
        try {
            String fileName = reader.readLine();
            System.out.println("Please enter path to place where you want to put your file");
            String newPath = reader.readLine();
            fileService.moveTo(currentDirectory, fileName, newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchInside(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter file name which you want to search in such directory");
        try {
            String fileName = reader.readLine();
            fileService.searchInside(currentDirectory, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchFileByText(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter text which you want to find");
        try {
            String text = reader.readLine();
            fileService.searchFileByText(currentDirectory, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(File currentDirectory, BufferedReader reader) {
        System.out.println("Please enter file or directory name which you want to delete");
        try {
            String fileName = reader.readLine();
            fileService.delete(currentDirectory, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

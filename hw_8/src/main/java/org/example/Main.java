package org.example;

import org.example.controller.FileController;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController fileController = new FileController();
        fileController.start();
    }

}
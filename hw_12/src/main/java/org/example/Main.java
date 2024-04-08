package org.example;


import org.example.config.ObjectFactory;

public class Main {
    public static void main(String[] args) {
        ObjectFactory.getInstance().initObjectFactory();
        StartApp startApp = new StartApp();
        startApp.run();
    }
}
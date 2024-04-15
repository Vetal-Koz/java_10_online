package org.example.controller;

import org.example.config.ObjectFactory;
import org.example.entity.User;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserController {
    private UserService userService = ObjectFactory.getInstance().getService(UserService.class);

    public void start(BufferedReader reader) throws IOException {
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create users please enter 1");
        System.out.println("If you want show all users please enter 2");
        System.out.println("If you want show user by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want show whole user's balance please enter 6");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll(reader);
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "6" -> showUserBalance(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        System.out.println("Please enter name");
        String name = reader.readLine();
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setName(name);
        userService.update(user);
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String name = reader.readLine();
        User user = new User();
        user.setName(name);
        userService.create(user);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();

        User user = userService.findById(Long.parseLong(idString));
        System.out.println("User = " + user);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        userService.delete(Long.parseLong(idString));
    }

    private void readAll(BufferedReader reader) throws IOException {
        userService.findAll().forEach(System.out::println);
    }

    private void showUserBalance(BufferedReader reader) throws IOException {
        System.out.println("Please enter user's id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Whole balance : " + userService.getWholeBalanceById(id));
    }


}

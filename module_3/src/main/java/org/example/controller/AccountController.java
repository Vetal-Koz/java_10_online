package org.example.controller;

import org.example.config.ObjectFactory;
import org.example.entity.Account;
import org.example.entity.User;
import org.example.service.AccountService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;

public class AccountController {
    private UserService userService = ObjectFactory.getInstance().getService(UserService.class);
    private AccountService accountService = ObjectFactory.getInstance().getService(AccountService.class);

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
        System.out.println("If you want create account please enter 1");
        System.out.println("If you want show all accounts please enter 2");
        System.out.println("If you want show account by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want show balance on account enter 6");
        System.out.println("If you want export account statement in csv file, please enter 7");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll(reader);
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "6" -> showBalance(reader);
            case "7" -> exportAccountStatement(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        System.out.println("Please enter name");
        String name = reader.readLine();
        System.out.println("Please enter user id");
        User user = userService.findById(Long.valueOf(id));
        Account account = new Account();
        account.setId(Long.valueOf(id));
        account.setName(name);
        account.setUser(user);
        accountService.update(account);
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String name = reader.readLine();
        Account account = new Account();
        account.setName(name);
        System.out.println("Please enter user id you want to connect with account");
        String userId = reader.readLine();
        User user = userService.findById(Long.valueOf(userId));
        account.setUser(user);
        accountService.create(account);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();

        Account account = accountService.findById(Long.parseLong(idString));
        System.out.println("Account = " + account);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        accountService.delete(Long.parseLong(idString));
    }

    private void readAll(BufferedReader reader) throws IOException {
        accountService.findAll().forEach(System.out::println);
    }

    private void showBalance(BufferedReader reader) throws IOException {
        System.out.println("Please enter account id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Balance on account : " + accountService.getAccountBalanceById(id));
    }

    private void exportAccountStatement(BufferedReader reader) throws IOException {
        System.out.println("Please enter account id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Please enter date from");
        String from = reader.readLine();
        Timestamp fromTimeStamp = Timestamp.valueOf(from);
        System.out.println("Please enter date to");
        String to = reader.readLine();
        Timestamp toTimeStamp = Timestamp.valueOf(from);
        accountService.exportAccountStatement(id, fromTimeStamp, toTimeStamp);
    }
}

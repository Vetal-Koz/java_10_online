package org.example.controller;

import org.example.config.ObjectFactory;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.entity.TransactionCategory;
import org.example.service.AccountService;
import org.example.service.TransactionCategoryService;
import org.example.service.TransactionService;

import java.io.BufferedReader;
import java.io.IOException;

public class TransactionController {
    private TransactionCategoryService transactionCategoryService = ObjectFactory.getInstance().getService(TransactionCategoryService.class);
    private AccountService accountService = ObjectFactory.getInstance().getService(AccountService.class);

    private TransactionService transactionService = ObjectFactory.getInstance().getService(TransactionService.class);

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
        System.out.println("If you want create transaction please enter 1");
        System.out.println("If you want show all transactions please enter 2");
        System.out.println("If you want show transaction by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want show transaction by account id please enter 5");
        System.out.println("If you want create transaction between accounts please enter 6");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> readByAccountId(reader);
            case "6" -> createTransactionBetweenAccounts(reader);

            case "10" -> throw new RuntimeException("exit");
        }
    }


    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter account id you want to connect transaction");
        Long accountId = Long.valueOf(reader.readLine());
        System.out.println("Please enter description");
        String description = reader.readLine();
        System.out.println("Please enter sum");
        Double sum = Double.valueOf(reader.readLine());
        System.out.println("Please select transaction category");
        System.out.println("if you want income transaction category, enter 1");
        System.out.println("if you want expense transaction category, enter 2");
        int category = Integer.parseInt(reader.readLine());
        TransactionCategory transactionCategory = null;
        switch (category) {
            case (1):
                transactionCategory = transactionCategoryService.findByCategory("Income");
                break;
            case (2):
                transactionCategory = transactionCategoryService.findByCategory("Expense");
                break;
        }

        Account account = accountService.findById(accountId);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDescription(description);
        transaction.setSum(sum);
        transaction.setTransactionCategory(transactionCategory);

        transactionService.create(transaction);
    }

    private void readAll() {
        for (Transaction transaction : transactionService.findAll()) {
            System.out.println(transaction);
        }
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter transaction id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println(transactionService.findById(id));
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter transaction id");
        Long id = Long.valueOf(reader.readLine());
        transactionService.delete(id);
    }

    private void readByAccountId(BufferedReader reader) throws IOException {
        System.out.println("Please enter account id");
        Long accountId = Long.valueOf(reader.readLine());
        Account account = accountService.findById(accountId);
        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }

    }

    private void createTransactionBetweenAccounts(BufferedReader reader) throws IOException {
        System.out.println("Please enter account id to which you to transfer money");
        Long accountIdTo = Long.valueOf(reader.readLine());
        System.out.println("Please enter account id from which you to transfer money");
        Long accountIdFrom = Long.valueOf(reader.readLine());
        Account accountTo = accountService.findById(accountIdTo);
        Account accountFrom = accountService.findById(accountIdFrom);
        System.out.println("Please enter sum of transaction");
        Double sum = Double.valueOf(reader.readLine());
        System.out.println("Please enter description");
        String description = reader.readLine();
        Transaction to = new Transaction();
        to.setAccount(accountTo);
        to.setDescription(description);
        to.setSum(sum);
        to.setTransactionCategory(transactionCategoryService.findByCategory("Income"));

        Transaction from = new Transaction();
        to.setAccount(accountFrom);
        to.setDescription(description);
        to.setSum(sum);
        to.setTransactionCategory(transactionCategoryService.findByCategory("Expense"));

        transactionService.createTransactionsBetweenAccounts(to, from);
    }
}

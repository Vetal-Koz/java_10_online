package org.example;


import org.example.config.ObjectFactory;
import org.example.entity.TransactionCategory;
import org.example.service.TransactionCategoryService;


public class Main {
    public static void main(String[] args) {
        ObjectFactory.getInstance().initObjectMap();
        TransactionCategoryService transactionCategoryService = ObjectFactory.getInstance().getService(TransactionCategoryService.class);
        if (transactionCategoryService.count() == 0) {
            TransactionCategory income = new TransactionCategory();
            income.setCategory("Income");
            TransactionCategory expense = new TransactionCategory();
            expense.setCategory("Expense");
            transactionCategoryService.create(income);
            transactionCategoryService.create(expense);
        }

        StartApp startApp = new StartApp();
        startApp.run();


    }
}
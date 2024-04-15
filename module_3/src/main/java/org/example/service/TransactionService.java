package org.example.service;

import org.example.entity.Transaction;

public interface TransactionService extends CrudService<Transaction> {
    void createTransactionsBetweenAccounts(Transaction to, Transaction from);
}

package org.example.dao;

import org.example.entity.Transaction;

public interface TransactionDao extends CrudDao<Transaction> {
    void createTransactionsBetweenAccounts(Transaction to, Transaction from);
}

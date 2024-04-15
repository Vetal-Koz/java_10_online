package org.example.service.impl;

import org.example.config.ObjectFactory;
import org.example.dao.TransactionDao;
import org.example.entity.Transaction;
import org.example.service.TransactionService;

import java.util.Collection;

public class TransactionServiceImpl implements TransactionService {
    TransactionDao transactionDao = ObjectFactory.getInstance().getService(TransactionDao.class);

    @Override
    public void create(Transaction entity) {
        if (entity.getSum() <= 0.0) {
            throw new RuntimeException("Transaction with sum less than 0, can't be created");
        }
        transactionDao.create(entity);

    }

    @Override
    public void update(Transaction entity) {
        if (entity.getSum() <= 0.0) {
            throw new RuntimeException("Transaction with sum less than 0, can't be updated");
        }
        transactionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        transactionDao.delete(id);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public long count() {
        return transactionDao.count();
    }

    @Override
    public void createTransactionsBetweenAccounts(Transaction to, Transaction from) {
        transactionDao.createTransactionsBetweenAccounts(to, from);
    }
}

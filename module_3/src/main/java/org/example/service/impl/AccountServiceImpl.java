package org.example.service.impl;

import org.example.config.ObjectFactory;
import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.example.service.AccountService;

import java.sql.Timestamp;
import java.util.Collection;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = ObjectFactory.getInstance().getService(AccountDao.class);

    @Override
    public Double getAccountBalanceById(Long id) {
        return accountDao.getAccountBalanceById(id);
    }

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void update(Account entity) {
        accountDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    @Override
    public Collection<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public long count() {
        return accountDao.count();
    }

    @Override
    public void exportAccountStatement(Long accountId, Timestamp from, Timestamp to) {
        accountDao.exportAccountStatement(accountId, from, to);
    }
}

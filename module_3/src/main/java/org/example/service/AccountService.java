package org.example.service;

import org.example.entity.Account;

import java.sql.Timestamp;

public interface AccountService extends CrudService<Account> {
    Double getAccountBalanceById(Long id);

    void exportAccountStatement(Long accountId, Timestamp from, Timestamp to);
}

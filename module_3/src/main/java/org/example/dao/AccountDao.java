package org.example.dao;

import org.example.entity.Account;

import java.sql.Timestamp;

public interface AccountDao extends CrudDao<Account> {
    Double getAccountBalanceById(Long id);

    void exportAccountStatement(Long accountId, Timestamp from, Timestamp to);
}

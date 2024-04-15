package org.example.dao;

import org.example.entity.TransactionCategory;

import java.util.Optional;

public interface TransactionCategoryDao extends CrudDao<TransactionCategory> {
    Optional<TransactionCategory> findByCategory(String category);
}

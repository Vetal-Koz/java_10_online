package org.example.service;

import org.example.entity.TransactionCategory;

public interface TransactionCategoryService extends CrudService<TransactionCategory> {
    TransactionCategory findByCategory(String category);
}

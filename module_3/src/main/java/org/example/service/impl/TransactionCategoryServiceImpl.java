package org.example.service.impl;

import org.example.config.ObjectFactory;
import org.example.dao.TransactionCategoryDao;
import org.example.entity.TransactionCategory;
import org.example.service.TransactionCategoryService;

import java.util.Collection;

public class TransactionCategoryServiceImpl implements TransactionCategoryService {
    private final TransactionCategoryDao transactionCategoryDao = ObjectFactory.getInstance().getService(TransactionCategoryDao.class);

    @Override
    public void create(TransactionCategory entity) {
        transactionCategoryDao.create(entity);
    }

    @Override
    public void update(TransactionCategory entity) {
        transactionCategoryDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        transactionCategoryDao.delete(id);
    }

    @Override
    public TransactionCategory findById(Long id) {
        return transactionCategoryDao.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    @Override
    public Collection<TransactionCategory> findAll() {
        return transactionCategoryDao.findAll();
    }

    @Override
    public long count() {
        return transactionCategoryDao.count();
    }

    @Override
    public TransactionCategory findByCategory(String category) {
        return transactionCategoryDao.findByCategory(category).orElseThrow(() -> new RuntimeException("Such category does not exist"));
    }
}

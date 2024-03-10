package org.example.dao.impl;

import org.example.dao.CrudDao;
import org.example.db.FileDb;
import org.example.entity.Car;
import org.example.factory.DBFactory;

import java.util.Collection;
import java.util.Optional;

public class CarDao implements CrudDao<Car> {

    private final FileDb<Car> fileDb = DBFactory.getInstance().getFileDb(this.getClass());

    @Override
    public void create(Car entity) {
        fileDb.create(entity);
    }

    @Override
    public void update(Car entity) {
        fileDb.update(entity);
    }

    @Override
    public void delete(Long id) {
        fileDb.delete(id);
    }

    @Override
    public Collection<Car> findAll() {
        return fileDb.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return fileDb.findById(id);
    }
}

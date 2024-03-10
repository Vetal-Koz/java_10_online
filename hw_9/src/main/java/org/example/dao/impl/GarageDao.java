package org.example.dao.impl;

import org.example.dao.CrudDao;
import org.example.db.FileDb;
import org.example.entity.Garage;
import org.example.factory.DBFactory;

import java.util.Collection;
import java.util.Optional;

public class GarageDao implements CrudDao<Garage> {

    private final FileDb<Garage> fileDb = DBFactory.getInstance().getFileDb(this.getClass());

    @Override
    public void create(Garage entity) {
        fileDb.create(entity);
    }

    @Override
    public void update(Garage entity) {
        fileDb.update(entity);
    }

    @Override
    public void delete(Long id) {
        fileDb.delete(id);
    }

    @Override
    public Collection<Garage> findAll() {
        return fileDb.findAll();
    }

    @Override
    public Optional<Garage> findById(Long id) {
        return fileDb.findById(id);
    }
}

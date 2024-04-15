package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.config.JpaConfig;
import org.example.config.ObjectFactory;
import org.example.dao.AccountDao;
import org.example.dao.TransactionDao;
import org.example.entity.Transaction;


import java.util.Collection;
import java.util.Optional;

public class TransactionDaoImpl implements TransactionDao {

    private final JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private final EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(Transaction entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public void update(Transaction entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createQuery("delete from Transaction t where t.id = :id ").
                    setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            if (entityTransaction != null){
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Transaction.class, id));
    }

    @Override
    public Collection<Transaction> findAll() {
        return entityManager.createQuery("select t from Transaction t").getResultList();
    }
    @Override
    public long count() {
        return 0;
    }

    @Override
    public void createTransactionsBetweenAccounts(Transaction to, Transaction from) {

        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(to);
            entityManager.persist(from);
            entityTransaction.commit();
        }catch (Exception e){
            if (entityTransaction != null){
                entityTransaction.rollback();
            }
        }
    }


}

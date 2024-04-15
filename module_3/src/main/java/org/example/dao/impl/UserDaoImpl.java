package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.config.JpaConfig;
import org.example.config.ObjectFactory;
import org.example.dao.AccountDao;
import org.example.dao.UserDao;
import org.example.entity.Account;
import org.example.entity.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private AccountDao accountDao = ObjectFactory.getInstance().getService(AccountDao.class);
    private EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void create(User entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public void update(User entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
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
            Query query = entityManager.createQuery("delete from User u where u.id = :id ").
                    setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Collection<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Double getWholeBalanceById(Long id) {
        User user = findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        Double sum = 0.0;
        Set<Account> accounts = user.getAccounts();
        if (accounts != null) {
            for (Account account : accounts) {
                Long accountId = account.getId();
                if (this.accountDao == null) {
                    accountDao = ObjectFactory.getInstance().getService(AccountDao.class);
                }
                sum += accountDao.getAccountBalanceById(accountId);
            }
        }
        return sum;
    }
}

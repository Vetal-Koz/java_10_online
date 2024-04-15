package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.config.JpaConfig;
import org.example.config.ObjectFactory;
import org.example.dao.TransactionCategoryDao;
import org.example.entity.TransactionCategory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TransactionCategoryDaoImpl implements TransactionCategoryDao {

    private final JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private final EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(TransactionCategory entity) {
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
    public void update(TransactionCategory entity) {
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
            Query query = entityManager.createQuery("delete from TransactionCategory a where a.id = :id ").
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
    public Optional<TransactionCategory> findById(Long id) {
        return Optional.ofNullable(entityManager.find(TransactionCategory.class, id));
    }

    @Override
    public Collection<TransactionCategory> findAll() {
        return entityManager.createQuery("select t from TransactionCategory t").getResultList();
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("select count(t) from TransactionCategory t").getSingleResult();
    }

    @Override
    public Optional<TransactionCategory> findByCategory(String category) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransactionCategory> criteriaQuery = criteriaBuilder.createQuery(TransactionCategory.class);
        Root<TransactionCategory> from = criteriaQuery.from(TransactionCategory.class);

        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("category"), category));

        List<TransactionCategory> categories = entityManager.createQuery(criteriaQuery).getResultList();


        return Optional.ofNullable(categories.getFirst());
    }
}

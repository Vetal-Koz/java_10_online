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
import org.example.dao.DataTableRequest;
import org.example.dao.GarageDao;
import org.example.entity.Garage;
import org.example.type.OrderType;

import java.util.Collection;
import java.util.Optional;

public class GarageDaoImpl implements GarageDao {

    JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(Garage entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void update(Garage entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("delete from Garage g where g.id = :id")
                    .setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Garage> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Garage.class, id));
    }

    @Override
    public Collection<Garage> findAll() {
        return entityManager.createQuery("select g from Garage g").getResultList();
    }

    @Override
    public Collection<Garage> findAll(DataTableRequest tableRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Garage> criteriaQuery = criteriaBuilder.createQuery(Garage.class);

        Root<Garage> from = criteriaQuery.from(Garage.class);

        if (tableRequest.getOrderType() == OrderType.ASC) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(tableRequest.getColumn())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(tableRequest.getColumn())));
        }

        int size = tableRequest.getSize();
        int page = (tableRequest.getPage() - 1) * tableRequest.getSize();

        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(g) from Garage g").executeUpdate();
    }
}

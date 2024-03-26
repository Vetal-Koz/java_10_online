package org.example.dao.impl;

import jakarta.persistence.Query;
import org.example.config.HibernateService;
import org.example.config.ObjectFactory;
import org.example.dao.GarageDao;
import org.example.entity.Car;
import org.example.entity.Garage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class GarageDaoImpl implements GarageDao {

    private final HibernateService hibernateService = ObjectFactory.getInstance().getService(HibernateService.class);

    @Override
    public void create(Garage entity) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Garage entity) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Garage g where g.id = :id ")
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
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Garage garage = session.find(Garage.class, id);
            transaction.commit();
            return Optional.of(garage);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Garage> findAll() {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Garage");
            List<Garage> garages = query.getResultList();
            transaction.commit();
            return garages;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return Collections.emptyList();
        }
    }

}

package org.example.dao.impl;

import jakarta.persistence.Query;
import org.example.config.HibernateService;
import org.example.config.ObjectFactory;
import org.example.dao.CarDao;
import org.example.dao.GarageDao;
import org.example.entity.Car;
import org.example.entity.Garage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class CarDaoImpl implements CarDao {
    private HibernateService hibernateService = ObjectFactory.getInstance().getService(HibernateService.class);
    private GarageDao garageDao = ObjectFactory.getInstance().getService(GarageDao.class);

    @Override
    public void create(Car entity) {
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
    public void update(Car entity) {
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
            Query query = session.createQuery("delete from Car c where c.id = :id ")
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
    public Optional<Car> findById(Long id) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Car car = session.find(Car.class, id);
            transaction.commit();
            return Optional.of(car);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Car> findAll() {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car");
            List<Car> cars = query.getResultList();
            transaction.commit();
            return cars;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return Collections.emptyList();
        }
    }

    @Override
    public void attachCarToGarage(Long carId, Long garageId) {
        Car car = findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Garage not found"));
        Set<Car> cars = garage.getCars();
        cars.add(car);
        garageDao.update(garage);
    }

    @Override
    public void detachCarFromGarage(Long carId, Long garageId) {
        Car car = findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Garage not found"));
        Set<Car> cars = garage.getCars();
        for (Car car1 : cars) {
            if (car.equals(car1)) {
                cars.remove(car1);
            }
        }
        garageDao.update(garage);
    }

    @Override
    public Collection<Car> findAllCarsByGarage(Long garageId) {
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Garage not found"));
        return garage.getCars();
    }

}

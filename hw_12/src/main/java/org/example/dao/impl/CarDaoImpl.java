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
import org.example.dao.CarDao;
import org.example.dao.DataTableRequest;
import org.example.dao.GarageDao;
import org.example.entity.Car;
import org.example.entity.Garage;
import org.example.type.OrderType;

import java.util.Collection;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private GarageDao garageDao = ObjectFactory.getInstance().getService(GarageDao.class);

    @Override
    public void attachCarToGarage(Long carId, Long garageId) {
        Car car = findById(carId).orElseThrow(() -> new RuntimeException("Entity not found"));
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Entity not found"));

        garage.getCars().add(car);
        garageDao.update(garage);
    }

    @Override
    public void detachCarFromGarage(Long carId, Long garageId) {
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Entity not found"));

        garage.getCars().removeIf(car1 -> car1.getId().equals(carId));
        garageDao.update(garage);
    }

    @Override
    public Collection<Car> findCarByGarageId(Long garageId) {
        Garage garage = garageDao.findById(garageId).orElseThrow(() -> new RuntimeException("Entity not found"));
        return garage.getCars();
    }

    @Override
    public void create(Car entity) {
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
    public void update(Car entity) {
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
            Query query = entityManager.createQuery("delete from Car c where c.id = :id")
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
        return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    @Override
    public Collection<Car> findAll() {
        return entityManager.createQuery("select c from Car c").getResultList();
    }

    @Override
    public Collection<Car> findAll(DataTableRequest tableRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);

        Root<Car> from = criteriaQuery.from(Car.class);

        if (tableRequest.getOrderType().equals(OrderType.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(tableRequest.getColumn())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(tableRequest.getColumn())));
        }

        int page = (tableRequest.getPage() - 1) * tableRequest.getSize();
        int size = tableRequest.getSize();
        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }



    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from Car c").executeUpdate();
    }
}

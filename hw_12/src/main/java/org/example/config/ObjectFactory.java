package org.example.config;

import org.example.dao.CarDao;
import org.example.dao.GarageDao;
import org.example.dao.impl.CarDaoImpl;
import org.example.dao.impl.GarageDaoImpl;
import org.example.service.CarService;
import org.example.service.GarageService;
import org.example.service.impl.CarServiceImpl;
import org.example.service.impl.GarageServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private final static ObjectFactory instance = new ObjectFactory();

    private ObjectFactory() {
    }

    private final Map<Class<?>, Object> objectMap = new HashMap<>();

    public <S> S getService(Class<?> interfaceService) {
        return (S) objectMap.get(interfaceService);
    }

    public void initObjectFactory() {
        objectMap.put(JpaConfig.class, new HibernateJpaConfig());
        objectMap.put(GarageDao.class, new GarageDaoImpl());
        objectMap.put(CarDao.class, new CarDaoImpl());
        objectMap.put(CarService.class, new CarServiceImpl());
        objectMap.put(GarageService.class, new GarageServiceImpl());
    }

    public static ObjectFactory getInstance() {
        return instance;
    }
}

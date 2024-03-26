package org.example.config;

import org.example.config.impl.HibernateServiceImpl;
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

    private static final ObjectFactory instance = new ObjectFactory();
    private Map<Class<?>, Object> objectMap = new HashMap<>();

    private ObjectFactory() {
    }

    ;

    public static ObjectFactory getInstance() {
        return instance;
    }

    public <E> E getService(Class<E> interfaceService) {
        return (E) objectMap.get(interfaceService);
    }

    public void initObjectFactory() {
        objectMap.put(HibernateService.class, new HibernateServiceImpl());
        objectMap.put(GarageDao.class, new GarageDaoImpl());
        objectMap.put(CarDao.class, new CarDaoImpl());
        objectMap.put(GarageService.class, new GarageServiceImpl());
        objectMap.put(CarService.class, new CarServiceImpl());
    }

}

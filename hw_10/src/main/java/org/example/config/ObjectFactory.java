package org.example.config;

import org.example.config.impl.PostgresJdbcService;
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

    private final Map<Class<?>, Object> objectMap = new HashMap<>();

    private ObjectFactory() {
    }

    ;

    public static ObjectFactory getInstance() {
        return instance;
    }

    public <S> S getService(Class<S> serviceInterface) {
        return (S) objectMap.get(serviceInterface);
    }

    public void initObjectFactory() {
        objectMap.put(JdbcService.class, new PostgresJdbcService());
        objectMap.put(CarDao.class, new CarDaoImpl());
        objectMap.put(GarageDao.class, new GarageDaoImpl());
        objectMap.put(CarService.class, new CarServiceImpl());
        objectMap.put(GarageService.class, new GarageServiceImpl());
    }
}

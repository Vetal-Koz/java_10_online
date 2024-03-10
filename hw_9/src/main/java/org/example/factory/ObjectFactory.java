package org.example.factory;

import org.example.dao.impl.CarDao;
import org.example.dao.impl.CarGarageDao;
import org.example.dao.impl.GarageDao;
import org.example.db.impl.CarGarageJsonFileDb;
import org.example.db.impl.CarJsonFileDb;
import org.example.db.impl.GarageJsonFileDb;

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

    public Object getService(Class<?> interfaceService) {
        return objectMap.get(interfaceService);
    }

    public void initObjectFactoryForJson() {
        objectMap.put(CarDao.class, new CarJsonFileDb());
        objectMap.put(GarageDao.class, new GarageJsonFileDb());
        objectMap.put(CarGarageDao.class, new CarGarageJsonFileDb());
    }
}

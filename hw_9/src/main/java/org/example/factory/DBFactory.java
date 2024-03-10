package org.example.factory;

import org.example.db.FileDb;
import org.example.db.impl.CarGarageJsonFileDb;
import org.example.db.impl.CarJsonFileDb;
import org.example.util.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DBFactory {
    private static DBFactory instance;

    private final ObjectFactory objectFactory = ObjectFactory.getInstance();

    private DBFactory() {
    }

    ;

    public static DBFactory getInstance() {
        if (instance == null) {
            instance = new DBFactory();
            return instance;
        } else {
            return instance;
        }
    }

    public FileDb getFileDb(Class<?> serviceClass) {
        final Map<String, String> pMap = ResourceUtil.getResource(this.getClass().getClassLoader());
        final String dbInstance = pMap.get("db.instance");
        if (dbInstance.equals(ResourceType.JSON.getType())) {
            File[] files = new File[]{new File(FileType.CARS_JSON.getType()), new File(FileType.GARAGES_JSON.getType()), new File(FileType.CAR_GARAGES_JSON.getType())};
            for (File file : files) {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            objectFactory.initObjectFactoryForJson();
            return (FileDb) objectFactory.getService(serviceClass);
        }

        throw new RuntimeException("incorrect db format");
    }
}

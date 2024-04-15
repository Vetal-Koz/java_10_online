package org.example.config;

import org.example.config.impl.PostgresJdbcService;
import org.example.dao.AccountDao;
import org.example.dao.TransactionCategoryDao;
import org.example.dao.TransactionDao;
import org.example.dao.UserDao;
import org.example.dao.impl.AccountDaoImpl;
import org.example.dao.impl.TransactionCategoryDaoImpl;
import org.example.dao.impl.TransactionDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.service.AccountService;
import org.example.service.TransactionCategoryService;
import org.example.service.TransactionService;
import org.example.service.UserService;
import org.example.service.impl.AccountServiceImpl;
import org.example.service.impl.TransactionCategoryServiceImpl;
import org.example.service.impl.TransactionServiceImpl;
import org.example.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static final ObjectFactory instance = new ObjectFactory();
    private static Map<Class<?>, Object> objectMap = new HashMap<>();

    private ObjectFactory() {
    }

    public <S> S getService(Class<?> interfaceService) {
        return (S) objectMap.get(interfaceService);
    }

    public void initObjectMap() {
        objectMap.put(JpaConfig.class, new HibernateJpaConfig());
        objectMap.put(JdbcService.class, new PostgresJdbcService());
        objectMap.put(UserDao.class, new UserDaoImpl());
        objectMap.put(AccountDao.class, new AccountDaoImpl());
        objectMap.put(TransactionDao.class, new TransactionDaoImpl());
        objectMap.put(TransactionCategoryDao.class, new TransactionCategoryDaoImpl());
        objectMap.put(UserService.class, new UserServiceImpl());
        objectMap.put(AccountService.class, new AccountServiceImpl());
        objectMap.put(TransactionService.class, new TransactionServiceImpl());
        objectMap.put(TransactionCategoryService.class, new TransactionCategoryServiceImpl());
    }

    public static ObjectFactory getInstance() {
        return instance;
    }
}

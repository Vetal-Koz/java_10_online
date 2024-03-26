package org.example.config.impl;

import org.example.config.HibernateService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateServiceImpl implements HibernateService {

    private final SessionFactory sessionFactory;

    public HibernateServiceImpl() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

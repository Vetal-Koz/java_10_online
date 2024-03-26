package org.example.config;

import org.hibernate.SessionFactory;

public interface HibernateService {
    SessionFactory getSessionFactory();
}

package org.example.config;

import jakarta.persistence.EntityManagerFactory;

public interface JpaConfig {
    EntityManagerFactory getEntityManagerFactory();
}

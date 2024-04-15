package org.example.dao.impl;

import com.opencsv.CSVWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.config.FileType;
import org.example.config.JdbcService;
import org.example.config.JpaConfig;
import org.example.config.ObjectFactory;
import org.example.dao.AccountDao;
import org.example.entity.Account;
import org.example.entity.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import java.util.*;

public class AccountDaoImpl implements AccountDao {

    private final JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private final JdbcService jdbcService = ObjectFactory.getInstance().getService(JdbcService.class);
    private final EntityManagerFactory entityManagerFactory = jpaConfig.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void create(Account entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public void update(Account entity) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Query query = entityManager.createQuery("delete from Account a where a.id = :id ").
                    setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    @Override
    public Collection<Account> findAll() {
        return entityManager.createQuery("select a from Account a").getResultList();

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Double getAccountBalanceById(Long id) {
        Account account = findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        Set<Transaction> transactions = account.getTransactions();
        Double sum = 0.0;
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                if (transaction.getTransactionCategory().getCategory().equals("Income")) {
                    sum += transaction.getSum();
                } else {
                    sum -= transaction.getSum();
                }
            }
        }
        return sum;
    }

    @Override
    public void exportAccountStatement(Long accountId, Timestamp from, Timestamp to) {
        File file = new File(FileType.STUDENTS_CSV.getType());
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("select t.description, t.id, t.created_on, t.sum, a.name, tc.category " +
                "from accounts as a " +
                "inner join public.transactions t on a.id = t.account_id " +
                "inner join public.transaction_category tc on t.transaction_category_id = tc.id " +
                "where a.id = ? and t.created_on between ? and ?"
        );
             CSVWriter csvWriter = new CSVWriter(new FileWriter(file))
        ) {
            if (file.exists()) {
                file.createNewFile();

            }
            preparedStatement.setLong(1, accountId);
            preparedStatement.setTimestamp(2, from);
            preparedStatement.setTimestamp(3, to);

            ResultSet resultSet = preparedStatement.executeQuery();
            String[] header = {"Description", "Id", "Date", "Sum", "Account", "Category"};
            csvWriter.writeNext(header);
            while (resultSet.next()) {
                List<String> results = new ArrayList<>();
                String description = resultSet.getString(1);
                Long id = resultSet.getLong(2);
                Timestamp timestamp = resultSet.getTimestamp(3);
                Double sum = resultSet.getDouble(4);
                String accountName = resultSet.getString(5);
                String category = resultSet.getString(6);

                results.add(description);
                results.add(id.toString());
                results.add(timestamp.toString());
                results.add(sum.toString());
                results.add(accountName);
                results.add(category);

                csvWriter.writeNext(results.toArray(new String[0]));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

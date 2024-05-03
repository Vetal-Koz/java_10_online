package org.example.dao.impl;

import lombok.Getter;
import org.example.service.JdbcService;
import org.example.dao.GarageDao;
import org.example.entity.Garage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
public class GarageDaoImpl implements GarageDao {
    private JdbcService jdbcService;

    @Override
    public void create(Garage entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("insert into garages values (default, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Garage entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("UPDATE garages SET garage_name = ? WHERE garage_id = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("DELETE FROM garages WHERE garage_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existById(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("SELECT COUNT(*) FROM garages WHERE garage_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Garage> findAll() {
        List<Garage> garages = new ArrayList<>();
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM garages")
        ) {
            while (resultSet.next()) {
                garages.add(buildCarByResultSet(resultSet));
            }
            return garages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Garage> findById(Long id) {
        if (!existById(id)) {
            return Optional.empty();
        } else {
            try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("SELECT * FROM garages WHERE garage_id = ?");
                 ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                resultSet.next();
                return Optional.of(buildCarByResultSet(resultSet));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Garage buildCarByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("garage_id");
        String brand = resultSet.getString("garage_name");
        Garage garage = new Garage();
        garage.setId(id);
        garage.setName(brand);
        return garage;
    }

    public void setJdbcService(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }
}

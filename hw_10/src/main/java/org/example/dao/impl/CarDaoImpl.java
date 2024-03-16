package org.example.dao.impl;

import org.example.config.JdbcService;
import org.example.config.ObjectFactory;
import org.example.dao.CarDao;
import org.example.entity.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private final JdbcService jdbcService = ObjectFactory.getInstance().getService(JdbcService.class);

    @Override
    public void create(Car entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("insert into cars values (default, ?, ?)")) {
            preparedStatement.setString(1, entity.getBrand());
            preparedStatement.setInt(2, entity.getYearOfCreating());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car entity) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("UPDATE cars SET brand = ?, year_of_creating = ? WHERE car_id = ?")) {
            preparedStatement.setString(1, entity.getBrand());
            preparedStatement.setInt(2, entity.getYearOfCreating());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("DELETE FROM cars WHERE car_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existById(Long id) {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("SELECT COUNT(*) FROM cars WHERE car_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getBoolean(1));
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM cars")
        ) {
            while (resultSet.next()) {
                cars.add(buildCarByResultSet(resultSet));
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Car> findById(Long id) {
        if (!existById(id)) {
            return Optional.empty();
        } else {
            try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("SELECT * FROM cars WHERE car_id = ?");
                 ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                resultSet.next();
                return Optional.of(buildCarByResultSet(resultSet));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void attachCarToGarage(Long carId, Long garageId) {
        if (existById(carId)) {
            try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("INSERT INTO car_garage VALUES (?, ?)")) {
                preparedStatement.setLong(1, carId);
                preparedStatement.setLong(2, garageId);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Such car does not exist");
        }
    }

    @Override
    public void detachCarFromGarage(Long carId, Long garageId) {
        if (existById(carId)) {
            try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("DELETE FROM car_garage WHERE car_id = ? AND garage_id = ?")) {
                preparedStatement.setLong(1, carId);
                preparedStatement.setLong(2, garageId);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Such car does not exist");
        }
    }

    @Override
    public Collection<Car> findAllCarsByGarage(Long garageId) {
        List<Car> carList = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("SELECT cars.car_id, cars.brand, cars.year_of_creating FROM car_garage LEFT JOIN public.cars ON car_garage.car_id = cars.car_id WHERE garage_id=?");
        ) {
            preparedStatement.setLong(1, garageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carList.add(buildCarByResultSet(resultSet));
            }
            return carList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Car buildCarByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("car_id");
        String brand = resultSet.getString("brand");
        int yearOfCreating = resultSet.getInt("year_of_creating");
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setYearOfCreating(yearOfCreating);
        return car;
    }
}

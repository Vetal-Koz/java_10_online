package org.example.hw_7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.hw_7.entity.Car;
import org.example.hw_7.entity.Garage;
import org.example.hw_7.reactiv.NativePubSub;
import org.example.hw_7.service.CarService;
import org.example.hw_7.service.impl.CarServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CarViewController implements Initializable {
    private final CarService carService = new CarServiceImpl();

    @FXML
    private TextField carIdText;

    @FXML
    private TextField carNameOfMarkText;
    @FXML
    private TextField carYearOfCreatingText;

    @FXML
    private Button createCar;

    @FXML
    private Button updateCar;

    @FXML
    private Button deleteCar;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, String> idColumn;

    @FXML
    private TableColumn<Car, String> nameOfMarkColumn;

    @FXML
    private TableColumn<Car, String> yearOfCreatingColumn;

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cars.addAll(carService.findAll());
        idColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
        nameOfMarkColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("nameOfMark"));
        yearOfCreatingColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("yearOfCreating"));
        carTable.setItems(cars);

        carTable.setRowFactory(tv -> {
            TableRow<Car> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Car rowData = row.getItem();
                carIdText.setText(String.valueOf(rowData.getId()));
                carNameOfMarkText.setText(rowData.getNameOfMark());
                carYearOfCreatingText.setText(String.valueOf(rowData.getYearOfCreating()));
            });
            return row;
        });

        NativePubSub.getInstance().subscribeCar(this::updateCars);
    }

    public void create() {
        Car car = new Car();
        car.setNameOfMark(carNameOfMarkText.getText());
        car.setYearOfCreating(Integer.valueOf(carYearOfCreatingText.getText()));
        carService.create(car);
        NativePubSub.getInstance().publishCar(true);
    }

    public void update() {
        Car car = new Car();
        car.setId(Long.parseLong(carIdText.getText()));
        car.setNameOfMark(carNameOfMarkText.getText());
        car.setYearOfCreating(Integer.valueOf(carYearOfCreatingText.getText()));
        carService.update(car);
        NativePubSub.getInstance().publishCar(true);
    }

    public void delete() {
        carService.delete(Long.valueOf(carIdText.getText()));
        NativePubSub.getInstance().publishCar(true);
    }

    private void updateCars(Boolean b) {
        if (b) {
            cars = FXCollections.observableArrayList();
            cars.addAll(carService.findAll());
            carTable.setItems(cars);
        }
    }
}

package org.example.hw_7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.hw_7.entity.Garage;
import org.example.hw_7.reactiv.NativePubSub;
import org.example.hw_7.service.GarageService;
import org.example.hw_7.service.impl.GarageServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GarageViewController implements Initializable {
    private final GarageService garageService = new GarageServiceImpl();

    @FXML
    private TextField garageIdText;

    @FXML
    private TextField garageNameText;

    @FXML
    private Button createGarage;

    @FXML
    private Button updateGarage;

    @FXML
    private Button deleteGarage;

    @FXML
    private TableView<Garage> garageTable;

    @FXML
    private TableColumn<Garage, String> idColumn;

    @FXML
    private TableColumn<Garage, String> nameColumn;

    private ObservableList<Garage> garages = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        garages.addAll(garageService.findAll());
        idColumn.setCellValueFactory(new PropertyValueFactory<Garage, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Garage, String>("name"));
        garageTable.setItems(garages);

        garageTable.setRowFactory(tv -> {
            TableRow<Garage> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Garage rowData = row.getItem();
                garageIdText.setText(String.valueOf(rowData.getId()));
                garageNameText.setText(rowData.getName());
            });
            return row;
        });

        NativePubSub.getInstance().subscribeGarage(this::updateGarages);
    }

    public void create() {
        Garage garage = new Garage();
        garage.setName(garageNameText.getText());
        garageService.create(garage);
        NativePubSub.getInstance().publishGarage(true);
    }

    public void update() {
        Garage garage = new Garage();
        garage.setId(Long.parseLong(garageIdText.getText()));
        garage.setName(garageNameText.getText());
        garageService.update(garage);
        NativePubSub.getInstance().publishGarage(true);
    }

    public void delete() {
        garageService.delete(Long.valueOf(garageIdText.getText()));
        NativePubSub.getInstance().publishGarage(true);
    }

    private void updateGarages(Boolean b) {
        if (b) {
            garages = FXCollections.observableArrayList();
            garages.addAll(garageService.findAll());
            garageTable.setItems(garages);
        }
    }
}

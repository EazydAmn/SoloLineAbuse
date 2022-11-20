package com.example.omerta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductsAdminConnector implements Initializable {

    @FXML
    TableView<Product> product;
    @FXML
    TableColumn<Product,String> name;
    @FXML
    TableColumn<Product,String> category;
    @FXML
    TableColumn<Product,String> rarity;
    @FXML
    TableColumn<Product,String> desc;
    @FXML
    TableColumn<Product,String> countInStock;
    @FXML
    TableColumn<Product,String> cost;
    @FXML
    TableColumn<Product,String> image;

    @FXML
    Button back;

    public static ObservableList<Product> productsData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product.getItems().clear();
        productsData = FXCollections.observableArrayList();
        try {
            DBConnector.loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        name.setCellValueFactory(celldata -> celldata.getValue().name);
        category.setCellValueFactory(celldata -> celldata.getValue().category);
        rarity.setCellValueFactory(celldata -> celldata.getValue().rarity);
        desc.setCellValueFactory(celldata -> celldata.getValue().desc);
        countInStock.setCellValueFactory(celldata -> celldata.getValue().countInStock);
        cost.setCellValueFactory(celldata -> celldata.getValue().cost);
        image.setCellValueFactory(celldata -> celldata.getValue().image);
        product.setItems(productsData);
    }

    public void back() throws IOException {
        Parent root_admin = FXMLLoader.load(getClass().getResource("main-admin.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root_admin, 1200, 600));
        window.show();
    }

    public void reloadProduct(){
        product.getItems().clear();
        productsData = FXCollections.observableArrayList();
        try {
            DBConnector.loadProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        name.setCellValueFactory(celldata -> celldata.getValue().name);
        category.setCellValueFactory(celldata -> celldata.getValue().category);
        rarity.setCellValueFactory(celldata -> celldata.getValue().rarity);
        desc.setCellValueFactory(celldata -> celldata.getValue().desc);
        countInStock.setCellValueFactory(celldata -> celldata.getValue().countInStock);
        cost.setCellValueFactory(celldata -> celldata.getValue().cost);
        image.setCellValueFactory(celldata -> celldata.getValue().image);
        product.setItems(productsData);
    }

    public void addProduct() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-product.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root, 900, 600));
        window.show();
    }
}

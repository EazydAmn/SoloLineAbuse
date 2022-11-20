package com.example.omerta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class MainAdminController implements Initializable {
    @FXML
    TableView<History> history;
    @FXML
    TableColumn<History,String> fio;
    @FXML
    TableColumn<History,String> clientCategory;
    @FXML
    TableColumn<History,String> login;
    @FXML
    TableColumn<History,String> password;
    @FXML
    TableColumn<History,String> email;
    @FXML
    TableColumn<History,String> telephone;
    @FXML
    TableColumn<History,String> lastEnter;
    @FXML
    TableColumn<History,String> typeEnter;

    @FXML
    Button product;

    public static ObservableList<History> usersData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersData = FXCollections.observableArrayList();
        try {
            DBConnector.loadHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fio.setCellValueFactory(celldata -> celldata.getValue().FIO);
        clientCategory.setCellValueFactory(celldata -> celldata.getValue().category);
        login.setCellValueFactory(celldata -> celldata.getValue().login);
        password.setCellValueFactory(celldata -> celldata.getValue().password);
        email.setCellValueFactory(celldata -> celldata.getValue().email);
        telephone.setCellValueFactory(celldata -> celldata.getValue().telephone);
        lastEnter.setCellValueFactory(celldata -> celldata.getValue().lastEnter);
        typeEnter.setCellValueFactory(celldata -> celldata.getValue().typeEnter);
        history.setItems(usersData);
    }

    public void reloadHistory() throws InterruptedException {
        history.getItems().clear();
        usersData = FXCollections.observableArrayList();
        try {
            DBConnector.loadHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fio.setCellValueFactory(celldata -> celldata.getValue().FIO);
        clientCategory.setCellValueFactory(celldata -> celldata.getValue().category);
        login.setCellValueFactory(celldata -> celldata.getValue().login);
        password.setCellValueFactory(celldata -> celldata.getValue().password);
        email.setCellValueFactory(celldata -> celldata.getValue().email);
        telephone.setCellValueFactory(celldata -> celldata.getValue().telephone);
        lastEnter.setCellValueFactory(celldata -> celldata.getValue().lastEnter);
        typeEnter.setCellValueFactory(celldata -> celldata.getValue().typeEnter);
        history.setItems(usersData);
    }
    public void products() throws IOException {
        Parent root_admin = FXMLLoader.load(getClass().getResource("products-admin.fxml"));
        Stage window = (Stage) product.getScene().getWindow();
        window.setScene(new Scene(root_admin, 1050, 600));
        window.show();
    }

    public void logOut() throws IOException {
        Parent root_admin = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) product.getScene().getWindow();
        window.setScene(new Scene(root_admin, 800, 600));
        window.show();
    }
}

package com.example.omerta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddProductConnector implements Initializable {
    @FXML
    ImageView logo;

    @FXML
    TextField id;

    @FXML
    TextField name;

    @FXML
    TextField count;

    @FXML
    TextField cost;

    @FXML
    TextField img;

    @FXML
    TextArea desc;

    @FXML
    ComboBox<String> category;

    @FXML
    ComboBox<String> rarity;

    public static ObservableList<String> typeProduct = FXCollections.observableArrayList();
    public static ObservableList<String> rarityProduct = FXCollections.observableArrayList();

    public void addProduct() throws SQLException, IOException {
        DBConnector.getIDRarity(rarity.getValue());
        DBConnector.getIDCategory(category.getValue());
        DBConnector.addProduct(id.getText(), name.getText(), count.getText(), cost.getText(), img.getText(), desc.getText(), DBConnector.idCategory, DBConnector.idRarity);
        reload();
    }

    public void clearProduct() throws IOException {
        reload();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeProduct = FXCollections.observableArrayList();
        rarityProduct = FXCollections.observableArrayList();
        File file = new File("img/logo.jpg");
        logo.setImage(new Image(file.toURI().toString(), 225, 225, false, false));
        try {
            DBConnector.loadCategory();
            DBConnector.loadRarity();
            DBConnector.getIDOrder();
            id.setText(String.valueOf(String.valueOf(DBConnector.id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id.setText(String.valueOf(DBConnector.id));
        category.setItems(typeProduct);
        rarity.setItems(rarityProduct);
    }

    public void back() throws IOException {
        Parent root_admin = FXMLLoader.load(getClass().getResource("products-admin.fxml"));
        Stage window = (Stage) rarity.getScene().getWindow();
        window.setScene(new Scene(root_admin, 1050, 600));
        window.show();
    }

    public void reload() throws IOException {
        Parent root_admin = FXMLLoader.load(getClass().getResource("add-product.fxml"));
        Stage window = (Stage) rarity.getScene().getWindow();
        window.setScene(new Scene(root_admin, 900, 600));
        window.show();
    }
}

package com.example.omerta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.omerta.MainClientController.cards;

public class CategoryController {
    @FXML
    Button buttonCat;

    public static Boolean flag = false;
    public static String category = "None";

    public void checkCategory() throws SQLException, IOException {
        System.out.println(buttonCat.getText());
        category = buttonCat.getText();
        reload();
    }

    public void SetData(String cat){
        buttonCat.setText(cat);
        buttonCat.setMinWidth(250);
    }

    public void reload() throws IOException {
        Parent root_client = FXMLLoader.load(getClass().getResource("main-client.fxml"));
        Stage window = (Stage) buttonCat.getScene().getWindow();
        window.setScene(new Scene(root_client, 1200, 900));
        window.show();
    }
}



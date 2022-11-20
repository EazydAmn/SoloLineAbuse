package com.example.omerta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    ImageView logo;
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;

    @FXML
    protected void logIn() throws SQLException, IOException {
        DBConnector.loginning(login.getText(),password.getText());
        if (DBConnector.admin_flag){
            Parent root_admin = FXMLLoader.load(getClass().getResource("main-admin.fxml"));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(new Scene(root_admin, 1200, 600));
            window.show();
        } else if (DBConnector.client_flag){
            Parent root_client = FXMLLoader.load(getClass().getResource("main-client.fxml"));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(new Scene(root_client, 1200, 900));
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnector.client_flag=false;
        DBConnector.admin_flag=false;
        File file = new File("img/logo.jpg");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }

    public void logInFromKey(KeyEvent keyEvent) throws SQLException, IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            logIn();
        }
    }
}
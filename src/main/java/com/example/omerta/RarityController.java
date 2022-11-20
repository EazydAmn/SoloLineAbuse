package com.example.omerta;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RarityController {
    @FXML
    Button buttonRar;

    public void checkRarity() {

    }

    public void SetData(String rar){
        buttonRar.setText(rar);
        buttonRar.setMinWidth(250);
    }

    public void reload(){

    }
}

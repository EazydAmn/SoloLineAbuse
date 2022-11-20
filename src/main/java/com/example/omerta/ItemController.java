package com.example.omerta;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ItemController {
    @FXML
    private Label catLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label costLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ratLabel;

    private Card card;

    public void SetData(Card card){
        this.card = card;
        nameLabel.setText(card.getName());
        descLabel.setText(card.getDesc());
        descLabel.setWrapText(true);
        catLabel.setText(card.getCatgory());
        ratLabel.setText(card.getRating());
        costLabel.setText(card.getCost());
        image.setImage(card.getImg());
    }
}

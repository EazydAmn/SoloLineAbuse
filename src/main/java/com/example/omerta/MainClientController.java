package com.example.omerta;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainClientController implements Initializable {

    @FXML
    ImageView logo;
    @FXML
    private Button cart;

    @FXML
    private GridPane categories;

    @FXML
    private GridPane gridProduct;

    @FXML
    private Button quit;

    @FXML
    private AnchorPane anch;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane rarities;

    public static List<Card> cards = new ArrayList<>();
    public static List<String> category = new ArrayList<>();
    public static List<String> rarity = new ArrayList<>();
    private int rowCard=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CategoryController.category.equals("None")) {
            cards = new ArrayList<>();
            category = new ArrayList<>();
            rarity = new ArrayList<>();
            File file = new File("img/logo.jpg");
            logo.setImage(new Image(file.toURI().toString()));
            try {
                DBConnector.listProduct();
                DBConnector.loadCategory();
                DBConnector.loadRarity();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rowCard = 0;
            int rowCat = 0;
            int rowRar = 0;
            try {
                System.out.println(cards);
                System.out.println(category);
                loadItems();
                for (String rar : rarity) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("category.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    CategoryController catController = fxmlLoader.getController();
                    catController.SetData(rar);
                    categories.add(anchorPane, 0, rowCat++);

                    categories.setMinWidth(Region.USE_COMPUTED_SIZE);
                    categories.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    categories.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    categories.setMinHeight(Region.USE_COMPUTED_SIZE);
                    categories.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    categories.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(0));
                }
                for (String categ : category) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("rarity.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    RarityController rarController = fxmlLoader.getController();
                    rarController.SetData(categ);
                    rarities.add(anchorPane, 0, rowCat++);

                    rarities.setMinWidth(Region.USE_COMPUTED_SIZE);
                    rarities.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    rarities.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    rarities.setMinHeight(Region.USE_COMPUTED_SIZE);
                    rarities.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    rarities.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(0));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            cards = new ArrayList<>();
            category = new ArrayList<>();
            rarity = new ArrayList<>();
            File file = new File("img/logo.jpg");
            logo.setImage(new Image(file.toURI().toString()));
            try {
                DBConnector.getIDCategory(CategoryController.category);
                DBConnector.checkCategory(DBConnector.idCategory);
                DBConnector.loadCategory();
                DBConnector.loadRarity();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rowCard = 0;
            int rowCat = 0;
            int rowRar = 0;
            try {
                System.out.println(cards);
                System.out.println(category);
                loadItems();
                for (String rar : rarity) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("category.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    CategoryController catController = fxmlLoader.getController();
                    catController.SetData(rar);
                    categories.add(anchorPane, 0, rowCat++);

                    categories.setMinWidth(Region.USE_COMPUTED_SIZE);
                    categories.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    categories.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    categories.setMinHeight(Region.USE_COMPUTED_SIZE);
                    categories.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    categories.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(0));
                }
                for (String categ : category) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("rarity.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    RarityController rarController = fxmlLoader.getController();
                    rarController.SetData(categ);
                    rarities.add(anchorPane, 0, rowCat++);

                    rarities.setMinWidth(Region.USE_COMPUTED_SIZE);
                    rarities.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    rarities.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    rarities.setMinHeight(Region.USE_COMPUTED_SIZE);
                    rarities.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    rarities.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(0));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void quit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) logo.getScene().getWindow();
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }

    public void loadItems() throws IOException {
        for (Card card: cards){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemController itemController = fxmlLoader.getController();
            itemController.SetData(card);
            gridProduct.add(anchorPane, 0, rowCard++);

            gridProduct.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridProduct.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridProduct.setMaxWidth(Region.USE_COMPUTED_SIZE);

            gridProduct.setMinHeight(Region.USE_COMPUTED_SIZE);
            gridProduct.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridProduct.setMaxHeight(Region.USE_COMPUTED_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }
    public void deleteItems(){
        gridProduct.getColumnConstraints().clear();
        gridProduct.getRowConstraints().clear();
        gridProduct.getChildren().clear();
    }
}

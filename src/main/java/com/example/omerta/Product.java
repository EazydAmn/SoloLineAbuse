package com.example.omerta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    StringProperty name;
    StringProperty category;
    StringProperty rarity;
    StringProperty desc;
    StringProperty countInStock;
    StringProperty cost;
    StringProperty image;
    public Product(String name, String category, String rarity, String desc, String countInStock, String cost, String image){
        this.name = new SimpleStringProperty(name);
        this.rarity = new SimpleStringProperty(rarity);
        this.desc = new SimpleStringProperty(desc);
        this.category = new SimpleStringProperty(category);
        this.countInStock = new SimpleStringProperty(countInStock);
        this.cost = new SimpleStringProperty(cost);
        this.image = new SimpleStringProperty(image);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty rarityProperty() {
        return rarity;
    }
    public StringProperty descProperty() {
        return desc;
    }
    public StringProperty categoryProperty() {
        return category;
    }
    public StringProperty countInStockProperty() {
        return countInStock;
    }
    public StringProperty costProperty() {
        return cost;
    }
    public StringProperty imageProperty() {
        return image;
    }
}

package com.example.omerta;

import javafx.scene.image.Image;

public class Card {

    private String name;
    private String desc;
    private String catgory;
    private String rating;
    private String cost;
    private Image img;

    public Card(String name, String desc, String catgory, String rating, String cost, Image img){
        this.name = name;
        this.desc = desc;
        this.catgory = catgory;
        this.rating = rating;
        this.cost = cost;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCatgory() {
        return catgory;
    }

    public void setCatgory(String catgory) {
        this.catgory = catgory;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}

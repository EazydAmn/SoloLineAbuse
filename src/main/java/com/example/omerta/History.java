package com.example.omerta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class History {
    StringProperty email;
    StringProperty login;
    StringProperty category;
    StringProperty telephone;
    StringProperty FIO;
    StringProperty password;
    StringProperty lastEnter;
    StringProperty typeEnter;
    public History(String FIO, String login, String category, String password, String email, String telephone, String lastEnter, String typeEnter){
        this.email = new SimpleStringProperty(email);
        this.FIO = new SimpleStringProperty(FIO);
        this.login = new SimpleStringProperty(login);
        this.category = new SimpleStringProperty(category);
        this.telephone = new SimpleStringProperty(telephone);
        this.password = new SimpleStringProperty(password);
        this.lastEnter = new SimpleStringProperty(lastEnter);
        this.typeEnter = new SimpleStringProperty(typeEnter);
    }
    public StringProperty emailProperty() {
        return email;
    }
    public StringProperty fioProperty() {
        return FIO;
    }
    public StringProperty loginProperty() {
        return login;
    }
    public StringProperty passwordProperty() {
        return password;
    }
    public StringProperty lastEnterProperty() {
        return lastEnter;
    }
    public StringProperty typeEnterProperty() {
        return typeEnter;
    }
    public StringProperty telephoneProperty() {
        return telephone;
    }
    public StringProperty categoryProperty() {
        return category;
    }
}

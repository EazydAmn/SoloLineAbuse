package com.example.omerta;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/omerta";
    private static final String DB_username = "root";
    private static final String DB_password = "";
    private static final String loginAdminQuery = "Select login, password from admins;";
    private static final String loginClientQuery = "Select login, password from clients;";
    private static final String clientTable_query = "SELECT c.fio, cat.name, c.login, c.password, c.email, c.telephone, c.last_enter, c.type_enter FROM clients c, category_of_client cat where c.category_of_client_id=cat.ID;";
    private static final String orderTable_query = "SELECT * FROM orders";
    private static final String categoryQuery = "Select name from type_product";
    private static final String rarityQuery = "Select name from rarities";
    private static final String productTable_query = "SELECT pn, tp.name, rn, pd, pcs, pc, pi from( select p.ID as pid, p.name as pn, p.Type_product_ID as ptp, r.name as rn, p.description pd , p.countInStock as pcs, p.cost as pc, p.image as pi FROM products p left join rarities r on p.rarity=r.id) A,\n" +
            "type_product tp where ptp=tp.id order by pid ASC";
    public static Boolean admin_flag = false;
    public static String idRarity;
    public static String idCategory;
    ObservableList<String> history = null;
    public static Boolean client_flag = false;
    public static int id;
    public static void loginning(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(URL,DB_username,DB_password);
        PreparedStatement adminStat = connection.prepareStatement(loginAdminQuery);
        ResultSet resultAdmin = adminStat.executeQuery();
        while (resultAdmin.next()){
            if((resultAdmin.getString(1).equals(login)) && (resultAdmin.getString(2).equals(password))){
                admin_flag = true;
            }
        }
        connection.close();
        Connection connection2 = DriverManager.getConnection(URL,DB_username,DB_password);
        PreparedStatement clientStat = connection2.prepareStatement(loginClientQuery);
        ResultSet resultClient = clientStat.executeQuery();
        while (resultClient.next()){
            if((resultClient.getString(1).equals(login)) && (resultClient.getString(2).equals(password))){
                client_flag = true;
            }
        }
        connection2.close();
    }
    public static void loadHistory() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement(clientTable_query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            MainAdminController.usersData.add(new History(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),  resultSet.getString(7),  resultSet.getString(8)));
        }
    }
    public static void loadProduct() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement(productTable_query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            ProductsAdminConnector.productsData.add(new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),  resultSet.getString(7)));
        }
    }

    public static void loadCategory() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement(rarityQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            AddProductConnector.rarityProduct.add(resultSet.getString(1));
            MainClientController.category.add(resultSet.getString(1));
        }
    }

    public static void loadRarity() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement(categoryQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            AddProductConnector.typeProduct.add(resultSet.getString(1));
            MainClientController.rarity.add(resultSet.getString(1));
        }
    }

    public static void getIDOrder() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement("Select max(id) from products");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            id = Integer.parseInt(resultSet.getString(1))+1;
        }
    }

    public static void getIDCategory(String category) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement("Select id from type_product where name='"+category+"'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            idCategory =  resultSet.getString(1);
        }
    }

    public static void getIDRarity(String rarity) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement("Select id from rarities where name='"+rarity+"'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            idRarity = resultSet.getString(1);
        }
    }

    public static void addProduct(String id, String name, String count, String cost, String image, String desc, String category, String rarity) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  `products` (`ID`, `Name`, `Type_product_ID`, `Rarity`, `Description`, `CountInStock`, `Cost`, `Image`) VALUES ('"+id+"', '"+name+"', '"+category+"', '"+rarity+"', '"+desc+"', '"+count+"', '"+cost+"', '"+image+"')");
        preparedStatement.executeUpdate();
    }

    public static void listProduct() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement(productTable_query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            File file = new File("img/"+rs.getString(7));
            Image image = new Image(file.toURI().toString());
            MainClientController.cards.add(new Card(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(6), image));
        }
    }

    public static void checkCategory(String cat) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_username, DB_password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT pn, tp.name, rn, pd, pcs, pc, pi from (select p.ID as pid, p.name as pn, p.Type_product_ID as ptp, r.name as rn, p.description pd , p.countInStock as pcs, p.cost as pc, p.image as pi FROM products p left join rarities r on p.rarity=r.id) A, \n" +
                "type_product tp where ptp=tp.id and ptp='"+cat+"' order by pid ASC");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            File file = new File("img/"+resultSet.getString(7));
            Image image = new Image(file.toURI().toString());
            MainClientController.cards.add(new Card(resultSet.getString(1), resultSet.getString(4), resultSet.getString(2), resultSet.getString(3), resultSet.getString(6), image));
        }
    }
}

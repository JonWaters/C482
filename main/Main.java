package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Add sample parts
        InHouse tvScreen = new InHouse(1,"TV Screen", 300.00, 5, 1, 20,
                101);
        InHouse tvShell = new InHouse(2,"TV Shell", 100.00, 5, 1, 20,
                101);
        InHouse powerCord = new InHouse(3,"Power Cord", 2.99, 5, 1, 20,
                101);
        Outsourced remote = new Outsourced(4, "Remote Control",29.99, 50, 30,
                100, "Panasonic");
        Inventory.addPart(tvScreen);
        Inventory.addPart(tvShell);
        Inventory.addPart(powerCord);
        Inventory.addPart(remote);

        //Add sample product
        Product television = new Product(1, "LCD Television", 499.99, 20, 20,
                100);
        television.addAssociatedPart(tvScreen);
        television.addAssociatedPart(tvShell);
        television.addAssociatedPart(powerCord);
        television.addAssociatedPart(remote);
        Inventory.addProduct(television);

        launch(args);
    }
}

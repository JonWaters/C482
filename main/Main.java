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

/**
 * The Inventory Management program implements an application for managing
 * an inventory of parts and products consisting of parts. 
 *
 * A feature suitable for a future version would be to control which parts can be
 * duplicated within a product.
 *
 * @author Jonathan Waters
 *
 */
public class Main extends Application {

    /**
     * The start method creates the FXML stage and loads the initial scene.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * The main method creates sample data and launches the application.
     * 
     * @param args
     */
    public static void main(String[] args) {

        //Add sample parts
        int partId = Inventory.getNewPartId();
        InHouse tvScreen = new InHouse(partId,"TV Screen", 300.00, 5, 1, 20,
                101);
        partId = Inventory.getNewPartId();
        InHouse tvShell = new InHouse(partId,"TV Shell", 100.00, 5, 1, 20,
                101);
        partId = Inventory.getNewPartId();
        InHouse powerCord = new InHouse(partId,"Power Cord", 2.99, 5, 1, 20,
                101);
        partId = Inventory.getNewPartId();
        Outsourced remote = new Outsourced(partId, "Remote Control",29.99, 50, 30,
                100, "Panasonic");
        Inventory.addPart(tvScreen);
        Inventory.addPart(tvShell);
        Inventory.addPart(powerCord);
        Inventory.addPart(remote);

        //Add sample product
        int productId = Inventory.getNewProductId();
        Product television = new Product(productId, "LCD Television", 499.99, 20, 20,
                100);
        television.addAssociatedPart(tvScreen);
        television.addAssociatedPart(tvShell);
        television.addAssociatedPart(powerCord);
        television.addAssociatedPart(remote);
        Inventory.addProduct(television);

        launch(args);
    }
}

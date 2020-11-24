package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    private static Part partToModify;

    private static Product productToModify;

    @FXML
    private TextField partSearchText;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TextField productSearchText;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    public static Part getPartToModify() {
        return partToModify;
    }

    public static Product getProductToModify() {
        return productToModify;
    }

    @FXML
    void exitButtonAction(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void partAddAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partDeleteAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }

    }

    @FXML
    void partModifyAction(ActionEvent event) throws IOException {

        partToModify = partTableView.getSelectionModel().getSelectedItem();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partSearchBtnAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchText.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayAlert(1);
        }
    }

    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }

    }

    @FXML
    void productAddAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddProductScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productDeleteAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want to delete the selected product?");
        Optional<ButtonType> result = alert.showAndWait();
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deleteProduct(selectedProduct);
        }

    }

    @FXML
    void productModifyAction(ActionEvent event) throws IOException {

        productToModify = productTableView.getSelectionModel().getSelectedItem();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProductScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productSearchBtnAction(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearchText.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            displayAlert(2);
        }
    }

    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (productSearchText.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Populate parts table view
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate products table view
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

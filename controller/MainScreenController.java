package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private TextField partSearchText;

    @FXML
    private TableView<?> partTableView;

    @FXML
    private TableColumn<?, ?> partIdColumn;

    @FXML
    private TableColumn<?, ?> partNameColumn;

    @FXML
    private TableColumn<?, ?> partInventoryColumn;

    @FXML
    private TableColumn<?, ?> partPriceColumn;

    @FXML
    private TextField productSearchText;

    @FXML
    private TableView<?> productTableView;

    @FXML
    private TableColumn<?, ?> productIdColumn;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productInventoryColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

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

    }

    @FXML
    void partModifyAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void partSearchTextChanged(InputMethodEvent event) {

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

    }

    @FXML
    void productModifyAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProductScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void productSearchTextChanged(InputMethodEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

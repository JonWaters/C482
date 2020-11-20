package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.input.InputMethodEvent;

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

    }

    @FXML
    void partAddAction(ActionEvent event) {

    }

    @FXML
    void partDeleteAction(ActionEvent event) {

    }

    @FXML
    void partModifyAction(ActionEvent event) {

    }

    @FXML
    void partSearchTextChanged(InputMethodEvent event) {

    }

    @FXML
    void productAddAction(ActionEvent event) {

    }

    @FXML
    void productDeleteAction(ActionEvent event) {

    }

    @FXML
    void productModifyAction(ActionEvent event) {

    }

    @FXML
    void productSearchTextChanged(InputMethodEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

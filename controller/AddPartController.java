package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML
    private Label partIdNameLabel;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private ToggleGroup tgPartType;

    @FXML
    private RadioButton outsouredRadioButton;

    @FXML
    private TextField partIdText;

    @FXML
    private TextField partNameText;

    @FXML
    private TextField partInventoryText;

    @FXML
    private TextField partPriceText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partIdNameText;

    @FXML
    private TextField partMinText;

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {

        returnToMainScreen(event);
    }

    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {

        setPartIdNameLabel();
    }

    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {

        setPartIdNameLabel();
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        int id = Inventory.getNewPartId();
        String name = partNameText.getText();
        Double price = Double.parseDouble(partPriceText.getText());
        int stock = Integer.parseInt(partInventoryText.getText());
        int min = Integer.parseInt(partMinText.getText());
        int max = Integer.parseInt(partMaxText.getText());
        int machineId;
        String companyName;

        if (inHouseRadioButton.isSelected()) {
            machineId = Integer.parseInt(partIdNameText.getText());
            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(newInHousePart);
        }

        if (outsouredRadioButton.isSelected()) {
            companyName = partIdNameLabel.getText();
            Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(newOutsourcedPart);
        }

        returnToMainScreen(event);

    }

    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void setPartIdNameLabel(){

        partIdNameLabel.setText("Part Type");

        if (inHouseRadioButton.isSelected()) {
            partIdNameLabel.setText("Machine ID");
        }

        if (outsouredRadioButton.isSelected()) {
            partIdNameLabel.setText("Company Name");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inHouseRadioButton.setSelected(true);
    }
}

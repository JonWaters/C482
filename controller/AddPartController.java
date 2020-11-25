package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML
    private Label partIdNameLabel;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private ToggleGroup tgPartType;

    @FXML
    private RadioButton outsourcedRadioButton;

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {

        partIdNameLabel.setText("Machine ID");
    }

    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {

        partIdNameLabel.setText("Company Name");
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = Inventory.getNewPartId();
            String name = partNameText.getText();
            Double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partAddSuccessful = false;

            if (minValid(min, max) && inventoryValid(min, max, stock)) {

                if (inHouseRadioButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(partIdNameText.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAddSuccessful = true;
                    } catch (Exception e) {
                        displayAlert(2);
                    }
                }

                if (outsouredRadioButton.isSelected()) {
                    companyName = partIdNameText.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddSuccessful = true;
                }

                if (partAddSuccessful) {
                    returnToMainScreen(event);
                }
            }
        } catch(Exception e) {
            displayAlert(1);
        }
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }

    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }

        return isValid;
    }

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 1 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inHouseRadioButton.setSelected(true);
    }
}

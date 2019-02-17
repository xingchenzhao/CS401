/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealerapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author zhaoxingchen
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView toyotaPicture;

    @FXML
    private Label optionals;

    @FXML
    private CheckBox optionAirConditionerCB;

    @FXML
    private CheckBox optionsPowerLocksCB;

    @FXML
    private CheckBox optionsPowerWindowsCB;

    @FXML
    private Label carType;

    @FXML
    private ChoiceBox<?> carTypeChoiceBox;

    @FXML
    private Label color;

    @FXML
    private ChoiceBox<?> carColorChoiceBox;

    @FXML
    private Label extendedInsurance;

    @FXML
    private RadioButton threeYearInsuranceRButton;

    @FXML
    private ToggleGroup insurance;

    @FXML
    private RadioButton oneYearInsuranceRButton;

    @FXML
    private RadioButton noneInsuranceRButton;

    @FXML
    private Label price;

    @FXML
    private Label carPrice;

    @FXML
    private Button reset;

    @FXML
    private Button exit;

    @FXML
    private ImageView carPhoto;

    @FXML
    private TextArea descriptions;

    @FXML
    void selectionChanged(ActionEvent event) {
        boolean[] isOptionalSelected = new boolean[3];
        isOptionalSelected[0] = optionsPowerLocksCB.isSelected();
        isOptionalSelected[1] = optionsPowerWindowsCB.isSelected();
        isOptionalSelected[2] = optionAirConditionerCB.isSelected();
        cars.setOptionalPrice(isOptionalSelected);

        descriptions.setText(cars.getDescriptions(cars.getType(), cars.getColor()));

        if (threeYearInsuranceRButton.isSelected()) {
            cars.setInsurancePrice("3 years");
        } else if (oneYearInsuranceRButton.isSelected()) {
            cars.setInsurancePrice("1 year");
        } else if (noneInsuranceRButton.isSelected()) {
            cars.setInsurancePrice("0");
        }

        carPrice.setText(cars.getTotalPrice());

    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void resetAction(ActionEvent event) {
        defaultSetting();

    }

    Cars cars = new Cars();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        defaultSetting();
        carTypeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number newValue) {
                String selectedType = (String) carTypeChoiceBox.getItems().get(newValue.intValue());
                System.out.println("From changed method " + selectedType);
                setAvailableColorForCar(selectedType);
                String selectedColor = null;
                if (selectedType.equalsIgnoreCase("Corolla")) {
                    selectedColor = "blue";
                    setPicture(selectedType, selectedColor);
                } else if (selectedType.equalsIgnoreCase("Yaris")) {
                    selectedColor = "black";
                    setPicture(selectedType, selectedColor);
                } else if (selectedType.equalsIgnoreCase("RAV4")) {
                    selectedColor = "white";
                    setPicture(selectedType, selectedColor);
                }

                cars = new Cars(selectedType, selectedColor);
                updateCarInfo(cars.getType(), cars.getColor());
                cars.setInsurancePrice(selectButton.getText());
                selectButton = (RadioButton) insurance.getSelectedToggle();
                carPrice.setText(cars.getTotalPrice());

//                setPicture(selectedType, selectedColor);
            }
        });

    }

    RadioButton selectButton;

    private void setAvailableColorForCar(String selectedType) {
        if (selectedType.equalsIgnoreCase("Corolla")) {
            ObservableList colorList = FXCollections.observableArrayList("Blue", "Red", "White");
            carColorChoiceBox.setItems(colorList);
            carColorChoiceBox.getSelectionModel().selectFirst();
        } else if (selectedType.equalsIgnoreCase("Yaris")) {
            ObservableList colorList = FXCollections.observableArrayList("Black", "Red");
            carColorChoiceBox.setItems(colorList);
            carColorChoiceBox.getSelectionModel().selectFirst();
        } else if (selectedType.equalsIgnoreCase("RAV4")) {
            ObservableList colorList = FXCollections.observableArrayList("White", "Grey");
            carColorChoiceBox.setItems(colorList);
            carColorChoiceBox.getSelectionModel().selectFirst();
        }

        carColorChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number newValue) {
                String selectedColor = (String) carColorChoiceBox.getItems().get(newValue.intValue());
                cars = new Cars(selectedType, selectedColor);
                setPicture(selectedType, selectedColor);
                updateCarInfo(cars.getType(), cars.getColor());
                RadioButton selectedRadioButton = (RadioButton) insurance.getSelectedToggle();
                cars.setInsurancePrice(selectedRadioButton.getText());
                carPrice.setText(cars.getTotalPrice());
                setPicture(selectedType, selectedColor);
            }
        });

    }

    private void updateCarInfo(String selectedType, String selectedColor) {
        descriptions.setText(cars.getDescriptions(selectedType, selectedColor));
        carPrice.setText(cars.getTotalPrice());
        setCheckBox(selectedType);
    }

    private void setCheckBox(String selectedType) {
        if (selectedType.equalsIgnoreCase("Corolla")) {
            optionAirConditionerCB.setSelected(false);
            optionAirConditionerCB.setDisable(true);
            optionsPowerLocksCB.setSelected(false);
            optionsPowerLocksCB.setDisable(false);
            optionsPowerWindowsCB.setSelected(false);
            optionsPowerWindowsCB.setDisable(false);
        } else if (selectedType.equalsIgnoreCase("Yaris")) {
            optionAirConditionerCB.setSelected(false);
            optionAirConditionerCB.setDisable(false);
            optionsPowerLocksCB.setSelected(false);
            optionsPowerLocksCB.setDisable(false);
            optionsPowerWindowsCB.setSelected(false);
            optionsPowerWindowsCB.setDisable(false);
        } else if (selectedType.equalsIgnoreCase("RAV4")) {
            optionAirConditionerCB.setSelected(false);
            optionAirConditionerCB.setDisable(false);
            optionsPowerLocksCB.setSelected(false);
            optionsPowerLocksCB.setDisable(false);
            optionsPowerWindowsCB.setSelected(false);
            optionsPowerWindowsCB.setDisable(true);
        }
    }
    Image selectImage;

    private void setPicture(String type, String color) {
        try {
            file = new File("src/resources/" + type + "_" + color + ".jpg");
            selectImage = new Image(file.toURI().toString());
            carPhoto.setImage(selectImage);
        } catch (Exception e) {
            System.out.println("Wrong File Path");
        }
    }

    File file = new File("");

    private void defaultSetting() {
        ObservableList typeList = FXCollections.observableArrayList("Corolla", "Yaris", "RAV4");
        carTypeChoiceBox.setItems(typeList);
        carTypeChoiceBox.getSelectionModel().selectFirst();
        ObservableList colorList = FXCollections.observableArrayList("Blue", "Red", "White");
        carColorChoiceBox.setItems(colorList);
        carColorChoiceBox.getSelectionModel().selectFirst();
        threeYearInsuranceRButton.setSelected(true);
        descriptions.setText(cars.getDescriptions("Corolla", "Blue"));
        descriptions.setWrapText(true);
        setPicture("Corolla", "Blue");
        carPrice.setText("18500");
        optionAirConditionerCB.setSelected(true);
        optionAirConditionerCB.setDisable(true);
        noneInsuranceRButton.setSelected(true);
        try {
            file = new File("src/resources/toyota_icon.jpg");
            selectImage = new Image(file.toURI().toString());
            toyotaPicture.setImage(selectImage);
        } catch (Exception e) {
            System.out.println("Wrong File Path");
        }
        carColorChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number newValue) {
                String selectedColor = (String) carColorChoiceBox.getItems().get(newValue.intValue());
                cars = new Cars("Corolla", selectedColor);
                updateCarInfo(cars.getType(), cars.getColor());
                selectButton = (RadioButton) insurance.getSelectedToggle();
                cars.setInsurancePrice(selectButton.getText());
                carPrice.setText(cars.getTotalPrice());
                setPicture(cars.getType(), selectedColor);
            }
        });

    }
}

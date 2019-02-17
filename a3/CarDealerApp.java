/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zhaoxingchen
 */
public class CarDealerApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        boolean[]optionals = new boolean[3];
//        optionals[0] = true;
//        optionals[1] = false;
//        optionals[2] = true;
//        Cars cars = new Cars("Corolla","Blue");
////        cars.setInsurancePrice("3 years");
//        cars.setOptionalPrice(optionals);
//        System.out.println(cars.getTotalPrice());
                
    }
    
}

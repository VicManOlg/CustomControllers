package com.solidsafe.scoutapp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author jordi
 */
public class Messages {
    public static void displayMissage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void displayError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static boolean displayQuestion(String title, String message) {
        boolean resultat = false;
        ButtonType okBttn = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType noBttn = new ButtonType("No", ButtonBar.ButtonData.NO);
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, title, okBttn, noBttn);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        if(alert.getResult()==okBttn){
        //selected yes
                resultat= true;
        }
        return resultat;
    }
}

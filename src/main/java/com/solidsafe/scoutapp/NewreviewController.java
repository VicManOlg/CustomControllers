package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class NewreviewController implements Initializable {

    @FXML
    private ScrollBar barTecnic;
    @FXML
    private Label lbTactic;
    @FXML
    private Label lbMental;
    @FXML
    private Label lbFisic;
    @FXML
    private Label lbIntelligence;
    @FXML
    private Label lbTecnic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barTecnic.setMax(10);
        barTecnic.setMin(1);
        barTecnic.setValue(5);
        ;
        lbTecnic.setText(barTecnic.getValue() + "");
    }    

    @FXML
    private void OnDragTecnicDetected(MouseEvent event) {
        lbTecnic.setText(barTecnic.getValue() + "");
    }

    @FXML
    private void OnDragTecnicDetected(DragEvent event) {
        lbTecnic.setText(barTecnic.getValue() + "");
    }
    
}

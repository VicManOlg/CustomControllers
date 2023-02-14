package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.PositionDTO;
import model.Repository;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class PositionsController implements Initializable {

    @FXML
    private MFXComboBox<PositionDTO> cbPositions;
    @FXML
    private MFXRadioButton rbFirst;
    @FXML
    private MFXRadioButton rbSecond;
    @FXML
    private MFXRadioButton rbStandar;
    ObservableList<PositionDTO> pObservableList;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<PositionDTO> pDTO = Repository.GetPositions(0);
        pObservableList = FXCollections.observableArrayList();
        for(PositionDTO p : pDTO){
            pObservableList.add(p);
        }
        cbPositions.setItems(pObservableList);
    }    

    @FXML
    private void ONbtnAddClickListenner(ActionEvent event) {
        
    }
    
}

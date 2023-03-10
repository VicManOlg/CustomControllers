package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.PlayerDTO;
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
    ObservableList<PositionDTO> pObservableList;
    @FXML
    private MFXCheckbox ckbFirstPos;
    private PlayerDTO player;
    @FXML
    private MFXCheckbox ckbFirstPos1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ONbtnAddClickListenner(ActionEvent event) throws IOException {
        int firstPos;
        if(ckbFirstPos.isSelected()){
            firstPos = 0;
        }
        else{
            firstPos = 1;
        }
        Repository repo = new Repository();
        repo.addPlayerPosition(this.player.getPlayerID(), cbPositions.getSelectedIndex() + 1, firstPos);
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
    }
    
    public void setPlayer(PlayerDTO player){
        this.player = player;
        loadComboBox();  
    }
    
    public void loadComboBox(){
        ArrayList<PositionDTO> pDTO = Repository.GetPositions(0);
        pObservableList = FXCollections.observableArrayList();
        for(PositionDTO p : pDTO){
            pObservableList.add(p);
        }
        cbPositions.setItems(pObservableList);
    }
    
}

package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import model.CategoryDTO;
import model.PlayerDTO;
import model.Repository;
import model.ScoutDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class NewreviewController implements Initializable {

    private ScrollBar barTecnic;
    private Label lbTecnic;
    
    ObservableList<PlayerDTO> pObservableList;
    ScoutDTO scout;
    PlayerDTO player;
    @FXML
    private ComboBox<PlayerDTO> cbPlayer;
    @FXML
    private ComboBox<String> cbLevels;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    public void setPlayer(PlayerDTO p, ScoutDTO s){
        this.player = p;
        this.scout = s;
        List<PlayerDTO> players = Repository.GetPlayers(1);
        pObservableList = FXCollections.observableArrayList();
        for (PlayerDTO player : players){
            pObservableList.add(player);
        }
        ObservableList<String> levelsOL = FXCollections.observableArrayList();
        levelsOL.add("Seguimiento");
        levelsOL.add("GFC");
        levelsOL.add("GFC+");
        levelsOL.add("Top");
        levelsOL.add("No Seguir");
        cbLevels.setItems(levelsOL);
        cbPlayer.setItems(pObservableList);
        
    }
    
    
    
}

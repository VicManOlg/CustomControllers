package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import model.CategoryDTO;
import model.PlayerDTO;
import model.PositionDTO;
import model.Repository;
import model.ReviewDTO;
import model.ScoutDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class NewreviewController implements Initializable {

    
    ObservableList<PlayerDTO> pObservableList;
    ObservableList<PositionDTO> posObservableList;
    ScoutDTO scout;
    PlayerDTO player;
    @FXML
    private ComboBox<PlayerDTO> cbPlayer;
    @FXML
    private ComboBox<String> cbLevels;
    @FXML
    private TextArea tfComments;
    @FXML
    private MFXTextField tfTecnic;
    @FXML
    private MFXTextField tfTactical;
    @FXML
    private MFXTextField tfMental;
    @FXML
    private MFXTextField tfPhisic;
    @FXML
    private MFXTextField tfIntelligence;
    @FXML
    private ComboBox<PositionDTO> cbPos;

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
        posObservableList = FXCollections.observableArrayList();
        pObservableList.add(p);
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
        cbPlayer.setValue(p);
        cbLevels.setValue(levelsOL.get(0));
        
        ArrayList<PositionDTO> positions = Repository.GetPlayerPositions(p.getPlayerID());
        for (PositionDTO pos : positions){
            posObservableList.add(pos);
        }
        cbPos.setItems(posObservableList);
    }

    @FXML
    private void OnBtnAddClickListenner(ActionEvent event)  {
        ReviewDTO review = new ReviewDTO(0, tfComments.getText(), java.time.LocalDate.now().toString(), 
                scout, player, 1, cbLevels.getSelectionModel().getSelectedItem(), Integer.parseInt(tfTecnic.getText()), 
                Integer.parseInt(tfTactical.getText()), Integer.parseInt(tfPhisic.getText()), Integer.parseInt(tfMental.getText()), Integer.parseInt(tfIntelligence.getText()));
        Repository r = new Repository();
        r.addReview(review);
       
    }

    @FXML
    private void OnCbPlayerSelectedChanged(ActionEvent event) {
        player = cbPlayer.getSelectionModel().getSelectedItem();       
        ArrayList<PositionDTO> positions = Repository.GetPlayerPositions(player.getPlayerID());
        posObservableList.clear();
        for (PositionDTO pos : positions){
            posObservableList.add(pos);
        }
        cbPos.setItems(posObservableList);
    }
    
    
    
}

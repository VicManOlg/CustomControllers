/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.CategoryDTO;
import model.Repository;
import model.ScoutDTO;
import model.TeamDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class TeamController implements Initializable {

    @FXML
    private MFXTextField tfUrl;
    @FXML
    private MFXTextField tfName;
    @FXML
    private ImageView ivUrl;
    @FXML
    private MFXComboBox<CategoryDTO> tfCat;
    ObservableList<CategoryDTO> catdto;
    @FXML
    private MFXButton btnAdd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<CategoryDTO> cats = Repository.GetCategories();
        catdto = FXCollections.observableArrayList();
        for (CategoryDTO cat : cats){
            catdto.add(cat);
        }
        tfCat.setItems(catdto);
    }    

    @FXML
    private void OnSearchUrlClickListenner(ActionEvent event) {
        Image image = new Image(tfUrl.getText());
        ivUrl.setImage(image);       
        
        

    }

    @FXML
    private void OnBtnAddClickListenner(ActionEvent event) throws IOException {
        TeamDTO t = new TeamDTO(0, tfName.getText(), tfCat.getSelectedIndex() + 1, tfUrl.getText()); 
        Repository r = new Repository();
        r.sendPOST(t, 1);
    }
    
    public void UpdateTeam( TeamDTO tdto){
        tfName.setText(tdto.getTeamName());
        tfUrl.setText(tdto.getPicture());
        tfCat.selectIndex(tdto.getCatID() - 1);
        Image image = new Image(tfUrl.getText());
        btnAdd.setText("Modificar");
        ivUrl.setImage(image); 


    }
    
}

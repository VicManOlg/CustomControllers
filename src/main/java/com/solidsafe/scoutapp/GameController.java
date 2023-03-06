/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class GameController implements Initializable {

    @FXML
    private Label lbClub;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivShield;
    @FXML
    private TableView<?> tableGames;
    @FXML
    private TableColumn<?, ?> rowVisitor;
    @FXML
    private TableColumn<?, ?> rowLocal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnPlayerClickListenner(ActionEvent event) {
    }

    @FXML
    private void OnReviwClickListenner(ActionEvent event) {
    }

    @FXML
    private void OnCategoryClickListenner(ActionEvent event) {
    }
    
}

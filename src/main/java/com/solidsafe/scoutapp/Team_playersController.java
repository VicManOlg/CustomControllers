package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.PlayerDTO;
import model.Repository;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class Team_playersController implements Initializable {

    @FXML
    private TableView<PlayerTV2> tbPlayer;
    @FXML
    private TableColumn<PlayerTV2, SimpleIntegerProperty> rowId;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowName;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowYear;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowNacionality;
    @FXML
    private ComboBox<?> comboPos;
    @FXML
    private TextField comboName;
    @FXML
    private CheckBox chbPos;
    @FXML
    private CheckBox chbName;
    
    ObservableList<PlayerTV2> playerstv;
    
    PlayerTV dat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rowName.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        rowId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowYear.setCellValueFactory(new PropertyValueFactory<>("Año"));
        rowNacionality.setCellValueFactory(new PropertyValueFactory<>("Nacionalidad"));
            
        playerstv = FXCollections.observableArrayList();
        ArrayList<PlayerDTO> players = Repository.GetPlayers(1);
        for (PlayerDTO player : players){
            PlayerTV2 ptv = new PlayerTV2();
            ptv.setId(player.getPlayerID());
            ptv.setAño(player.getPlayerBirth());
            ptv.setNacionalidad(player.getNacionality());
            ptv.setNombre(player.getPlayerName() + " " + player.getPlayerSurname());
            playerstv.add(ptv);
        }
        tbPlayer.setItems(playerstv);
        addButtonUpdateToTable();
        
    }

    private void addButtonUpdateToTable() {
        TableColumn<PlayerTV2, Void> colBtn = new TableColumn("Ver");
        colBtn.setStyle("-fx-background-color:  rgba(29, 33, 28, 0.84); -fx-border-color:  black; -fx-text-background-color: white");
        Callback<TableColumn<PlayerTV2, Void>, TableCell<PlayerTV2, Void>> cellFactory =
                new Callback<TableColumn<PlayerTV2, Void>, TableCell<PlayerTV2, Void>>() {
                    public TableCell<PlayerTV2, Void> call(final TableColumn<PlayerTV2, Void> param) {
                        final TableCell<PlayerTV2, Void> cell = new TableCell<PlayerTV2, Void>() {
                            private final CheckBox btn = new CheckBox("");
                            {
                                
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);

        tbPlayer.getColumns().add(colBtn);

    }

    @FXML
    private void OnAddClickListenner(ActionEvent event) {
    }
    
}

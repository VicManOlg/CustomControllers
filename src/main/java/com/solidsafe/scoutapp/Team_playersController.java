package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.util.Callback;
import model.ClubDTO;
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
    
    ObservableList<PlayerTV2> teamPlayersTV;
    
    PlayerTV dat;
    @FXML
    private TableColumn<PlayerTV2, SimpleIntegerProperty> rowIdTeam;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowNameTeam;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowAgeTeam;
    @FXML
    private TableColumn<PlayerTV2, SimpleStringProperty> rowNacionalityTeam;
    private TableColumn<PlayerTV2, SimpleDoubleProperty> rowPriceTeam;
    @FXML
    private TableView<PlayerTV2> tbTeamPlayers;
    
    private int idTeam;
    private ClubDTO club;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    private void setupTable1(){
        rowName.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        rowId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowYear.setCellValueFactory(new PropertyValueFactory<>("Año"));
        rowNacionality.setCellValueFactory(new PropertyValueFactory<>("Nacionalidad")); 
        playerstv = FXCollections.observableArrayList();
        ArrayList<PlayerDTO> players = Repository.GetPlayers(club.getClubId());
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
    
    private void setupTable(){
        rowIdTeam.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowNameTeam.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        rowAgeTeam.setCellValueFactory(new PropertyValueFactory<>("Año"));
        rowNacionalityTeam.setCellValueFactory(new PropertyValueFactory<>("Nacionalidad")); 

        
        teamPlayersTV = FXCollections.observableArrayList();
        ArrayList<PlayerDTO> players =  Repository.GetPlayersByTeam(this.idTeam);
        
        for (PlayerDTO player : players){
            PlayerTV2 ptv = new PlayerTV2();
            ptv.setId(player.getPlayerID());
            ptv.setAño(player.getPlayerBirth());
            ptv.setNacionalidad(player.getNacionality());
            ptv.setNombre(player.getPlayerName() + " " + player.getPlayerSurname());
            teamPlayersTV.add(ptv);
        }
        tbTeamPlayers.setItems(teamPlayersTV);
    }
    
    public void getTeam(int idTeam, ClubDTO c){
        this.idTeam = idTeam;
        this.club = c;
        setupTable1();
        setupTable();
    }

    private void addButtonUpdateToTable() {
        TableColumn<PlayerTV2, Void> colBtn = new TableColumn("Ver");
        colBtn.setStyle("-fx-background-color:  rgba(29, 33, 28, 0.84); -fx-border-color:  black; -fx-text-background-color: white");
        Callback<TableColumn<PlayerTV2, Void>, TableCell<PlayerTV2, Void>> cellFactory =
                new Callback<TableColumn<PlayerTV2, Void>, TableCell<PlayerTV2, Void>>() {
                    public TableCell<PlayerTV2, Void> call(final TableColumn<PlayerTV2, Void> param) {
                        final TableCell<PlayerTV2, Void> cell = new TableCell<PlayerTV2, Void>() {
                            private final Button btn = new Button("");
                            {    
                                Image image = new Image(getClass().getResource("/images/ic_arrow.png").toExternalForm());
                                ImageView iv = new ImageView(image);
                                iv.setFitHeight(40.0);
                                iv.setFitWidth(40.0);
                                btn.setGraphic(iv);
                                btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0)");
                                btn.setTooltip(
                                        new Tooltip("Editar")
                                );
                                btn.setOnAction((ActionEvent event) -> {
                                    try {
                                        PlayerTV2 dat = getTableView().getItems().get(getIndex());
                                        if(Messages.displayQuestion("Confirmacion", "Quieres añadir el jugador  "+dat.getNombre()+" al equipo?")){
                                            
                                            teamPlayersTV.add(dat);
                                            playerstv.remove(dat);
                                        }            
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                });
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

    private void OnAddClickListenner(ActionEvent event) {
        for(PlayerTV2 p : playerstv){
            
        }
        
    }
    
}

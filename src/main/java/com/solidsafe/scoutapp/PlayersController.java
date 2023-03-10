package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;       
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClubDTO;
import model.PlayerDTO;
import model.Repository;
import model.ScoutDTO;




/**
 * FXML Controller class
 *
 * @author Victor
 */
public class PlayersController implements Initializable {

    @FXML
    private Label lbClub;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivShield;
    @FXML
    private ComboBox<?> cbCat;
    @FXML
    private MFXTableView<PlayerDTO> tbPlayers;
    ObservableList<PlayerDTO> playersList;
    private ScoutDTO scoutDTO;
    private ClubDTO clubDTO;
    @FXML
    private MFXButton btnView;
    @FXML
    private MFXButton btnEdit;
    @FXML
    private MFXButton btnDelete;
    @FXML
    private MFXButton btnReview;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void callPlayer(ClubDTO club, ScoutDTO scout) {
        lbName.setText(scout.getScoutName() + " " + scout.getScoutSurname());
        scoutDTO = scout;
        clubDTO = club;
        Image m = new Image(club.getClubPhoto());
        ivShield.setImage(m);
        lbClub.setText(club.getClubName());
        setupTable();
    } 
    
    private void setupTable() {
        //pendiente posicion del jugador
            MFXTableColumn<PlayerDTO> columnId = new MFXTableColumn<>("ID", true, Comparator.comparing(PlayerDTO::getPlayerID));
            MFXTableColumn<PlayerDTO> columnName = new MFXTableColumn<>("Nombre", true, Comparator.comparing(PlayerDTO::getPlayerName));
            MFXTableColumn<PlayerDTO> columnSurName = new MFXTableColumn<>("Apellidos", true, Comparator.comparing(PlayerDTO::getPlayerSurname));
            MFXTableColumn<PlayerDTO> columnBirth = new MFXTableColumn<>("Nacimiento", true, Comparator.comparing(PlayerDTO::getPlayerBirth));
            MFXTableColumn<PlayerDTO> columnNacionality = new MFXTableColumn<>("Nacionalidad", true, Comparator.comparing(PlayerDTO::getNacionality));
            MFXTableColumn<PlayerDTO> columnPrice = new MFXTableColumn<>("Precio", true, Comparator.comparing(PlayerDTO::getPrice));
            MFXTableColumn<PlayerDTO> columnHeigth = new MFXTableColumn<>("Altura", true, Comparator.comparing(PlayerDTO::getHeigth));
            MFXTableColumn<PlayerDTO> columnWeight = new MFXTableColumn<>("Peso", true, Comparator.comparing(PlayerDTO::getWeight));
            
            columnId.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getPlayerID));
            columnName.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getPlayerName));
            columnSurName.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getPlayerSurname));
            columnBirth.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getPlayerBirth));
            columnNacionality.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getNacionality));
            columnPrice.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getPrice));
            columnHeigth.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getHeigth));
            columnWeight.setRowCellFactory(person -> new MFXTableRowCell<>(PlayerDTO::getWeight));
            columnSurName.setMinWidth(200);
            
            tbPlayers.getTableColumns().addAll(columnId, columnName, columnSurName, columnBirth, columnNacionality, columnPrice, columnHeigth, columnWeight);
            
            tbPlayers.getFilters().addAll(
                            new StringFilter<>("Nombre", PlayerDTO::getPlayerName),
                            new IntegerFilter<>("ID", PlayerDTO::getPlayerID),
                            new DoubleFilter<>("Precio", PlayerDTO::getPrice)
            );
            playersList = FXCollections.observableArrayList();
            ArrayList<PlayerDTO> players = Repository.GetPlayers(clubDTO.getClubId());
            for(PlayerDTO p : players){
               playersList.add(p);
            }
            tbPlayers.setItems(playersList);
            tbPlayers.getSelectionModel().selectionProperty().addListener((MapChangeListener<? super Integer, ? super PlayerDTO>) change -> {
                System.out.println(getTableSelection());
                btnView.setDisable(false);
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                btnReview.setDisable(false);
                
            });
	}
    private  PlayerDTO getTableSelection()
        {
            if (hasTableSelection()){
                return tbPlayers.getSelectionModel().getSelectedValues().get(0);
            }
            return null;
        }
        private boolean hasTableSelection()
        {
            return !tbPlayers.getSelectionModel().selectionProperty().isEmpty();
        }

    @FXML
    private void OnButtonAddClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        PlayerController pc = loader.getController();
        pc.addPlayer(clubDTO);
        stage.setScene(new Scene(root));
        stage.setTitle("Añadir Jugador");
        stage.setWidth(800);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        
        stage.show(); 
        
    }

    @FXML
    private void OnBtnViewClickListenner(ActionEvent event) throws IOException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        PlayerController pc = loader.getController();
        pc.displayPlayer(Repository.GetPlayer(getTableSelection().getPlayerID()));
        stage.setScene(new Scene(root));
        stage.setTitle("Informacion del Jugador");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.show();            
    }

    @FXML
    private void OnMenuPlayerClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        StartController stc = loader.getController();
        stc.displayName(scoutDTO, clubDTO);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        //stage.setMaximized(false);   
        stage.setWidth(1300);
        stage.setHeight(780);
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnMenuReviewClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("review.fxml"));
        Parent root = loader.load();
        ReviewController reviewController = loader.getController();
        reviewController.displayName(scoutDTO, clubDTO);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        //stage.setMaximized(false);   
        stage.setWidth(1300);
        stage.setHeight(780);
        stage.show();
    }

    @FXML
    private void OnCategoryClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
        Parent root = loader.load();
        CategoryController categoryController = loader.getController();
        categoryController.loadCategory(clubDTO, scoutDTO);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        stage.setWidth(1300);
        stage.setHeight(780);
        //stage.setMaximized(false);   
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnBtnReviewClickListenner(ActionEvent event) throws IOException {
        //setPlayer
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newreview.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        NewreviewController nr = loader.getController();
        nr.setPlayer(getTableSelection(), scoutDTO);
        stage.setScene(new Scene(root));
        stage.setTitle("Informacion del Jugador");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    private void OnBtnEditClickListenner(ActionEvent event) throws IOException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        PlayerController pc = loader.getController();
        pc.displayPlayer(Repository.GetPlayer(getTableSelection().getPlayerID()));
        stage.setScene(new Scene(root));
        stage.setTitle("Informacion del Jugador");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.show();   
    }
    
}

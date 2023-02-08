
package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.CategoryDTO;
import model.ClubDTO;
import model.PlayerDTO;
import model.Repository;
import model.ScoutDTO;
import model.TeamDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class StartController implements Initializable {


    @FXML
    private TableColumn<PlayerTV, SimpleIntegerProperty> rowName;
    @FXML
    private TableColumn<PlayerTV, SimpleStringProperty> rowPos;
      @FXML
    private ImageView ivShield;

    @FXML
    private Label lbClub;
    @FXML
    private Label lbName;
    
    ObservableList<PlayerTV> playerstv;
    ObservableList<Button> btsntv;
    PlayerTV dat;
    @FXML
    private ListView<Button> lvTeams;
    private TextField tfTeamName;
    @FXML
    private Label lbTeamName;
    @FXML
    private ImageView ivLogo;
    @FXML
    private Label lbIdTeam;
    @FXML
    private MFXButton btnEdit;
    @FXML
    private MFXButton btnDelete;
    @FXML
    private TableView<PlayerTV> tablePlayers;
    @FXML
    private TableColumn<PlayerTV, SimpleIntegerProperty> rowAge;
    @FXML
    private TableColumn<PlayerTV, SimpleIntegerProperty> rowId;
    @FXML
    private MFXTextField tfSearch;
    @FXML
    private ComboBox<CategoryDTO> cbCat;
    ObservableList<CategoryDTO> catdto;
    private ScoutDTO scoutDTO;
    private ClubDTO clubDTO;
    @FXML
    private MFXButton btnSquad;
    private int idTeam;
    @FXML
    private Label lbIdTeam1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<CategoryDTO> cats = Repository.GetCategories();
        catdto = FXCollections.observableArrayList();
        for (CategoryDTO cat : cats){
            catdto.add(cat);
        }
        cbCat.setItems(catdto);
        btnEdit.setVisible(false);
        tablePlayers.setVisible(false);
        btnDelete.setVisible(false);
        btnSquad.setVisible(false);
        lvTeams.setStyle("-fx-background-color: rgba(245, 39, 145, 0)");
        rowName.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        rowPos.setCellValueFactory(new PropertyValueFactory<>("Posicion"));
        rowId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowAge.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        
        playerstv = FXCollections.observableArrayList();
        btsntv = FXCollections.observableArrayList();
        addButtonDeleteToTable();
        addButtonUpdateToTable();   
        cbCat.setValue(cats.get(0));
        ArrayList<TeamDTO> teams = Repository.GetTeamsByCategory(1, cbCat.getSelectionModel().getSelectedItem().getCatName());
        for(TeamDTO t : teams){
            Image image = new Image(t.getPicture());
            if(image.getWidth() < 10){
                image =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
            }
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(60);
            TeamTV teamtv = new TeamTV();
            teamtv.setId(t.getTeamID());
            teamtv.setName(t.getTeamName());
            teamtv.setImg(imageView);
            
            Button btn = new Button(t.getTeamName());
                {
                    ImageView iv = new ImageView(image);
                    //Label lb1 = new Label("     "+t.getTeamName());
                    //lb1.setStyle("-fx-text-background-color: white;");
                    iv.setFitHeight(80.0);
                    iv.setFitWidth(65.0);
                    //lb1.setGraphic(iv);
                    //btn.setGraphic(lb1);
                    btn.setGraphic(iv);
                    btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0); -fx-text-fill: white;");
                    btn.setTooltip(
                            new Tooltip(t.getTeamName())
                    );
                    btn.setOnAction((ActionEvent event) -> {
                        playerstv.clear();
                        lbTeamName.setText(t.getTeamName());
                        lbIdTeam1.setText(t.getTeamID()+"");
                        lbIdTeam.setText("Identificador: ");
                        Image image1 = new Image(t.getPicture());
                        if(image1.getWidth() < 10){
                            image1 =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
                        }
                        ivLogo.setImage(image1);
                        btnEdit.setVisible(true);
                        btnDelete.setVisible(true);
                        tablePlayers.setVisible(true);
                        btnSquad.setVisible(true);
                        ArrayList<PlayerDTO> players = Repository.GetPlayersByTeam(t.getTeamID());
                        idTeam = t.getTeamID();
                        for(PlayerDTO p : players){
                            PlayerTV ptv = new PlayerTV();
                            ptv.setId(p.getPlayerID());
                            ptv.setNombre(p.getPlayerName() + " " + p.getPlayerSurname() );
                            ptv.setEdad(12);
                            ptv.setPosicion("ld");
                            playerstv.add(ptv);
                        }
                        tablePlayers.setItems(playerstv);
                              
                    });
                }
            btsntv.add(btn);                           
        }
        lvTeams.setItems(btsntv);
         
  
    }    
    public void displayName(ScoutDTO scout, ClubDTO c){
        lbName.setText(scout.getScoutName() + " " + scout.getScoutSurname());
        scoutDTO = scout;
        clubDTO = c;
        Image m = new Image(c.getClubPhoto());
        ivShield.setImage(m);
        lbClub.setText(c.getClubName());
    }
    
    private void addButtonDeleteToTable() {
        TableColumn<PlayerTV, Void> colBtn = new TableColumn("Eliminar");
        colBtn.setStyle("-fx-background-color:  rgba(29, 33, 28, 0.84); -fx-border-color:  black; -fx-text-background-color: white");
        Callback<TableColumn<PlayerTV, Void>, TableCell<PlayerTV, Void>> cellFactory =
                new Callback<TableColumn<PlayerTV, Void>, TableCell<PlayerTV, Void>>() {
                    public TableCell<PlayerTV, Void> call(final TableColumn<PlayerTV, Void> param) {
                        final TableCell<PlayerTV, Void> cell = new TableCell<PlayerTV, Void>() {
                            private final Button btn = new Button("");
                            {
                                Image image = new Image(getClass().getResource("/images/delete.png").toExternalForm());
                                ImageView iv = new ImageView(image);
                                iv.setFitHeight(40.0);
                                iv.setFitWidth(40.0);
                                btn.setGraphic(iv);
                                btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0)");
                                btn.setTooltip(
                                        new Tooltip("Eliminar")
                                );
                                btn.setOnAction((ActionEvent event) -> {
                                    

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

        tablePlayers.getColumns().add(colBtn);
    }
    
    private void addButtonUpdateToTable() {
        TableColumn<PlayerTV, Void> colBtn = new TableColumn("Ver");
        colBtn.setStyle("-fx-background-color:  rgba(29, 33, 28, 0.84); -fx-border-color:  black; -fx-text-background-color: white");
        Callback<TableColumn<PlayerTV, Void>, TableCell<PlayerTV, Void>> cellFactory =
                new Callback<TableColumn<PlayerTV, Void>, TableCell<PlayerTV, Void>>() {
                    public TableCell<PlayerTV, Void> call(final TableColumn<PlayerTV, Void> param) {
                        final TableCell<PlayerTV, Void> cell = new TableCell<PlayerTV, Void>() {
                            private final Button btn = new Button("");
                            {
                                Image image = new Image(getClass().getResource("/images/Search.png").toExternalForm());
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
                                        PlayerTV dat = getTableView().getItems().get(getIndex());
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
                                        Stage stage = new Stage();
                                        Parent root = loader.load();
                                        PlayerController pc = loader.getController();
                                        pc.displayPlayer(Repository.GetPlayer(dat.getId()));
                                        stage.setScene(new Scene(root));
                                        stage.setTitle("Informacion del Jugador");
                                        stage.initModality(Modality.WINDOW_MODAL);
                                        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                                        stage.show();               
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

        tablePlayers.getColumns().add(colBtn);

    }

    @FXML
    private void SearchClickListenner(ActionEvent event) {
        btsntv.clear();
        ArrayList<TeamDTO> teams = Repository.GetTeamsByCategoryName(1,tfSearch.getText(),cbCat.getSelectionModel().getSelectedItem().getCatName());
        for(TeamDTO t : teams){
            Image image = new Image(t.getPicture());
            if(image.getWidth() < 10){
                image =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
            }
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(60);
            TeamTV teamtv = new TeamTV();
            teamtv.setId(t.getTeamID());
            teamtv.setName(t.getTeamName());
            teamtv.setImg(imageView);
            
            Button btn = new Button(t.getTeamName());
                {
                    ImageView iv = new ImageView(image);
                    //Label lb1 = new Label("     "+t.getTeamName());
                    //lb1.setStyle("-fx-text-background-color: white;");
                    iv.setFitHeight(80.0);
                    iv.setFitWidth(65.0);
                    //lb1.setGraphic(iv);
                    //btn.setGraphic(lb1);
                    btn.setGraphic(iv);
                    btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0); -fx-text-fill: white;");
                    btn.setTooltip(
                            new Tooltip(t.getTeamName())
                    );
                    btn.setOnAction((ActionEvent event1) -> {
                        playerstv.clear();
                        lbTeamName.setText(t.getTeamName());
                        lbIdTeam1.setText(t.getTeamID()+"");
                        lbIdTeam.setText("Identificador: ");
                        Image image1 = new Image(t.getPicture());
                        if(image1.getWidth() < 10){
                            image1 =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
                        }
                        ivLogo.setImage(image1);
                        btnEdit.setVisible(true);
                        btnDelete.setVisible(true);
                        tablePlayers.setVisible(true);
                        btnSquad.setVisible(true);
                        ArrayList<PlayerDTO> players = Repository.GetPlayersByTeam(t.getTeamID());
                        idTeam = t.getTeamID();
                        for(PlayerDTO p : players){
                            PlayerTV ptv = new PlayerTV();
                            ptv.setId(p.getPlayerID());
                            ptv.setNombre(p.getPlayerName() + " " + p.getPlayerSurname() );
                            ptv.setEdad(12);
                            ptv.setPosicion("ld");
                            playerstv.add(ptv);
                        }
                        tablePlayers.setItems(playerstv);
                              
                    });
                }
            btsntv.add(btn);                           
        }
        lvTeams.setItems(btsntv);     
    }

    @FXML
    private void OnAddClickListenner(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
        TeamController.class.getResource("team.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Añadir Equipo");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    @FXML
    private void OnModifyClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("team.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        TeamController tc = loader.getController();
        TeamDTO t = new TeamDTO(Integer.parseInt(lbIdTeam1.getText()), lbTeamName.getText(), 1, ivLogo.getImage().getUrl());
        tc.UpdateTeam(t, clubDTO.getClubId());
        stage.setScene(new Scene(root));
        stage.setTitle("Modificar Equipo");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    @FXML
    private void OnReviwClickListenner(ActionEvent event) throws IOException {
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
        stage.setHeight(779);
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnPlayerClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("players.fxml"));
        Parent root = loader.load();
        PlayersController playersController = loader.getController();
        playersController.callPlayer(clubDTO, scoutDTO);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        //stage.setMaximized(false);   
        stage.setWidth(1300);
        stage.setHeight(779);
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnBtnSquadClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("team_players.fxml"));
        Parent root = loader.load();
        Team_playersController tpController = loader.getController();
        tpController.getTeam(idTeam, clubDTO);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        //stage.setMaximized(false);   
        stage.setWidth(1150);
        stage.setHeight(779);
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnSelectionChanged(ActionEvent event) {
        btsntv.clear();
        ArrayList<TeamDTO> teams = Repository.GetTeamsByCategoryName(1,tfSearch.getText(),cbCat.getSelectionModel().getSelectedItem().getCatName());
        for(TeamDTO t : teams){
            Image image = new Image(t.getPicture());
            if(image.getWidth() < 10){
                image =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
            }
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(60);
            TeamTV teamtv = new TeamTV();
            teamtv.setId(t.getTeamID());
            teamtv.setName(t.getTeamName());
            teamtv.setImg(imageView);
            
            Button btn = new Button(t.getTeamName());
                {
                    ImageView iv = new ImageView(image);
                    //Label lb1 = new Label("     "+t.getTeamName());
                    //lb1.setStyle("-fx-text-background-color: white;");
                    iv.setFitHeight(80.0);
                    iv.setFitWidth(65.0);
                    //lb1.setGraphic(iv);
                    //btn.setGraphic(lb1);
                    btn.setGraphic(iv);
                    btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0); -fx-text-fill: white;");
                    btn.setTooltip(
                            new Tooltip(t.getTeamName())
                    );
                    btn.setOnAction((ActionEvent event1) -> {
                        playerstv.clear();
                        lbTeamName.setText(t.getTeamName());
                        lbIdTeam1.setText(t.getTeamID()+"");
                        lbIdTeam.setText("Identificador: ");
                        Image image1 = new Image(t.getPicture());
                        if(image1.getWidth() < 10){
                            image1 =  new Image("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
                        }
                        ivLogo.setImage(image1);
                        btnEdit.setVisible(true);
                        btnDelete.setVisible(true);
                        btnSquad.setVisible(true);
                        tablePlayers.setVisible(true);
                        idTeam = t.getTeamID();
                        ArrayList<PlayerDTO> players = Repository.GetPlayersByTeam(t.getTeamID());
                        for(PlayerDTO p : players){
                            PlayerTV ptv = new PlayerTV();
                            ptv.setId(p.getPlayerID());
                            ptv.setNombre(p.getPlayerName() + " " + p.getPlayerSurname() );
                            ptv.setEdad(12);
                            ptv.setPosicion("ld");
                            playerstv.add(ptv);
                        }
                        tablePlayers.setItems(playerstv);
                              
                    });
                }
            btsntv.add(btn);                           
        }
        lvTeams.setItems(btsntv);     
    }
                
    
    
        
}

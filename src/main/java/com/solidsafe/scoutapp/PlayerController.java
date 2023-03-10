package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClubDTO;
import model.PlayerDTO;
import model.PositionDTO;
import model.Repository;
import model.TeamDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class PlayerController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfBirthdate;
    @FXML
    private TextField tfNacionality;
    @FXML
    private TextField tfHeigt;
    @FXML
    private TextField tfContact;
    @FXML
    private TextField tfWhigt;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfAgent;
    @FXML
    private TextField tfContactAgent;
    @FXML
    private TextField tfFamily;
    @FXML
    private TableView<TeamTV> tvTeams;
    @FXML
    private TableColumn<TeamTV, SimpleIntegerProperty> rowId;
    @FXML
    private TableColumn<TeamTV, ImageView> rowLogo;
    @FXML
    private TableColumn<TeamTV, SimpleStringProperty> rowName;
    ObservableList<TeamTV> teamsTV;
    ObservableList<PositionTV> positionsTV;
    @FXML
    private TableColumn<PositionTV, SimpleStringProperty> rowLeter;
    @FXML
    private TableColumn<PositionTV, SimpleStringProperty> rowPos;
    @FXML
    private TableView<PositionTV> tablePos;
    @FXML
    private Button btnAction;
    private ClubDTO club;
    @FXML
    private TextField tfSurname;
    private PlayerDTO player;
    @FXML
    private ImageView ivPhoto;
    @FXML
    private MFXTextField lbUrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * 
     * @param club 
     * Metod called for other stage when call this stage as a dialog for add a new player
     */
    public void addPlayer(ClubDTO club){
        this.club = club;
        tfPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    tfPrice.setText(oldValue);
                }
            }
        });
        tfHeigt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    tfHeigt.setText(oldValue);
                }
            }
        });tfWhigt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    tfWhigt.setText(oldValue);
                }
            }
        });
        //Allow edit every txtfild
        tfName.setEditable(true);
        tfId.setEditable(false);
        tfId.setText("0");
        tfAgent.setText("No");
        tfContactAgent.setText("No");
        tfFamily.setText("No");
        tfContact.setText("No");
        
        tfHeigt.setText("0");
        tfWhigt.setText("0");
        tfPrice.setText("0");
        
        tfId.setVisible(false);
        tfBirthdate.setEditable(true);
        tfNacionality.setEditable(true);
        tfHeigt.setEditable(true);
        tfWhigt.setEditable(true);
        tfContact.setEditable(true);
        tfPrice.setEditable(true);
        
        tfAgent.setEditable(true);
        tfContactAgent.setEditable(true);
        tfFamily.setEditable(true);
        tvTeams.setDisable(true);
        tvTeams.setVisible(false);
        tablePos.setDisable(true);
        tablePos.setVisible(false);
        //btnAction.setLayoutX(400);
        
    }
    public void displayPlayer(PlayerDTO p) throws ParseException{
        this.player = p;
        Image playerPhoto = new Image(p.getPhoto());
        ivPhoto.setImage(playerPhoto);
        btnAction.setVisible(false);
        rowName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        rowLogo.setCellValueFactory(new PropertyValueFactory<>("Img"));
        rowId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowLeter.setCellValueFactory(new PropertyValueFactory<>("Leters"));
        rowPos.setCellValueFactory(new PropertyValueFactory<>("Name"));
        teamsTV = FXCollections.observableArrayList();
        positionsTV = FXCollections.observableArrayList();
        tfId.setText(p.getPlayerID()+"");
        tfName.setEditable(false);
        tfSurname.setEditable(false);
        tfId.setEditable(false);
        tfBirthdate.setEditable(true);
        tfNacionality.setEditable(false);
        tfHeigt.setEditable(false);
        tfWhigt.setEditable(false);
        tfContact.setEditable(false);
        tfPrice.setEditable(false);
        tfAgent.setEditable(false);
        tfContactAgent.setEditable(false);
        tfFamily.setEditable(false);
        tfName.setText(p.getPlayerName());
        tfBirthdate.setEditable(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(p.getPlayerBirth(), formatter);
        tfBirthdate.setText(localDate.toString());
        tfNacionality.setText(p.getNacionality());
        tfHeigt.setText(p.getHeigth() + "");
        tfWhigt.setText(p.getWeight() + "");
        tfContact.setText(p.getContact());
        tfPrice.setText(p.getPrice() + "");
        tfSurname.setText(p.getPlayerSurname());
        tfAgent.setText(p.getAgent());
        tfContactAgent.setText(p.getContactAgent());
        tfFamily.setText(p.getContactFamily());
        ArrayList<TeamDTO> teams = Repository.GetPlayerTeams(p.getPlayerID());
        for(TeamDTO t : teams){
            TeamTV teamTV = new TeamTV();

            teamTV.setId(t.getTeamID());
            teamTV.setName(t.getTeamName());
            Image image = new Image(t.getPicture());
            ImageView logo = new ImageView(image);
            logo.setFitHeight(40);
            logo.setFitWidth(35);
            teamTV.setImg(logo);
            teamsTV.add(teamTV);
        }
        ArrayList<PositionDTO> positions = Repository.GetPlayerPositions(p.getPlayerID());
        for(PositionDTO pos : positions){
            PositionTV posTV = new PositionTV();
            posTV.setId(pos.getPosID());
            posTV.setLeters(pos.getLeters());
            posTV.setName(pos.getPosName());
            positionsTV.add(posTV);
        }
        tvTeams.setItems(teamsTV);
        tablePos.setItems(positionsTV);
        
    }
    public void updatePlayer(PlayerDTO p) throws ParseException{
        this.player = p;
        Image playerPhoto = new Image(p.getPhoto());
        ivPhoto.setImage(playerPhoto);
        rowName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        rowLogo.setCellValueFactory(new PropertyValueFactory<>("Img"));
        rowId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        rowLeter.setCellValueFactory(new PropertyValueFactory<>("Leters"));
        rowPos.setCellValueFactory(new PropertyValueFactory<>("Name"));
        teamsTV = FXCollections.observableArrayList();
        positionsTV = FXCollections.observableArrayList();
        tfId.setText(p.getPlayerID()+"");
        tfName.setEditable(false);
        tfSurname.setEditable(false);
        tfId.setEditable(false);
        tfBirthdate.setEditable(true);
        tfNacionality.setEditable(false);
        tfHeigt.setEditable(false);
        tfWhigt.setEditable(false);
        tfContact.setEditable(false);
        tfPrice.setEditable(false);
        tfAgent.setEditable(false);
        tfContactAgent.setEditable(false);
        tfFamily.setEditable(false);
        tfName.setText(p.getPlayerName());
        tfBirthdate.setEditable(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(p.getPlayerBirth(), formatter);
        tfBirthdate.setText(localDate.toString());
        tfNacionality.setText(p.getNacionality());
        tfHeigt.setText(p.getHeigth() + "");
        tfWhigt.setText(p.getWeight() + "");
        tfContact.setText(p.getContact());
        tfPrice.setText(p.getPrice() + "");
        tfSurname.setText(p.getPlayerSurname());
        tfAgent.setText(p.getAgent());
        tfContactAgent.setText(p.getContactAgent());
        tfFamily.setText(p.getContactFamily());
        ArrayList<TeamDTO> teams = Repository.GetPlayerTeams(p.getPlayerID());
        for(TeamDTO t : teams){
            TeamTV teamTV = new TeamTV();

            teamTV.setId(t.getTeamID());
            teamTV.setName(t.getTeamName());
            Image image = new Image(t.getPicture());
            ImageView logo = new ImageView(image);
            logo.setFitHeight(40);
            logo.setFitWidth(35);
            teamTV.setImg(logo);
            teamsTV.add(teamTV);
        }
        ArrayList<PositionDTO> positions = Repository.GetPlayerPositions(p.getPlayerID());
        for(PositionDTO pos : positions){
            PositionTV posTV = new PositionTV();
            posTV.setId(pos.getPosID());
            posTV.setLeters(pos.getLeters());
            posTV.setName(pos.getPosName());
            positionsTV.add(posTV);
        }
        tvTeams.setItems(teamsTV);
        tablePos.setItems(positionsTV);
        btnAction.setText("Modificar");
    }
    
    

    @FXML
    private void OnAddClickListenner(ActionEvent event) throws IOException {
       String photo = lbUrl.getText();
        Repository repo = new Repository();
        if(tfName.getText().equals("") || tfSurname.getText().equals("") ){
            Messages.displayError("Error", "Error nombre, apellido o edad son campos obligatorios");
        }
        else{
            if(btnAction.getText().equalsIgnoreCase("Añadir")){
                if(photo.equals("")){
                    photo = "https://s3.ppllstatics.com/laverdad/www/pre2017/multimedia/RC/201501/12/media/cortadas/avatar--320x378.jpg";
                }
                PlayerDTO player = new PlayerDTO(0, tfNacionality.getText(), Double.parseDouble(tfHeigt.getText()), Double.parseDouble(tfWhigt.getText()), Double.parseDouble(tfPrice.getText()), tfContact.getText(), tfAgent.getText(), tfContactAgent.getText(), tfFamily.getText(), photo);
                player.setContract(" ");
                player.setPlayerName(tfName.getText());
                player.setPlayerSurname(tfSurname.getText());
                player.setPlayerBirth(tfBirthdate.getText());
                if(repo.addPlayer(player, club.getClubId())){
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                }
            } 
        }
    }

    @FXML
    private void OnButtonAddPosClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("positions.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        PositionsController pc = loader.getController();
        pc.setPlayer(this.player);
        stage.setScene(new Scene(root));
        stage.setTitle("");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    
}

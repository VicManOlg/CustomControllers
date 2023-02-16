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
    private MFXTextField tfId;
    @FXML
    private MFXTextField tfName;
    @FXML
    private DatePicker tfBirthdate;
    @FXML
    private MFXTextField tfNacionality;
    @FXML
    private MFXTextField tfHeigt;
    @FXML
    private MFXTextField tfContact;
    @FXML
    private MFXTextField tfWhigt;
    @FXML
    private MFXTextField tfPrice;
    @FXML
    private MFXTextField tfAgent;
    @FXML
    private MFXTextField tfContactAgent;
    @FXML
    private MFXTextField tfFamily;
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
    private MFXTextField tfSurname;
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
        
        //Allow edit every txtfild
        tfName.setAllowEdit(true);
        tfId.setAllowEdit(true);    
        tfBirthdate.setEditable(true);
        tfNacionality.setAllowEdit(true);
        tfHeigt.setAllowEdit(true);
        tfWhigt.setAllowEdit(true);
        tfContact.setAllowEdit(true);
        tfPrice.setAllowEdit(true);
        tfAgent.setAllowEdit(true);
        tfContactAgent.setAllowEdit(true);
        tfFamily.setAllowEdit(true);
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
        tfName.setAllowEdit(false);
        tfSurname.setAllowEdit(false);
        tfId.setAllowEdit(false);
        tfBirthdate.setEditable(true);
        tfNacionality.setAllowEdit(false);
        tfHeigt.setAllowEdit(false);
        tfWhigt.setAllowEdit(false);
        tfContact.setAllowEdit(false);
        tfPrice.setAllowEdit(false);
        tfAgent.setAllowEdit(false);
        tfContactAgent.setAllowEdit(false);
        tfFamily.setAllowEdit(false);
        tfName.setText(p.getPlayerName());
        tfBirthdate.setEditable(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(p.getPlayerBirth(), formatter);
        tfBirthdate.setValue(localDate);
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

    @FXML
    private void OnAddClickListenner(ActionEvent event) throws IOException {
        Repository repo = new Repository();
        if(btnAction.getText().equalsIgnoreCase("Añadir")){
            PlayerDTO player = new PlayerDTO(0, tfNacionality.getText(), Double.parseDouble(tfHeigt.getText()), Double.parseDouble(tfWhigt.getText()), Double.parseDouble(tfPrice.getText()), tfContact.getText(), tfAgent.getText(), tfContactAgent.getText(), tfFamily.getText(), lbUrl.getText());
            player.setContract(" ");
            player.setPlayerName(tfName.getText());
            player.setPlayerSurname(tfSurname.getText());
            player.setPlayerBirth(tfBirthdate.getValue().toString());
            if(repo.addPlayer(player, club.getClubId())){
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
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

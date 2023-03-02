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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.CategoryDTO;
import model.Repository;
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
    private int idTeam;
    private int idClub;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void OnSearchUrlClickListenner(ActionEvent event) {
        Image image = new Image(tfUrl.getText());
        ivUrl.setImage(image);       
        
        

    }
    public void loadComboBox(int id){
        List<CategoryDTO> cats = Repository.GetCategories(id);
        catdto = FXCollections.observableArrayList();
        for (CategoryDTO cat : cats){
            catdto.add(cat);
        }
        tfCat.setItems(catdto);
    }

    @FXML
    private void OnBtnAddClickListenner(ActionEvent event) throws IOException {
        boolean exit = false;
        if(btnAdd.getText().equals("Modificar")){
            TeamDTO t = new TeamDTO(idTeam, tfName.getText(), tfCat.getSelectedIndex() + 1, tfUrl.getText()); 
            Repository r = new Repository();
            r.updateTeam(t, idClub);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            
        }
        else{
            TeamDTO t = new TeamDTO(0, tfName.getText(), tfCat.getSelectedIndex() + 1, tfUrl.getText()); 
            Repository r = new Repository();
            exit = r.sendPOST(t, 1);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            
        }
        
    }
    
    public void UpdateTeam(TeamDTO tdto, int club){
        tfName.setText(tdto.getTeamName());
        tfUrl.setText(tdto.getPicture());
        loadComboBox(club);
        tfCat.selectIndex(tdto.getCatID() - 1);
        Image image = new Image(tfUrl.getText());
        btnAdd.setText("Modificar");
        ivUrl.setImage(image); 
        idTeam = tdto.getTeamID();
        idClub = club;
        
    }
    

    
}

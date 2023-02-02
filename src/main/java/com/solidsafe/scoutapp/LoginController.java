package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ClubDTO;
import model.Repository;
import model.ScoutDTO;
/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginController implements Initializable {


    @FXML
    private AnchorPane gridPane;
    @FXML
    private AnchorPane gridPane2;
    @FXML
    private MFXButton btnLogin;
    @FXML
    private MFXTextField tfUser;
    @FXML
    private MFXPasswordField tfPass;   
    @FXML
    private Label lError;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnBtnLoginClickListenner(ActionEvent event) throws IOException {
        ScoutDTO scout = Repository.login(tfUser.getText(), tfPass.getText());
        if(scout != null){
            ClubDTO cdto = Repository.GetClub(scout.getClubId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
                root = loader.load();
                StartController startController = loader.getController();
                startController.displayName(scout, cdto);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                //stage.setMaximized(false);      
                stage.show();
                //stage.setMaximized(true);
                stage.setWidth(1150);
                stage.setHeight(779);
                //stage.setFullScreen(false);
        }
        else{
            lError.setText("Error revisa tus credenciales");
            
        }
        
    }

    @FXML
    private void OnKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode().toString().equals("ENTER")){
            ScoutDTO scout = Repository.login(tfUser.getText(), tfPass.getText());
        if(scout != null){
            ClubDTO cdto = Repository.GetClub(scout.getClubId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
                root = loader.load();
                StartController startController = loader.getController();
                startController.displayName(scout, cdto);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                //stage.setMaximized(false);      
                stage.show();
                //stage.setMaximized(true);
                stage.setWidth(1150);
                stage.setHeight(779);
                //stage.setFullScreen(false);
        }
        else{
            lError.setText("Error revisa tus credenciales");
            
        }
        }
    }
    
}

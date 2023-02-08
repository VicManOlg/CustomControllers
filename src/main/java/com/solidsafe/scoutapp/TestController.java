
package com.solidsafe.scoutapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class TestController implements Initializable {

    @FXML
    private ListView<Button> lv;
    private ObservableList<Button> items = FXCollections.observableArrayList();
    @FXML
    private Label lb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lv.setItems(items);
        for(int i = 0; i < 5; i++){
            Button btn = new Button("Team Name");
                {
                    Image image = new Image(getClass().getResource("/images/edit1.png").toExternalForm());
                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(40.0);
                    iv.setFitWidth(40.0);
                    btn.setGraphic(iv);
                    btn.setStyle("-fx-background-color: rgba(245, 39, 145, 0)");
                    btn.setTooltip(
                            new Tooltip("Editar")
                    );
                    btn.setOnAction((ActionEvent event) -> {
                         
                                 
                    });
                }
            items.add(btn);
        }       
    }    
    
}

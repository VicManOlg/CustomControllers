package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.CategoryDTO;
import model.ClubDTO;
import model.PlayerDTO;
import model.Repository;
import model.ScoutDTO;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class CategoryController implements Initializable {
    
    private ClubDTO club;
    private ArrayList<CategoryDTO> categories; 
    @FXML
    private MFXTableView<CategoryDTO> tbCategory;
    ObservableList<CategoryDTO> categoryList;
    @FXML
    private MFXTableColumn<CategoryDTO> trowName;
    @FXML
    private TextField tfCategory;
    @FXML
    private Label lbClub;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivShield;
    private ScoutDTO scout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void loadCategory(ClubDTO clubDTO, ScoutDTO scout){
        this.club = clubDTO;
        this.scout = scout;
        categories = Repository.GetCategories(club.getClubId());
        setupTable();
        
        
    }
    private void setupTable() {
        //pendiente posicion del jugador
            
            MFXTableColumn<CategoryDTO> columnId = new MFXTableColumn<>("ID", true, Comparator.comparing(CategoryDTO::getCatId));
            trowName = new MFXTableColumn<>("Nombre", true, Comparator.comparing(CategoryDTO::getCatName));
            
            
            
            columnId.setRowCellFactory(person -> new MFXTableRowCell<>(CategoryDTO::getCatId));
            trowName.setRowCellFactory(person -> new MFXTableRowCell<>(CategoryDTO::getCatName));


            tbCategory.getTableColumns().addAll(columnId, trowName);
            

            categoryList = FXCollections.observableArrayList();
            for(CategoryDTO cat : categories){
               categoryList.add(cat);
            }
            tbCategory.setItems(categoryList);
            tbCategory.getSelectionModel().selectionProperty().addListener((MapChangeListener<? super Integer, ? super CategoryDTO>) change -> {
                System.out.println(getTableSelection());
                
                
            });
	}
    private  CategoryDTO getTableSelection()
        {
            if (hasTableSelection()){
                return tbCategory.getSelectionModel().getSelectedValues().get(0);
            }
            return null;
        }
        private boolean hasTableSelection()
        {
            return !tbCategory.getSelectionModel().selectionProperty().isEmpty();
        }

    @FXML
    private void OnBtnAddClickListenner(ActionEvent event) throws IOException {
        CategoryDTO catDTO = new CategoryDTO(0,tfCategory.getText());
        Repository repo = new Repository();
        repo.addCategory(catDTO ,this.club.getClubId());
    }

    @FXML
    private void OnPlayerClickListenner(ActionEvent event) {
    }

    @FXML
    private void OnReviwClickListenner(ActionEvent event) {
    }

    @FXML
    private void OnBtnTeamsClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        StartController stc = loader.getController();
        stc.displayName(scout, club);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(false);    
        stage.setScene(scene);
        //stage.setMaximized(false);   
        stage.setWidth(1300);
        stage.setHeight(781);
        stage.show();
    }
    
}

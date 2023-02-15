package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.CategoryDTO;
import model.ClubDTO;
import model.PlayerDTO;
import model.Repository;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void loadCategory(ClubDTO clubDTO){
        this.club = clubDTO;
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
    
}

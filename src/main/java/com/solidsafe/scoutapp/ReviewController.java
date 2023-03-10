package com.solidsafe.scoutapp;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import java.io.IOException;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import model.ClubDTO;
import model.Repository;
import model.ReviewDTO;
import model.ScoutDTO;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Victor
 */
public class ReviewController implements Initializable {

    @FXML
    private Label lbClub;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivShield;
    @FXML
    private ComboBox<?> cbCat;
    private ScoutDTO scoutDTO;
    private ClubDTO clubDTO;
    ObservableList<ReviewDTO> reviewstv;
    @FXML
    private MFXTableView<ReviewDTO> idCode;
    @FXML
    private BarChart<String, Number> chartStats;
    private TextArea taComments;
    @FXML
    private PieChart chartPie;
    @FXML
    private Label lbComm;
    @FXML
    private AreaChart<String, Number> sachart;
    @FXML
    private TextArea txArea;
    private int count = 0;
    ArrayList<Integer> indexList = new ArrayList<Integer>();
    @FXML
    private AnchorPane anchorPaneId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //idCode.setStyle("-fx-text-fill: white");
        
        
        
    } 
    public void displayName(ScoutDTO scout, ClubDTO c){
        lbName.setText(scout.getScoutName() + " " + scout.getScoutSurname());
        scoutDTO = scout;
        clubDTO = c;
        Image m = new Image(c.getClubPhoto());
        ivShield.setImage(m);
        lbClub.setText(c.getClubName());
        reviewstv = FXCollections.observableArrayList();
        ArrayList<ReviewDTO> teams = Repository.GetScoutReviews(scoutDTO.getScoutID());
        for(ReviewDTO t : teams){
           reviewstv.add(t);
        }
        setupTable();  
    }
    
    private void setupTable() {
        //pendiente posicion del jugador
            MFXTableColumn<ReviewDTO> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(ReviewDTO::getReviewId));
            MFXTableColumn<ReviewDTO> nameColumn = new MFXTableColumn<>("Jugador", true, Comparator.comparing(ReviewDTO::getPlayerName));
            MFXTableColumn<ReviewDTO> dateColumn = new MFXTableColumn<>("Fecha", true, Comparator.comparing(ReviewDTO::getDate));
            MFXTableColumn<ReviewDTO> levelColumn = new MFXTableColumn<>("Nivel", true, Comparator.comparing(ReviewDTO::getLevel));

            nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ReviewDTO::getPlayerName));
            idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ReviewDTO::getReviewId));
            dateColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ReviewDTO::getDate));
            levelColumn.setRowCellFactory(person -> new MFXTableRowCell<>(ReviewDTO::getLevel));

            idCode.getTableColumns().addAll(nameColumn, idColumn, dateColumn, levelColumn);

            idCode.getFilters().addAll(
                            new StringFilter<>("Jugador", ReviewDTO::getPlayerName),
                            new IntegerFilter<>("ID", ReviewDTO::getReviewId),
                            new StringFilter<>("Nivel", ReviewDTO::getLevel)
            );

            idCode.setItems(reviewstv);
            idCode.getSelectionModel().selectionProperty().addListener((MapChangeListener<? super Integer, ? super ReviewDTO>) change -> {
                count  = count +1;
                System.out.println(getTableSelection());
                
                
                chartPie.getData().clear();
                ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Tecnica", getTableSelection().getTechnical()),
                new PieChart.Data("Mental", getTableSelection().getMental()),
                new PieChart.Data("Fisico", getTableSelection().getPhisic()),
                new PieChart.Data("Tactico", getTableSelection().getTactical()),
                new PieChart.Data("Inteligencia", getTableSelection().getIntelligence()));
                chartPie.getData().addAll(pieChartData);
                chartPie.setTitle("Nivel de habilidad");
                lbComm.setText(getTableSelection().getComments());
                txArea.setText(getTableSelection().getComments());
                
                if(count >= 6){
                    sachart.getData().clear();
                    indexList.clear();
                    
                    count = 0;
                }
                //sachart.getData().clear();
                XYChart.Series seriesApril = new XYChart.Series();  
                seriesApril.setName("Valoracion " + getTableSelection().getReviewId());
                seriesApril.getData().add(new XYChart.Data("Tecnica", getTableSelection().getTechnical()));
                seriesApril.getData().add(new XYChart.Data("Mental", getTableSelection().getMental()));
                seriesApril.getData().add(new XYChart.Data("Fisico", getTableSelection().getPhisic()));
                seriesApril.getData().add(new XYChart.Data("Tactico", getTableSelection().getTactical()));
                seriesApril.getData().add(new XYChart.Data("Inteligencia", getTableSelection().getIntelligence()));
                boolean comfirm = false;
                for (Integer i : indexList){ 
                    if(i == getTableSelection().getReviewId()){
                        comfirm = true;
                    } 
                }
                if(!comfirm){
                    sachart.getData().addAll(seriesApril);
                    indexList.add(getTableSelection().getReviewId());
                }
                //sachart.getData().add(seriesApril);      
            });
	}
        private  ReviewDTO getTableSelection()
        {
            if (hasTableSelection()){
                return idCode.getSelectionModel().getSelectedValues().get(0);
            }
            return null;
        }
        private boolean hasTableSelection()
        {
            return !idCode.getSelectionModel().selectionProperty().isEmpty();
        }

    @FXML
    private void OnMouseClicked(MouseEvent event) throws IOException {
        
    }

    @FXML
    private void OnBtnAddClickListenner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newreview.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        NewreviewController nrc = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Nueva Valoracion");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        
        stage.show(); 
    }

    @FXML
    private void OnMenuTeamlickListenner(ActionEvent event) throws IOException {
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
        stage.setHeight(781);
        stage.show();
        //stage.setMaximized(true);
    }

    @FXML
    private void OnMenuPlayerClickListenner(ActionEvent event) throws IOException {
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
        stage.setHeight(781);
        stage.show();
        //stage.setMaximized(true);
    }
    public ArrayList<double[]> GetPoints(int nSides, int centerX, int centerY, double radius){
        ArrayList<double[]> points = new ArrayList<double[]>();
        double[] pointsX = new double[nSides];
        double[] pointsY = new double[nSides];
        if(nSides >= 3){
            for (int n = 0; n < nSides; n++){
                double pointX = centerX +(radius * cos(55+(2 * PI*n / nSides)));
                double pointY = centerY +(radius * sin(55+(2 * PI*n / nSides)));
                pointsX[n] = pointX;
                pointsY[n] = pointY;
            }
            points.add(pointsX);
            points.add(pointsY);  
        }
        return points;
    }
    public ArrayList<double[]> GetPointsLevel(int nSides, int centerX, int centerY, double radius, double[] levels){
        ArrayList<double[]> points = new ArrayList<double[]>();
        double[] pointsX = new double[nSides];
        double[] pointsY = new double[nSides];
        if(nSides >= 3){
            for (int n = 0; n < nSides; n++){
                double pointX = centerX +(levels[n] * cos(55+(2 * PI*n / nSides)));
                double pointY = centerY +(levels[n] * sin(55+(2 * PI*n / nSides)));
                pointsX[n] = pointX;
                pointsY[n] = pointY;
            }
            points.add(pointsX);
            points.add(pointsY);  
        }
        return points;
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
        stage.setHeight(781);
        //stage.setMaximized(false);   
        stage.show();
        //stage.setMaximized(true);
    }


    



    
}

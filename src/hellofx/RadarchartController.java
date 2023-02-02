package hellofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Polyline;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class RadarchartController implements Initializable {

    @FXML
    private Polyline triangle2;
    @FXML
    private Polyline triangle1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public Polyline getTriangle1() {
        return triangle1;
    }
    public Polyline getTriangle2() {
        return triangle2;
    }
}










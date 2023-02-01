package hellofx;

import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Victor
 */
public class CustomRadarChartControl extends AnchorPane
{
    
    RadarchartController controller;
    ObjectProperty<Paint> arrowColor = new SimpleObjectProperty<>(Color.YELLOW);
    
    public CustomRadarChartControl()
    {
        super();
        
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("arrow.fxml"));
            
            controller = new RadarchartController();
            loader.setController(controller);
            
            Node n = loader.load();
            
            this.getChildren().add(n);

        }
        catch(IOException ix)
        {
            
        }
    }

    public RadarchartController getController() {
        return controller;
    }



    public ObjectProperty<Paint> getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(ObjectProperty<Paint> arrowColor) {
        this.arrowColor = arrowColor;
    }
    
}

package com.solidsafe.scoutapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PrimaryController {
    @FXML
    private ListView<?> lv;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
 
}

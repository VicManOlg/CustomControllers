module com.solidsafe.scoutapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires MaterialFX;
    requires json.simple;
    requires okhttp3;
    


    opens com.solidsafe.scoutapp to javafx.fxml;
    exports com.solidsafe.scoutapp;
}

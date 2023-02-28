module com.solidsafe.scoutapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires MaterialFX;
    requires json.simple;
    requires okhttp3;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    


    opens com.solidsafe.scoutapp to javafx.fxml;
    exports com.solidsafe.scoutapp;
}

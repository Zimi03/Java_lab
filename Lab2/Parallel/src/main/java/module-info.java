module org.example.parallel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.parallel to javafx.fxml;
    exports org.example.parallel;
}
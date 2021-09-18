module com.hairsoft.hairsoft {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.hairsoft.hairsoft to javafx.fxml;
    exports com.hairsoft.hairsoft;
}
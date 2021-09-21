module com.hairsoft.hairsoft {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires charm.glisten;

    opens com.hairsoft.hairsoft to javafx.fxml;
    exports com.hairsoft.hairsoft;
    exports com.hairsoft.controller;
    exports com.hairsoft.entity;
    exports com.hairsoft.dialog;
    opens com.hairsoft.controller to javafx.fxml;
}
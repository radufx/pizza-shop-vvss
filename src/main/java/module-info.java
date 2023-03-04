module srir3010MV {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens srir3010MV.model to javafx.base;
    exports srir3010MV.model;
    opens srir3010MV to javafx.fxml;
    exports srir3010MV;
    opens srir3010MV.controller to javafx.fxml;
    exports srir3010MV.controller;
}
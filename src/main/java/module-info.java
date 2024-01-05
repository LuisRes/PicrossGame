module picross.picrossgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens picross to javafx.fxml;
    exports picross;
}
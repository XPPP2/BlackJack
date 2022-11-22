module com.igame.blackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.igame.blackjack to javafx.fxml;
    exports com.igame.blackjack;
}
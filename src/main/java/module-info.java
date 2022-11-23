module com.game.blackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.blackjack to javafx.fxml;
    exports com.game.blackjack;
}
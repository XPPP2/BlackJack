module com.game.blackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.game.blackjack to javafx.fxml;
    opens com.game.blackjack.controllers to javafx.fxml;
    exports com.game.blackjack;
}
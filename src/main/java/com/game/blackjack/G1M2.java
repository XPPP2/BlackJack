package com.game.blackjack;

import com.game.blackjack.fixedObjects.Medias;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class G1M2{
    private static Stage stage;
    private static int tscore=0;

    public static int getTscore() {
        return tscore;
    }

    public static void setTscore(int tscore) {
        G1M2.tscore = tscore;
    }

    public static void closeStage() {
        stage.close();
    }

    public static void setStage(Stage stage) {
        G1M2.stage = stage;
    }

    public void goG1M2() throws Exception{
        setStage(new Stage());
        FXMLLoader fx = new FXMLLoader(getClass().getResource("G1M2.fxml"));

        Scene sc = new Scene(fx.load());
        Medias.mp.setCycleCount(MediaPlayer.INDEFINITE);
        Medias.mp.play();

        stage.setTitle("Black Jack Mode 3");
        stage.setScene(sc);
        stage.show();
    }

}

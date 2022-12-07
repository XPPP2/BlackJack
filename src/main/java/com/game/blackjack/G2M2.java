package com.game.blackjack;

import com.game.blackjack.controllers.G1M2Controller;
import com.game.blackjack.fixedObjects.Medias;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class G2M2 {
    private static Stage stage;
    private static int tscore;
    public static int getTscore() {
        return tscore;
    }
    public static void setTscore(int tscore) {
        G2M2.tscore = tscore;
    }
    private static int tscoreP2;
    private static int tscoreP3;
    public static int getTscoreP2() {
        return tscoreP2;
    }
    public static void setTscoreP2(int tscoreP2) {
        G2M2.tscoreP2 = tscoreP2;
    }
    public static int getTscoreP3() {
        return tscoreP3;
    }
    public static void setTscoreP3(int tscoreP3) {
        G2M2.tscoreP3 = tscoreP3;
    }
    public static int getTscoreP4() {
        return tscoreP4;
    }
    public static void setTscoreP4(int tscoreP4) {
        G2M2.tscoreP4 = tscoreP4;
    }
    private static int tscoreP4;

    public static void closeStage() {
        stage.close();
    }

    public static void setStage(Stage stage) {
        G2M2.stage = stage;
    }

    public void goG2M2() throws Exception {
        setStage(new Stage());
        FXMLLoader fx = new FXMLLoader(getClass().getResource("G2M2.fxml"));
        G1M2Controller g1m2c = new G1M2Controller();
        g1m2c.setTscore();

        try (Scanner finp = new Scanner(new File("G2M2score.txt"))) {
            if (finp.hasNextInt()) {
                G2M2.setTscore(finp.nextInt());
            } else {
                G2M2.tscore = 0;
            }
            if(finp.hasNextInt()){
                G2M2.setTscoreP2(finp.nextInt());
            }else{
                G2M2.tscoreP2=0;
            }
            if(finp.hasNextInt()){
                G2M2.setTscoreP3(finp.nextInt());
            }else{
                G2M2.tscoreP3=0;
            }
            if(finp.hasNextInt()){
                G2M2.setTscoreP4(finp.nextInt());
            }else{
                G2M2.tscoreP4=0;
            }
        } catch (FileNotFoundException ex) {
            G2M2.tscore = 0;G2M2.tscoreP2=0;G2M2.tscoreP3=0;G2M2.tscoreP4=0;
        }
        Scene sc = new Scene(fx.load());
        Medias.mp.setCycleCount(MediaPlayer.INDEFINITE);
        Medias.mp.play();

        stage.setTitle("Blackjack Mode 2");
        stage.setScene(sc);
        stage.show();
    }

}

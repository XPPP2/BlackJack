package com.game.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChooseMode extends Application{
    //图片们
    private final Image la = new Image(getClass().getResourceAsStream("/image/la.png"));
    private final Image l2 = new Image(getClass().getResourceAsStream("/image/l2.png"));
    private final Image l3 = new Image(getClass().getResourceAsStream("/image/l3.png"));
    private final Image l4 = new Image(getClass().getResourceAsStream("/image/l4.png"));
    private final Image l5 = new Image(getClass().getResourceAsStream("/image/l5.png"));
    private final Image l6 = new Image(getClass().getResourceAsStream("/image/l6.png"));
    private final Image l7 = new Image(getClass().getResourceAsStream("/image/l7.png"));
    private final Image l8 = new Image(getClass().getResourceAsStream("/image/l8.png"));
    private final Image l9 = new Image(getClass().getResourceAsStream("/image/l9.png"));
    private final Image l10 = new Image(getClass().getResourceAsStream("/image/l10.png"));
    private final Image lj = new Image(getClass().getResourceAsStream("/image/lj.png"));
    private final Image lq = new Image(getClass().getResourceAsStream("/image/lq.png"));
    private final Image lk = new Image(getClass().getResourceAsStream("/image/lk.png"));
    private final Image pa = new Image(getClass().getResourceAsStream("/image/pa.png"));
    private final Image p2 = new Image(getClass().getResourceAsStream("/image/p2.png"));
    private final Image p3 = new Image(getClass().getResourceAsStream("/image/p3.png"));
    private final Image p4 = new Image(getClass().getResourceAsStream("/image/p4.png"));
    private final Image p5 = new Image(getClass().getResourceAsStream("/image/p5.png"));
    private final Image p6 = new Image(getClass().getResourceAsStream("/image/p6.png"));
    private final Image p7 = new Image(getClass().getResourceAsStream("/image/p7.png"));
    private final Image p8 = new Image(getClass().getResourceAsStream("/image/p8.png"));
    private final Image p9 = new Image(getClass().getResourceAsStream("/image/p9.png"));
    private final Image p10 = new Image(getClass().getResourceAsStream("/image/p10.png"));
    private final Image pj = new Image(getClass().getResourceAsStream("/image/pj.png"));
    private final Image pq = new Image(getClass().getResourceAsStream("/image/pq.png"));
    private final Image pk = new Image(getClass().getResourceAsStream("/image/pk.png"));
    private final Image back = new Image(getClass().getResourceAsStream("/image/back.png"));
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("Choose.fxml"));

        Scene sc = new Scene(fx.load());

        primaryStage.setTitle("Choose Mode");
        primaryStage.setScene(sc);
        primaryStage.show();
    }
    //改变场景
    @FXML
    public void goG1() throws Exception{
        Game g = new Game();
        stage.close();
        g.start(stage);
    }
    @FXML
    public void goG2() throws Exception{
        Game2 g = new Game2();
        stage.close();
        g.start(stage);
    }

    @FXML
    public void back(){
        stage.close();
    }
}


package com.game.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game extends Application {
    @FXML
    private static boolean keepturn = true;
    @FXML
    private static boolean gamekeep = true;
    private static int playerCard = 0;
    private static int player2Card = 0;
    private static int testvalue; //对手逻辑
    @FXML
    private Label warnLabel = new Label();
    @FXML
    private Label score = new Label();
    private static ArrayList<Integer> player = new ArrayList<Integer>();
    private static ArrayList<Integer> player2 = new ArrayList<Integer>();
    //图片
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

    private static HashMap<Integer,Image> cardset = new HashMap<Integer, Image>();

    //图片放置点
    @FXML
    private ImageView p1c1 = new ImageView();
    @FXML
    private ImageView p1c2 = new ImageView();
    @FXML
    private ImageView p1c3 = new ImageView();
    @FXML
    private ImageView p1c4 = new ImageView();
    @FXML
    private ImageView p1c5 = new ImageView();
    @FXML
    private ImageView p1c6 = new ImageView();
    @FXML
    private ImageView p2c1 = new ImageView();
    @FXML
    private ImageView p2c2 = new ImageView();
    @FXML
    private ImageView p2c3 = new ImageView();
    @FXML
    private ImageView p2c4 = new ImageView();
    @FXML
    private ImageView p2c5 = new ImageView();
    @FXML
    private ImageView p2c6 = new ImageView();
    //牌号，防止重复
    private static ArrayList<Integer> checkRepeat = new ArrayList<Integer>();
    //结束p2翻牌
    private static ArrayList<Integer> flipp2 = new ArrayList<Integer>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fx = new FXMLLoader(getClass().getResource("GameFxml.fxml"));
        //图片设置
        cardset.put(0,back);
        cardset.put(1,la); cardset.put(2,l2); cardset.put(3,l3); cardset.put(4,l4);
        cardset.put(5,l5); cardset.put(6,l6); cardset.put(7,l7); cardset.put(8,l8);
        cardset.put(9,l9); cardset.put(10,l10); cardset.put(11,lj); cardset.put(12,lq);
        cardset.put(13,lk); cardset.put(14,pa); cardset.put(15,p2); cardset.put(16,p3);
        cardset.put(17,p4); cardset.put(18,p5); cardset.put(19,p6); cardset.put(20,p7);
        cardset.put(21,p8); cardset.put(22,p9); cardset.put(23,p10); cardset.put(24,pj);
        cardset.put(25,pq); cardset.put(26,pk);

        Random r = new Random();
        testvalue = r.nextInt(4)+14; //对手逻辑

        //第一张牌
        int rnum1 = r.nextInt(26)+1;
        int num = getNum(rnum1); //得到点数
        player.add(num);
        checkRepeat.add(rnum1);
        playerCard = 1;
        //敌人需要防止重复
        int rnum2 = getNoRep(checkRepeat);
        int num2 = getNum(rnum2);
        player2.add(num2);
        checkRepeat.add(rnum2);
        flipp2.add(rnum2);
        player2Card = 1;

        //为啥不显示啊！！
        setScore();
        p1c1.setImage(cardset.get(rnum1));
        p2c1.setImage(cardset.get(rnum2));

        Scene sc = new Scene(fx.load());
        primaryStage.setTitle("Black Jack");
        primaryStage.setScene(sc);
        primaryStage.show();


    }

    public void whichCard(int index,int player,int num){
        if(player==1){
            if(index == 1){
                p1c1.setImage(cardset.get(num));
            }else if(index==2){
                p1c2.setImage(cardset.get(num));
            }else if(index==3){
                p1c3.setImage(cardset.get(num));
            }else if(index==4){
                p1c4.setImage(cardset.get(num));
            }else if(index==5){
                p1c5.setImage(cardset.get(num));
            }else{
                p1c6.setImage(cardset.get(num));
            }
        }else{
            if(index == 1){
                p2c1.setImage(cardset.get(num));
            }else if(index==2){
                p2c2.setImage(cardset.get(num));
            }else if(index==3){
                p2c3.setImage(cardset.get(num));
            }else if(index==4){
                p2c4.setImage(cardset.get(num));
            }else if(index==5){
                p2c5.setImage(cardset.get(num));
            }else{
                p2c6.setImage(cardset.get(num));
            }
        }
    }

    public void setScore(){
        score.setText("P2's score:"+sumArr(player2)
                +"\nYour score:"+sumArr(player));
    }

    public static int getNoRep(ArrayList check){
        Random r = new Random();
        int rnum = r.nextInt(26)+1;
        while (check.contains(rnum)) {
            rnum = r.nextInt(26)+1; //找出没用过的牌
        }
        return rnum;
    }

    //得到点数
    public static int getNum(int nums){
        //点数（大于10则算作10）
        int num=(nums-1)%13+1;
        if(num>10){
            num=10;
        }
        return num;
    }

    @FXML
    public void hit() {
        Random r = new Random();
        // 测试是否已经结束
        if(gamekeep) {
            //玩家卡
            if (keepturn) {
                //防止重复
                int rnum1 = getNoRep(checkRepeat);
                int num = getNum(rnum1); //得到点数
                player.add(num);
                checkRepeat.add(rnum1);
                playerCard += 1;
                score.setText("P2's score: ?"
                        + "\nYour score:" + sumArr(player)); //更新分数标签
                whichCard(playerCard, 1, rnum1); //更新卡片
            } else {
                warnLabel.setText("Already Stand."); //虽然实际测试好像用不到但是总之先加上吧
            }

            //敌人卡
            if (sumArr(player2) < testvalue) {
                int rnum2 = getNoRep(checkRepeat);
                int num2 = getNum(rnum2);
                player2.add(num2);
                checkRepeat.add(rnum2);
                flipp2.add(rnum2);
                player2Card += 1;
                whichCard(player2Card, 2, 0); //更新卡片（背面）
            }

            //是否有人超过21点
            if(sumArr(player2)>21) {
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
            }else if(sumArr(player)>21) {
                warnLabel.setText("You Explode, You Lose!");
                flipcard();
                setScore();
                gamekeep = false;
            }
        }else{
            warnLabel.setText("Already Gameover");
        }
    }

    @FXML
    public void stand(){
        //是否已经结束
        if(gamekeep) {
            //玩家停止抽卡
            keepturn = false;
            Random r = new Random();
            //敌人继续抽卡
            while (sumArr(player2) < testvalue) {
                int rnum2 = getNoRep(checkRepeat);
                int num2 = getNum(rnum2);
                player2.add(num2);
                checkRepeat.add(rnum2);
                flipp2.add(rnum2);
                player2Card += 1;
                whichCard(player2Card, 2, 0); //更新卡片（背面）
            }

            //结算输赢
            if (sumArr(player2) > 21) {
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
            } else if (sumArr(player) == sumArr(player2)) {
                warnLabel.setText("Same Score!");
                flipcard();
                setScore();
            } else if (sumArr(player) > sumArr(player2)) {
                warnLabel.setText("You Win!");
                flipcard();
                setScore();
            } else {
                warnLabel.setText("You Lose!");
                flipcard();
                setScore();
            }
            gamekeep = false;
        }else{
            warnLabel.setText("Already Gameover");
        }
    }

    //结束后把敌人的卡翻过来(配合花色防重复）
    public void flipcard(){
        int index = 1;
        for(int j:flipp2){
            whichCard(index,2,j);
            index++;
        }
    }

    @FXML
    public void restart(){
        Random r = new Random();
        testvalue = r.nextInt(4)+14;
        keepturn = true;
        gamekeep = true;
        warnLabel.setText("");
        score.setText("");
        player = new ArrayList<Integer>();
        player2 = new ArrayList<Integer>();
        checkRepeat = new ArrayList<Integer>();
        flipp2 = new ArrayList<Integer>();
        //图片
        p1c1.setImage(null); p1c2.setImage(null); p1c3.setImage(null); p1c4.setImage(null);
        p1c5.setImage(null); p1c6.setImage(null); p2c1.setImage(null); p2c2.setImage(null);
        p2c3.setImage(null); p2c4.setImage(null); p2c5.setImage(null); p2c6.setImage(null);

        //第一张牌
        int rnum1 = r.nextInt(26)+1;
        int num = getNum(rnum1); //得到点数
        player.add(num);
        checkRepeat.add(rnum1);
        playerCard=1;
        int rnum2 = getNoRep(checkRepeat);
        int num2 = getNum(rnum2);
        player2.add(num2);
        checkRepeat.add(rnum2);
        flipp2.add(rnum2);
        player2Card=1;
        setScore();
        //放图片
        p1c1.setImage(cardset.get(rnum1));
        p2c1.setImage(cardset.get(rnum2));
    }

    public static int sumArr(ArrayList<Integer> arr) {
        int sum=0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

}





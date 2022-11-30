package com.game.blackjack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game2 extends Application {
    public static Stage stage; //用来让改变场景的method可以用到
    @FXML
    private static boolean gamekeep = false; //是否结束游戏
    private static int playerCard = 0; //玩家目前卡片数量，用来确定卡片图片放在第几个位置
    private static int player2Card = 0; //敌人卡片数量
    private static int testvalue; //对手逻辑
    @FXML
    private Label warnLabel = new Label(); //用来显示提示语
    @FXML
    private Label score = new Label(); //显示分数
    private static ArrayList<Integer> player = new ArrayList<Integer>(); //玩家卡片数值
    private static ArrayList<Integer> player2 = new ArrayList<Integer>(); //对手卡片数值
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
    //规则
    private final Image rule2 = new Image(getClass().getResourceAsStream("/image/rule2.png"));
    // 用来对应随机点数和图片
    private static HashMap<Integer, Image> cardset = new HashMap<Integer, Image>();

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
    @FXML
    private ImageView help2 = new ImageView(); //规则
    private boolean helpdisplay = false; //规则页面是否显示
    //记录牌号，防止重复
    private static ArrayList<Integer> checkRepeat = new ArrayList<Integer>();
    private static Media m = new Media(new File("src/main/resources/music/6199.mp3").toURI().toString());
    public static MediaPlayer mp = new MediaPlayer(m);
    private static Media m2 = new Media(new File("src/main/resources/music/exp-1688.wav").toURI().toString());
    public static MediaPlayer ex = new MediaPlayer(m2);

    public static void main(String[] args) {
        launch(args);
    }
    //音乐

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;

        FXMLLoader fx2 = new FXMLLoader(getClass().getResource("G2.fxml"));
        //图片hashmap，点数对应图片
        cardset.put(0,back);
        cardset.put(1,la); cardset.put(2,l2); cardset.put(3,l3); cardset.put(4,l4);
        cardset.put(5,l5); cardset.put(6,l6); cardset.put(7,l7); cardset.put(8,l8);
        cardset.put(9,l9); cardset.put(10,l10); cardset.put(11,lj); cardset.put(12,lq);
        cardset.put(13,lk); cardset.put(14,pa); cardset.put(15,p2); cardset.put(16,p3);
        cardset.put(17,p4); cardset.put(18,p5); cardset.put(19,p6); cardset.put(20,p7);
        cardset.put(21,p8); cardset.put(22,p9); cardset.put(23,p10); cardset.put(24,pj);
        cardset.put(25,pq); cardset.put(26,pk);

        // 刚开局强制要求点new turn，因为第一局就开始游戏的话有bug，我不会解决
        gamekeep = false;

        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();

        Scene sc2 = new Scene(fx2.load());

        primaryStage.setTitle("Black Jack Mode 2");
        primaryStage.setScene(sc2);
        primaryStage.show();
    }

    //改变场景
    @FXML
    public void goG1() throws Exception{
        Game g = new Game();
        stage.close();
        g.start(stage); //不然stage会是null，虽然好像也可以用initializable但是我偷工减料了（
    }
    @FXML
    public void goBack() throws Exception{
        mp.stop();
        ChooseMode g = new ChooseMode();
        stage.close();
        g.start(stage);
    }

    //确定哪个位置放什么牌，index从1开始
    public void whichCard(int index, int player, int num) {
        if (player == 1) {
            if (index == 1) {
                p1c1.setImage(cardset.get(num));
            } else if (index == 2) {
                p1c2.setImage(cardset.get(num));
            } else if (index == 3) {
                p1c3.setImage(cardset.get(num));
            } else if (index == 4) {
                p1c4.setImage(cardset.get(num));
            } else if (index == 5) {
                p1c5.setImage(cardset.get(num));
            } else {
                p1c6.setImage(cardset.get(num));
            }
        } else {
            if (index == 1) {
                p2c1.setImage(cardset.get(num));
            } else if (index == 2) {
                p2c2.setImage(cardset.get(num));
            } else if (index == 3) {
                p2c3.setImage(cardset.get(num));
            } else if (index == 4) {
                p2c4.setImage(cardset.get(num));
            } else if (index == 5) {
                p2c5.setImage(cardset.get(num));
            } else {
                p2c6.setImage(cardset.get(num));
            }
        }
    }

    //score label显示分数，用在结算时
    public void setFinalScore() {
        score.setText("P2's score:" + finalSumArr(player2)
                + "\nYour score:" + finalSumArr(player));
    }
    // score显示分数，用在平时
    public void setScore() {
        score.setText("P2's score: ? + " + (finalSumArr(player2) - player2.get(0))
                + "\nYour score: " + finalSumArr(player));
    }

    //获取一个不和 checkRepeat Arraylist 重复的随机数
    public static int getNoRep(ArrayList check) {
        Random r = new Random();
        int rnum = r.nextInt(26) + 1;
        while (check.contains(rnum)) {
            rnum = r.nextInt(26) + 1; //找出没用过的牌
        }
        return rnum;
    }

    //检测是否爆炸，爆炸则检查是否有A，有的话替换后输出T/F
    public boolean ifExplode(ArrayList<Integer> arr,int p){
        if(finalSumArr(arr)>21){
            return true;
        }
        return false;
    }
    //把作为11的A换成1（默认这之前已经检查过是否爆炸）
    public void changeA(ArrayList<Integer> arr,int p){
        if(arr.contains(11)){
            int index = arr.indexOf(11);
            if(p==1){
                player.set(index,1);
            }else{
                player2.set(index,1);
            }
        }
    }
    public void ifchangeA(ArrayList<Integer> arr,int p){
        if(finalSumArr(arr)>21){
            changeA(arr,p);
        }
    }

    @FXML
    public void hit() {
        Random r = new Random();
        // 测试是否已经结束
        if (gamekeep) {
            //防止重复
            int rnum1 = getNoRep(checkRepeat);
            int num1 = getNum(rnum1);
            player.add(num1);
            checkRepeat.add(rnum1);
            playerCard += 1;
            whichCard(playerCard, 1, rnum1); //更新卡片
            ifchangeA(player,1); //是否需要change A

            if (ifExplode(player,1)) {//检查玩家是否超过21
                ex.play();
                warnLabel.setText("You Explode, You Lose!");
                flipcard();
                setFinalScore();
                gamekeep = false;
            } else {//如果玩家没超过21则对手摸牌
                if (finalSumArr(player2)<testvalue) {
                    int rnum2 = getNoRep(checkRepeat);
                    int num2 = getNum(rnum2);
                    player2.add(num2);
                    checkRepeat.add(rnum2);
                    player2Card += 1;
                    whichCard(player2Card, 2, rnum2); //更新卡片
                    ifchangeA(player2,2);
                    if (ifExplode(player2,2)) {//检查对手是否超过21
                        ex.play();
                        warnLabel.setText("P2 Explode, You Win!");
                        flipcard();
                        setFinalScore();
                        gamekeep = false;
                    }
                }
                if (gamekeep) {
                    setScore();//各自摸牌结束且没有结束游戏则更新分数
                }
            }
        } else {
            warnLabel.setText("Please Start New Turn");
        }
    }

    @FXML
    public void stand() {
        //是否已经结束
        if (gamekeep) {
            Random r = new Random();
            //敌人继续抽卡
            while (finalSumArr(player2)<testvalue) {
                int rnum2 = getNoRep(checkRepeat);
                int num2 = getNum(rnum2);
                player2.add(num2);
                checkRepeat.add(rnum2);
                player2Card += 1;
                whichCard(player2Card, 2, rnum2);
                ifchangeA(player2,2);
            }

            //结算输赢
            if (ifExplode(player2,2)) {
                ex.play();
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setFinalScore();
            } else if (finalSumArr(player) == finalSumArr(player2)) {
                warnLabel.setText("Same Score!");
                flipcard();
                setFinalScore();
            } else if (finalSumArr(player) > finalSumArr(player2)) {
                warnLabel.setText("You Win!");
                flipcard();
                setFinalScore();
            } else {
                warnLabel.setText("You Lose!");
                flipcard();
                setFinalScore();
            }
            gamekeep = false;
        } else {
            warnLabel.setText("Please Start New Turn");
        }
    }

    //朴实无华的把所有数据全初始化
    @FXML
    public void restart() {
        ex.stop();
        Random r = new Random();
        gamekeep = true;
        testvalue = r.nextInt(4)+14;
        warnLabel.setText("");
        score.setText("");
        player = new ArrayList<Integer>();
        player2 = new ArrayList<Integer>();
        checkRepeat = new ArrayList<Integer>();
        //图片
        p1c1.setImage(null); p1c2.setImage(null); p1c3.setImage(null); p1c4.setImage(null);
        p1c5.setImage(null); p1c6.setImage(null); p2c1.setImage(null); p2c2.setImage(null);
        p2c3.setImage(null); p2c4.setImage(null); p2c5.setImage(null); p2c6.setImage(null);
        //第一张牌
        int rnum1 = r.nextInt(26) + 1;
        int num1 = getNum(rnum1);
        player.add(num1);
        checkRepeat.add(rnum1);
        playerCard = 1;
        int rnum2 = getNoRep(checkRepeat);
        int num2 = getNum(rnum2);
        player2.add(num2);
        checkRepeat.add(rnum2);
        player2Card = 1;
        //放图片
        p1c1.setImage(cardset.get(rnum1));
        p2c1.setImage(cardset.get(0));
        setScore();
    }

    //结束后把双方的第一张牌翻过来
    public void flipcard() {
        whichCard(1, 2, getNum(player2.get(0)));
    }

    //显示和取消help
    public void clickHelp(){
        if(helpdisplay){
            help2.setImage(null);
            helpdisplay=false;
        }else{
            help2.setImage(rule2);
            helpdisplay=true;
        }
    }

    //得到点数
    public static int getNum(int nums){
        //点数（大于10则算作10,A算作11）
        int num=(nums-1)%13+1;
        int ans;
        if(num>10){
            ans = 10;
        }else if(num==1){
            ans = 11;
        }else{
            ans = num;
        }
        return ans;
    }

    //使用随机点数和method计算最终得分
    public static int finalSumArr(ArrayList<Integer> arr) {
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

}

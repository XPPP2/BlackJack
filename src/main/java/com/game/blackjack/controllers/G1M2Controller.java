package com.game.blackjack.controllers;

import com.game.blackjack.ChooseMode;
import com.game.blackjack.G1M2;
import com.game.blackjack.fixedObjects.Medias;
import com.game.blackjack.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class G1M2Controller extends MainControl{
    @FXML
    private static boolean keepturn = true; //是否stand，感觉没啥用，总之先用着
    @FXML
    private static boolean gamekeep = false; //是否结束游戏
    private static int playerCard = 0; //玩家目前卡片数量，用来确定卡片图片放在第几个位置
    private static int player2Card = 0; //敌人卡片数量
    private static int testvalue; //对手逻辑
    @FXML
    private Label warnLabel = new Label(); //用来显示提示语
    @FXML
    private Label score = new Label(); //显示分数
    @FXML
    private Label tScore = new Label(); //玩家总分
    private static ArrayList<Integer> player = new ArrayList<Integer>(); //玩家卡片数值
    private static ArrayList<Integer> player2 = new ArrayList<Integer>(); //对手卡片数值

    //规则
    private final Image rule1 = new Image(getClass().getResourceAsStream("/image/rule1.png"));

    // 用来对应随机点数和图片
    public static HashMap<Integer,Image> cardset = new HashMap<>();

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
    private ImageView help1 = new ImageView(); //规则
    private boolean helpdisplay = false; //规则页面是否显示
    //记录牌号，防止重复
    private static ArrayList<Integer> checkRepeat = new ArrayList<Integer>();
    //记录p2牌号，结束时p2翻牌
    private static ArrayList<Integer> flipp2 = new ArrayList<Integer>();

    @FXML
    public void goG2() throws Exception{
        Stage stage = new Stage();
        Medias.mp.stop();
        G1M2.closeStage();
        Game g = new Game();
        g.start(stage);
    }

    @FXML
    public void goBack() throws Exception{
        Stage stage = new Stage();
        Medias.mp.stop();
        ChooseMode g = new ChooseMode();
        G1M2.closeStage();
        g.start(stage);
    }

    @FXML
    @Override
    public void whichCard(int index, int player, int num) {
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

    @FXML
    @Override
    public void clickHelp() {
        if(helpdisplay){
            help1.setImage(null);
            helpdisplay=false;
        }else{
            help1.setImage(rule1);
            helpdisplay=true;
        }
    }
    @FXML
    @Override
    public void setScore() {
        score.setText("P2's score:"+sumArr(player2)
                +"\nYour score:"+sumArr(player));
    }

    @FXML
    public void setTscore(){
        tScore.setText("Your Total Score: "+G1M2.getTscore());
    }

    @FXML
    @Override
    public void hit() {
        Random r = new Random();
        // 测试是否已经结束
        if(gamekeep) {
            //玩家卡
            //防止重复
            int rnum1 = getNoRep(checkRepeat);
            int num = getNum(rnum1); //得到点数
            player.add(num);
            checkRepeat.add(rnum1);
            playerCard += 1;
            score.setText("P2's score: ?"
                    + "\nYour score:" + sumArr(player)); //更新分数标签
            whichCard(playerCard, 1, rnum1); //更新卡片

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
                Medias.ex.play();
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                //加分
                G1M2.setTscore(G1M2.getTscore()+3);
                setTscore();//显示
            }else if(sumArr(player)>21) {
                Medias.ex.play();
                warnLabel.setText("You Explode, You Lose!");
                flipcard();
                setScore();
                gamekeep = false;
                //扣分
                G1M2.setTscore(G1M2.getTscore()-3);
                setTscore();//显示
            }
        }else{
            warnLabel.setText("Please Start New Turn");
        }
    }

    @FXML
    @Override
    public void stand() {
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
            if(sumArr(player2) > 21) {
                Medias.ex.play();
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
                G1M2.setTscore(G1M2.getTscore()+3);//加分
                setTscore();//显示
            }else if (sumArr(player) == sumArr(player2)) {
                warnLabel.setText("Same Score!");
                flipcard();
                setScore();
                //分数不变
            }else if (sumArr(player) > sumArr(player2)) {
                int addscore = sumArr(player) - sumArr(player2);
                warnLabel.setText("You Win!");
                flipcard();
                setScore();
                G1M2.setTscore(G1M2.getTscore()+addscore);
                setTscore();//显示新分数
            }else {
                int addscore = sumArr(player)-sumArr(player2);
                warnLabel.setText("You Lose!");
                flipcard();
                setScore();
                G1M2.setTscore(G1M2.getTscore()+addscore);
                setTscore();//显示
            }
            gamekeep = false;
        }else{
            warnLabel.setText("Please Start New Turn");
        }
    }

    @FXML
    @Override
    public void restart() {
        Medias.ex.stop();
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

    @FXML
    @Override
    public void flipcard() {
        int index = 1;
        for(int j:flipp2){
            whichCard(index,2,j);
            index++;
        }
    }
}

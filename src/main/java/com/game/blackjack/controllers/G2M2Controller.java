package com.game.blackjack.controllers;

import com.game.blackjack.*;
import com.game.blackjack.fixedObjects.Medias;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class G2M2Controller extends MainControl implements Initializable {

    @FXML
    private static boolean gamekeep = false; //是否结束游戏
    private static int playerCard = 0; //玩家目前卡片数量，用来确定卡片图片放在第几个位置
    private static int player2Card = 0; //敌人卡片数量
    private static int player3Card = 0; //p3
    private static int player4Card = 0; //p4
    private static int testvalue; //对手逻辑
    private static int testvalue2; //p3逻辑
    private static int testvalue3; //p4逻辑
    @FXML
    private Label warnLabel = new Label(); //用来显示提示语
    @FXML
    private Label score = new Label(); //显示分数
    @FXML
    private Label tScore = new Label(); //玩家总分
    @FXML
    private Label tScore2 = new Label();//p2总分
    @FXML
    private Label tScore3 = new Label();
    @FXML
    private Label tScore4 = new Label();
    private static ArrayList<Integer> player = new ArrayList<Integer>(); //玩家卡片数值
    private static ArrayList<Integer> player2 = new ArrayList<Integer>(); //对手卡片数值
    private static ArrayList<Integer> player3 = new ArrayList<Integer>(); //p3卡片数值
    private static ArrayList<Integer> player4 = new ArrayList<Integer>(); //p4卡片数值

    //规则
    private final Image rule2 = new Image(getClass().getResourceAsStream("/image/rule2.png"));

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
    private ImageView p3c1 = new ImageView();
    @FXML
    private ImageView p3c2 = new ImageView();
    @FXML
    private ImageView p3c3 = new ImageView();
    @FXML
    private ImageView p3c4 = new ImageView();
    @FXML
    private ImageView p3c5 = new ImageView();
    @FXML
    private ImageView p3c6 = new ImageView();
    @FXML
    private ImageView p4c1 = new ImageView();
    @FXML
    private ImageView p4c2 = new ImageView();
    @FXML
    private ImageView p4c3 = new ImageView();
    @FXML
    private ImageView p4c4 = new ImageView();
    @FXML
    private ImageView p4c5 = new ImageView();
    @FXML
    private ImageView p4c6 = new ImageView();

    @FXML
    private ImageView help2 = new ImageView(); //规则
    private boolean helpdisplay = false; //规则页面是否显示
    //记录牌号，防止重复
    private static ArrayList<Integer> checkRepeat = new ArrayList<Integer>();
    private static ArrayList<Integer> flipping = new ArrayList<Integer>(); //记录三个首位

    @FXML
    public void goG2() throws Exception{
        Stage stage = new Stage();
        Medias.mp.stop();
        G2M2.closeStage();
        Game2 g = new Game2();
        g.start(stage);
    }
    @FXML
    public void goBack() throws Exception{
        Stage stage = new Stage();
        Medias.mp.stop();
        ChooseMode g = new ChooseMode();
        G2M2.closeStage();
        g.start(stage);
    }

    @Override
    public void whichCard(int index, int players, int num) {
        if(players==1){
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
        }else if(players==2){
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
        }else if(players==3) {
            if (index == 1) {
                p3c1.setImage(cardset.get(num));
            } else if (index == 2) {
                p3c2.setImage(cardset.get(num));
            } else if (index == 3) {
                p3c3.setImage(cardset.get(num));
            } else if (index == 4) {
                p3c4.setImage(cardset.get(num));
            } else if (index == 5) {
                p3c5.setImage(cardset.get(num));
            } else {
                p3c6.setImage(cardset.get(num));
            }
        }else{
            if (index == 1) {
                p4c1.setImage(cardset.get(num));
            } else if (index == 2) {
                p4c2.setImage(cardset.get(num));
            } else if (index == 3) {
                p4c3.setImage(cardset.get(num));
            } else if (index == 4) {
                p4c4.setImage(cardset.get(num));
            } else if (index == 5) {
                p4c5.setImage(cardset.get(num));
            } else {
                p4c6.setImage(cardset.get(num));
            }
        }
    }

    @Override
    public void clickHelp() {
        if(helpdisplay){
            help2.setImage(null);
            helpdisplay=false;
        }else{
            help2.setImage(rule2);
            helpdisplay=true;
        }
    }

    @Override
    public void setScore() {
        score.setText("P2's score:"+sumArr(player2)
                +"\nP3's score: "+sumArr(player3)
                +"\nP4's scpre: "+sumArr(player4)
                +"\nYour score:"+sumArr(player));
    }
    public void sethalfScore() {
        score.setText("P2's score: ?+ " + (sumArr(player2) - player2.get(0))
                +"\nP3's score: ?+ "+(sumArr(player3) - player3.get(0))
                +"\nP4's scpre: ?+ "+(sumArr(player4) - player4.get(0))
                +"\nYour score: " + sumArr(player));
    }
    @FXML
    public void setTscore(){
        tScore.setText("Your Total Score: "+G2M2.getTscore());
        tScore2.setText(""+G2M2.getTscoreP2());
        tScore3.setText(""+G2M2.getTscoreP3());
        tScore4.setText(""+G2M2.getTscoreP4());
    }
    
    @Override
    public void hit() throws Exception {
        Random r = new Random();
        // 测试是否已经结束
        if(gamekeep) {
            //玩家卡
            if(playerCard<7) {
                //防止重复
                int rnum1 = getNoRep(checkRepeat);
                int num = getNum(rnum1); //得到点数
                player.add(num);
                checkRepeat.add(rnum1);
                playerCard += 1;
                whichCard(playerCard, 1, rnum1); //更新卡片
                ifchangeA(player, 1);
            }else{
                warnLabel.setText("Reach Card Max!");
            }

            //敌人卡
            if (sumArr(player2) < testvalue & player2Card<7) {
                int rnum2 = getNoRep(checkRepeat);
                int num2 = getNum(rnum2);
                player2.add(num2);
                checkRepeat.add(rnum2);
                player2Card += 1;
                whichCard(player2Card, 2, rnum2); //更新卡片（背面）
                ifchangeA(player2,2);
            }
            if (sumArr(player3) < testvalue2 & player3Card<7) {
                int rnum3 = getNoRep(checkRepeat);
                int num3 = getNum(rnum3);
                player3.add(num3);
                checkRepeat.add(rnum3);
                player3Card += 1;
                whichCard(player3Card, 3, rnum3); //更新卡片（背面）
                ifchangeA(player3,3);
            }
            if (sumArr(player4) < testvalue3 & player4Card<7) {
                int rnum4 = getNoRep(checkRepeat);
                int num4 = getNum(rnum4);
                player4.add(num4);
                checkRepeat.add(rnum4);
                player4Card += 1;
                whichCard(player4Card, 4, rnum4); //更新卡片（背面）
                ifchangeA(player4,4);
            }
            sethalfScore(); //更新分数标签,应该放在最后

            //是否有人超过21点
            if(sumArr(player)>21) {
                Medias.ex.play();
                warnLabel.setText("You Explode, You Lose!");
                flipcard();
                setScore();
                gamekeep = false;
                //扣分
                ifnotExplode();
                ifget21(2);ifget21(3);ifget21(4);
                setTscore();//显示
                changeTscore();
            }else if(sumArr(player2)>21) {
                Medias.ex.play();
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                //加分
                ifnotExplode();
                ifget21(1);ifget21(3);ifget21(4);
                setTscore();//显示
                changeTscore();
            }else if(sumArr(player3)>21) {
                Medias.ex.play();
                warnLabel.setText("P3 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                ifnotExplode();
                ifget21(1);ifget21(2);ifget21(4);
                setTscore();//显示
                changeTscore();
            }else if(sumArr(player4)>21) {
                Medias.ex.play();
                warnLabel.setText("P4 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                ifnotExplode();
                ifget21(1);ifget21(2);ifget21(3);
                setTscore();//显示
                changeTscore();
            }
        }else{
            warnLabel.setText("Please Start New Turn");
        }
    }

    @FXML
    @Override
    public void stand() throws FileNotFoundException {
        //是否已经结束
        if(gamekeep) {
            //玩家停止抽卡
            Random r = new Random();
            //敌人继续抽卡
            while (sumArr(player2) < testvalue & player2Card<7) {
                int rnum2 = getNoRep(checkRepeat);
                int num2 = getNum(rnum2);
                player2.add(num2);
                checkRepeat.add(rnum2);
                player2Card += 1;
                whichCard(player2Card, 2, rnum2); //更新卡片（背面）
                ifchangeA(player2,2);
            }
            while (sumArr(player3) < testvalue2 & player3Card<7) {
                int rnum3 = getNoRep(checkRepeat);
                int num3 = getNum(rnum3);
                player3.add(num3);
                checkRepeat.add(rnum3);
                player3Card += 1;
                whichCard(player3Card, 3, rnum3); //更新卡片（背面）
                ifchangeA(player3,3);
            }
            while (sumArr(player4) < testvalue3 & player4Card<7) {
                int rnum4 = getNoRep(checkRepeat);
                int num4 = getNum(rnum4);
                player4.add(num4);
                checkRepeat.add(rnum4);
                player4Card += 1;
                whichCard(player4Card, 4, rnum4); //更新卡片（背面）
                ifchangeA(player4,4);
            }

            //结算输赢
            if(sumArr(player2) > 21) {
                Medias.ex.play();
                warnLabel.setText("P2 Explode, You Win!");
                flipcard();
                setScore();
                ifnotExplode();//加分
                ifget21(1);ifget21(3);ifget21(4);
                setTscore();//显示
                changeTscore();
            }else if(sumArr(player3)>21) {
                Medias.ex.play();
                warnLabel.setText("P3 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                ifnotExplode();//爆炸全扣分
                ifget21(1);ifget21(2);ifget21(4);
                setTscore();//显示
                changeTscore();
            }else if(sumArr(player4)>21) {
                Medias.ex.play();
                warnLabel.setText("P4 Explode, You Win!");
                flipcard();
                setScore();
                gamekeep = false;
                ifnotExplode();
                ifget21(1);ifget21(2);ifget21(3);
                setTscore();//显示
                changeTscore();
            }else if (sumArr(player)==Math.max(sumArr(player4),Math.max(sumArr(player3),Math.max(sumArr(player),sumArr(player2))))) {
                warnLabel.setText("You Win!");
                flipcard();
                setScore();
                sumScore();
                ifget21(1);ifget21(2);ifget21(3);ifget21(4);
                setTscore();//显示新分数
                changeTscore();
            }else if(sumArr(player2)==Math.max(sumArr(player4),Math.max(sumArr(player3),Math.max(sumArr(player),sumArr(player2))))) {
                warnLabel.setText("You Lose! P2 Win.");
                flipcard();
                setScore();
                sumScore();
                ifget21(1);ifget21(2);ifget21(3);ifget21(4);
                setTscore();//显示新分数
                changeTscore();
            }else if(sumArr(player3)==Math.max(sumArr(player4),Math.max(sumArr(player3),Math.max(sumArr(player),sumArr(player2))))) {
                warnLabel.setText("You Lose! P3 Win.");
                flipcard();
                setScore();
                sumScore();
                ifget21(1);ifget21(2);ifget21(3);ifget21(4);
                setTscore();//显示新分数
                changeTscore();
            }else if(sumArr(player4)==Math.max(sumArr(player4),Math.max(sumArr(player3),Math.max(sumArr(player),sumArr(player2))))) {
                warnLabel.setText("You Lose! P4 Win.");
                flipcard();
                setScore();
                sumScore();
                ifget21(1);ifget21(2);ifget21(3);ifget21(4);
                setTscore();//显示新分数
                changeTscore();
            }
            gamekeep = false;
        }else{
            warnLabel.setText("Please Start New Turn");
        }
    }

    //分别计算总分
    public void sumScore(){
        G2M2.setTscore(G2M2.getTscore()+3*sumArr(player)-sumArr(player2)-sumArr(player3)-sumArr(player4));
        G2M2.setTscoreP2(G2M2.getTscoreP2()+3*sumArr(player2)-sumArr(player)-sumArr(player3)-sumArr(player4));
        G2M2.setTscoreP3(G2M2.getTscoreP3()+3*sumArr(player3)-sumArr(player2)-sumArr(player)-sumArr(player4));
        G2M2.setTscoreP4(G2M2.getTscoreP4()+3*sumArr(player4)-sumArr(player2)-sumArr(player3)-sumArr(player));
    }

    @FXML
    @Override
    public void restart(){
        if(gamekeep){
            warnLabel.setText("Never Give Up!");
        }else {
            Medias.ex.stop();
            Random r = new Random();
            testvalue = r.nextInt(4) + 14;
            testvalue2 = r.nextInt(3) + 14;
            testvalue3 = r.nextInt(2) + 14;
            gamekeep = true;
            warnLabel.setText("");
            score.setText("");
            player = new ArrayList<Integer>();
            player2 = new ArrayList<Integer>();
            player3 = new ArrayList<Integer>();
            player4 = new ArrayList<Integer>();
            checkRepeat = new ArrayList<Integer>();
            flipping = new ArrayList<Integer>();

            //图片
            p1c1.setImage(null);
            p1c2.setImage(null);
            p1c3.setImage(null);
            p1c4.setImage(null);
            p1c5.setImage(null);
            p1c6.setImage(null);
            p2c1.setImage(null);
            p2c2.setImage(null);
            p2c3.setImage(null);
            p2c4.setImage(null);
            p2c5.setImage(null);
            p2c6.setImage(null);
            p3c1.setImage(null);
            p3c2.setImage(null);
            p3c3.setImage(null);
            p3c4.setImage(null);
            p3c5.setImage(null);
            p3c6.setImage(null);
            p4c1.setImage(null);
            p4c2.setImage(null);
            p4c3.setImage(null);
            p4c4.setImage(null);
            p4c5.setImage(null);
            p4c6.setImage(null);

            //第一张牌
            int rnum1 = r.nextInt(26) + 1;
            int num = getNum(rnum1); //得到点数
            player.add(num);
            checkRepeat.add(rnum1);
            playerCard = 1;
            int rnum2 = getNoRep(checkRepeat);
            int num2 = getNum(rnum2);
            player2.add(num2);
            checkRepeat.add(rnum2);
            flipping.add(rnum2);
            player2Card = 1;
            int rnum3 = getNoRep(checkRepeat);
            int num3 = getNum(rnum3);
            player3.add(num3);
            checkRepeat.add(rnum3);
            flipping.add(rnum3);
            player3Card = 1;
            int rnum4 = getNoRep(checkRepeat);
            int num4 = getNum(rnum4);
            player4.add(num4);
            checkRepeat.add(rnum4);
            flipping.add(rnum4);
            player4Card = 1;

            setScore();
            //放图片
            p1c1.setImage(cardset.get(rnum1));
            p2c1.setImage(cardset.get(0));
            p3c1.setImage(cardset.get(0));
            p4c1.setImage(cardset.get(0));
        }
    }

    public void clearTscore() throws FileNotFoundException {
        PrintWriter fout = new PrintWriter(new File("G2M2score.txt"));
        G2M2.setTscore(0);
        fout.println(0);
        G2M2.setTscoreP2(0);
        fout.println(0);
        G2M2.setTscoreP3(0);
        fout.println(0);
        G2M2.setTscoreP4(0);
        fout.println(0);
        setTscore();
        fout.close();
    }
    public void changeTscore() throws FileNotFoundException {
        PrintWriter fout = new PrintWriter(new File("G2M2score.txt"));
        fout.println(G2M2.getTscore());
        fout.println(G2M2.getTscoreP2());
        fout.println(G2M2.getTscoreP3());
        fout.println(G2M2.getTscoreP4());
        fout.close();
    }

    public void ifget21(int players){
        if(players==1){
            if(sumArr(player)==21){
                G2M2.setTscore(G2M2.getTscore()+10);
                ifnot21();
            }
        }else if(players==2){
            if(sumArr(player2)==21){
                G2M2.setTscoreP2(G2M2.getTscoreP2()+10);
                ifnot21();
            }
        }else if(players==3){
            if(sumArr(player3)==21){
                G2M2.setTscoreP3(G2M2.getTscoreP3()+10);
                ifnot21();
            }
        }else{
            if(sumArr(player4)==21){
                G2M2.setTscoreP4(G2M2.getTscoreP4()+10);
                ifnot21();
            }
        }
    }

    public void ifnot21(){
        if(sumArr(player)!=21) {
            G2M2.setTscore(G2M2.getTscore() - 4);
        }
        if(sumArr(player2)!=21){
            G2M2.setTscoreP2(G2M2.getTscoreP2() - 4);
        }
        if(sumArr(player3)!=21){
            G2M2.setTscoreP3(G2M2.getTscoreP3() - 4);
        }
        if(sumArr(player4)!=21) {
            G2M2.setTscoreP4(G2M2.getTscoreP4() - 4);
        }
    }

    @Override
    public void flipcard() {
        whichCard(1,2,flipping.get(0));
        whichCard(1,3,flipping.get(1));
        whichCard(1,4,flipping.get(2));
    }

    public void ifnotExplode(){
        if(!ifExplode(player)){
            G2M2.setTscore(G2M2.getTscore()+3);
        }else{
            G2M2.setTscore(G2M2.getTscore()-6);
        }
        if(!ifExplode(player2)){
            G2M2.setTscoreP2(G2M2.getTscoreP2()+3);
        }else{
            G2M2.setTscoreP2(G2M2.getTscoreP2()-6);
        }
        if(!ifExplode(player3)){
            G2M2.setTscoreP3(G2M2.getTscoreP3()+3);
        }else{
            G2M2.setTscoreP3(G2M2.getTscoreP3()-6);
        }
        if(!ifExplode(player4)){
            G2M2.setTscoreP4(G2M2.getTscoreP4()+3);
        }else{
            G2M2.setTscoreP4(G2M2.getTscoreP4()-6);
        }
    }

    //检测是否爆炸，爆炸则检查是否有A，有的话替换后输出T/F
    public boolean ifExplode(ArrayList<Integer> arr){
        if(sumArr(arr)>21){
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
            }else if(p==2){
                player2.set(index,1);
            }else if(p==3){
                player3.set(index,1);
            }else{
                player4.set(index,1);
            }
        }
    }
    public void ifchangeA(ArrayList<Integer> arr,int p){
        if(ifExplode(arr)){
            changeA(arr,p);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamekeep=false;
        restart();
        setTscore();
    }
}

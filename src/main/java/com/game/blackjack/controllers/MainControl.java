package com.game.blackjack.controllers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public abstract class MainControl {
    //确定哪个位置放什么牌
    public abstract void whichCard(int index,int player,int num);
    //显示和取消help
    public abstract void clickHelp();
    //score label显示分数，用在结算时
    public abstract void setScore();

    public abstract void hit() throws Exception;
    public abstract void stand() throws Exception;
    public abstract void restart() throws Exception;

    //结束后把敌人的卡翻过来
    public abstract void flipcard();

    //获取一个不和 checkRepeat Arraylist 重复的随机数
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
    //玩家点数总和
    public static int sumArr(ArrayList<Integer> arr) {
        int sum=0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

}

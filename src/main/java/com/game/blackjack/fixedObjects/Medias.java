package com.game.blackjack.fixedObjects;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Medias {

    //音乐
    private final static Media m = new Media(new File("src/main/resources/music/6199.mp3").toURI().toString());
    public final static MediaPlayer mp = new MediaPlayer(m);
    private final static Media m2 = new Media(new File("src/main/resources/music/exp-1688.wav").toURI().toString());
    public final static MediaPlayer ex = new MediaPlayer(m2);
    
}

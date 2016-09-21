package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by sadiq on 8/23/2016.
 */
public class Player extends GameObjects{
    private Bitmap spriteSheet;
    private int score;
    private double dya; //for acceleration
    private boolean up; //for acceleration
    private boolean playing; //for acceleration
    private Animation animation = new Animation(); //own custom animation
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0; //dy is using GameObject and it is protected
        score = 0;
        height = h;
        width = w;

        Bitmap[] images = new Bitmap[numFrames];
        spriteSheet = res;


        for (int i = 1; i <images.length ; i++) {
            images[i] = Bitmap.createBitmap(spriteSheet, i * width, 0, width, height);
        }
        animation.setFrames(images);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b ){
        up = b;
    }

    public void update(){
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if ( elapsed > 100 ){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if (up){
            dy = (int) (dya-=1.1);
        } else {
            dy = (int) (dya+=1.1);
        }

        //speed of helicopter
        if ( dy > 14 )
            { dy = 14; }
        if ( dy <- 14 )
            { dy = -14; }

        y += dy*2;
        dy = 0;
    }

    public  void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x, y, null);
    }

    public int getScore(){ return score; }

    public boolean getPlaying(){ return playing; }

    public void setPlaying(boolean b){ playing = b; }

    public void resetDYA(){ dya = 0;}

    public void resetScore(){ score = 0;}

}

package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;

/**
 * Created by sadiq on 8/23/2016.
 */
public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playerOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d){
        delay = d;
    }

    public void setFrame(int i){
        currentFrame = i;
    }

    public void update(){
        long elspsed = (System.nanoTime() - startTime)/1000000;

        if (elspsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }

        if ( currentFrame == frames.length){
            currentFrame = 0;
            playerOnce = true;
        }
    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }

    public int getFrame(){
        return currentFrame;
    }

    public boolean playerOnce(){
        return playerOnce;
    }
}

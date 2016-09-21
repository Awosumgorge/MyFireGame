package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;

/**
 * Created by sadiq on 8/23/2016.
 */
public class BulletAnimation {
    private Bitmap bulletImage;
    private long bulletStartTime;
    private long bulletDelayTime;
    private long delay;
    private boolean bulletOnce;

    public void setImage(Bitmap bulletImage){
        this.bulletImage = bulletImage;
    }

    public Bitmap getImage(){
        return bulletImage;
    }

    public void setDelay(long d){
        bulletDelayTime = d;
    }

    public void update(){
        long elspsed = (System.nanoTime() - bulletStartTime)/1000000;

        if (elspsed > delay){
            //currentFrame++;
            bulletStartTime = System.nanoTime();
        }

        //if ( currentFrame == frames.length){
            //currentFrame = 0;
            bulletOnce = true;
        //}
    }

    public boolean bulletOnce(){
        return bulletOnce;
    }

}

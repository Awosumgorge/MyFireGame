package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 9/7/2016.
 */
public class TargetBirdOne {
    private int x = 0;
    private int y = 0;
    private GamePanel view;
    private Bitmap bmp;
    //private int left = 0;
    //private int right = 0;
    private boolean move = true;
    //private boolean isFired = false;

    public TargetBirdOne(GamePanel view, Bitmap bmp, int x, int y){
        this.view = view;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        //this.left = x;
        //this.right = y;
    }

    public void onDraw(Canvas canvas){
        targetMotionUpAndDown();
        canvas.drawBitmap(this.bmp, this.x, this.y, null);

        /*Log.e("targetBirdOne", "targetBirdOne x => " + this.x);
        Log.e("targetBirdOne", "targetBirdOne this.bmp.getWidth() => " + this.bmp.getWidth());
        Log.e("targetBirdOne", "targetBirdOne this.view.getBackgroundTotalHeight() => " + this.view.getBackgroundTotalHeight() );
        Log.e("targetBirdOne","targetBirdOne y => " + (this.bmp.getWidth() - this.view.getBackgroundTotalHeight())) ;
        Log.e("targetBirdOne","targetBirdOne y => " + this.y +"\n=====");*/

    }

    public void targetMotionUpAndDown(){
        int upDownSpeedValue = 0;
        if (GamePanel.scoreValue >= 20 && GamePanel.scoreValue <= 55){
            upDownSpeedValue = 8;
        } else if ( GamePanel.scoreValue > 55  ){//&& GamePanel.scoreValue <= 80
            upDownSpeedValue = 10;
        } else {
            upDownSpeedValue = 4;
        }

        if (this.y < (this.view.getBackgroundTotalHeight() - this.bmp.getWidth()) && this.move && !DialogBoxCustom.isGameOver  ){

            this.y += upDownSpeedValue;
            if (this.y == this.view.getBackgroundTotalHeight() - this.bmp.getWidth() || this.y > this.view.getBackgroundTotalHeight() - this.bmp.getWidth() ){
                this.targetBirdBoolean(false);
            } else {
                this.targetBirdBoolean(true);
            }

        } else if ( !this.move && !DialogBoxCustom.isGameOver ) {//y > (view.getBackgroundTotalHeight() - bmp.getWidth()) &&
            this.y -= upDownSpeedValue;

            if (this.y == 0){
                this.targetBirdBoolean(true);
            } else{
                this.targetBirdBoolean(false);
            }
        } else if ( DialogBoxCustom.isGameOver  ){
            this.y = 520;
            this.getX();
        }
    }

    public void targetBirdBoolean(boolean b){  //may be it will static
        this.move = b;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHeight(){
        return this.bmp.getHeight();
    }

    public int getWidth(){
        return this.bmp.getWidth();
    }

}

package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 8/29/2016.
 */
//public class AnotherTargetBird extends GameObjects {
public class AnotherTargetBird  {

    private int x ;
    private int y ;
    private GamePanel view;
    private Bitmap bmp;
    //private boolean moveAnotherBird = true;
    private boolean move = true;


    public AnotherTargetBird( GamePanel view, Bitmap bmp, int x, int y ){
        this.view = view;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
    }

    public void onDraw(Canvas canvas){
        //anotherTargetMotionUpAndDown();
        targetMotionUpAndDown();
        canvas.drawBitmap(bmp, x, y, null);
    }

    public void targetMotionUpAndDown(){
        int upDownSpeedValue = 6;
        if (y < (view.getBackgroundTotalHeight() - bmp.getWidth()) && move && !DialogBoxCustom.isGameOver  ){

            y += upDownSpeedValue;
            if (y == view.getBackgroundTotalHeight() - bmp.getWidth() || y > view.getBackgroundTotalHeight() - bmp.getWidth() ){
                targetBirdBoolean(false);
            } else {
                targetBirdBoolean(true);
            }

        } else if ( !move && !DialogBoxCustom.isGameOver ) {//y > (view.getBackgroundTotalHeight() - bmp.getWidth()) &&

            y -= upDownSpeedValue;

            if (y == 0){
                targetBirdBoolean(true);
            } else{
                targetBirdBoolean(false);
            }
        } else if ( DialogBoxCustom.isGameOver  ){
            this.y = 0;
            this.getX();
        }
    }

    public void targetBirdBoolean(boolean b){
        move = b;
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
        return bmp.getHeight();
    }

    public int getWidth(){
        return bmp.getWidth();
    }
}

package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 8/24/2016.
 */
public class TargetBird {
    private int x = 0;
    private int y = 0;
    private GamePanel view;
    private Bitmap bmp;
    private int left = 0;
    private int right = 0;
    private boolean move = true;
    private boolean isFired = false;


    private BulletNew bulletNew;


    public TargetBird(GamePanel view, Bitmap bmp, int x, int y){
        this.view = view;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.left = x;
        this.right = y;
    }

    public void onDraw(Canvas canvas){
        targetMotionUpAndDown();
        canvas.drawBitmap(bmp, x, y, null);
    }

    /*public void update(Bitmap bmp){
        this.bmp = bmp;
    }*/

    public void targetMotionUpAndDown(){
        int upDownSpeedValue = 0;
        if (GamePanel.scoreValue >= 20 && GamePanel.scoreValue <= 55){
            upDownSpeedValue = 8;
        } else if ( GamePanel.scoreValue > 55  ){//&& GamePanel.scoreValue <= 80
            upDownSpeedValue = 10;
        } else {
            upDownSpeedValue = 4;
        }

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
            this.x = (this.view.getBackgroundTotalWidth() - (this.getWidth() + 10));
            //this.getX();
        }
    }

    public void targetBirdBoolean(boolean b){  //may be it will static
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

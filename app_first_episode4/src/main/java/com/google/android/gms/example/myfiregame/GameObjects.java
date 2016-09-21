package com.google.android.gms.example.myfiregame;

import android.graphics.Rect;

/**
 * Created by sadiq on 8/23/2016.
 */
public abstract class GameObjects {
    protected int x;
    protected int y;
    protected int dy; //directly call from Player class
    protected int dx;
    protected int width;
    protected int height;

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public Rect getRectangle(){
        return new Rect(this.x, this.y,this.x+ this.width, this.y+this.height);
    }


}

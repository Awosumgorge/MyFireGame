package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by sadiq on 8/23/2016.
 */
public class Background {
    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res){
        this.image = res;
        dx = GamePanel.MOVESPEED;
    }

    public void update(){
        x += dx;
        if (x < 0 ){
            x =0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        if (x <- GamePanel.WIDTH){
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }


}

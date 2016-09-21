package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 8/24/2016.
 */
public class BulletNew {
    //public static int countGunClick = -1;
    /*public static int scoreValue = 0;
    public static int totalChance = 3;
    public static boolean isFired = false;*/

    private int x = 0;
    private int y = 0;
    private GamePanel view;
    private Bitmap bmp;
    private int left = 0;
    private int right = 0;

    private boolean resetBullet = true; //for bullet Reset
    private TargetBirdOne targetBirdOne;
    private TargetBird targetBird;
    //String bulletName;
    //AnotherTargetBird anotherTargetBird;

    public BulletNew(GamePanel view, Bitmap bmp, int x, int y, TargetBird targetBird, TargetBirdOne targetBirdOne ){//,  AnotherTargetBird anotherTargetBird
        //this.bulletName = bulletName;
        this.view = view;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.left = x;
        this.right = y;
        this.targetBirdOne = targetBirdOne;
        this.targetBird = targetBird;
        //this.anotherTargetBird = anotherTargetBird;

    }

    public void onDraw(Canvas canvas){
        updateBullet();
        canvas.drawBitmap(this.bmp, this.x, this.y, null);
    }

    /*public void update(Bitmap bmp){
        this.bmp = bmp;
    }*/

    /**
     * Update the state of the sprite.
     */
    public void updateBullet(){
        if(GamePanel.isFired){
            //Log.e("touchDown", "BulletNew  updateBullet function  isFired => "+ isFired);

            if ( this.x < this.view.getBackgroundTotalWidth() && GamePanel.isFired){
                this.x += 20;

                isCollide();
                //isCollision();
                if ( this.x == ( this.view.getBackgroundTotalWidth() - 4) ){
                    this.x = this.view.getBackgroundTotalWidth();
                }

                if (this.x == this.view.getBackgroundTotalWidth() && GamePanel.bulletCount == 0 ){
                    this.resetBullet = false; //for bullet Reset
                    GamePanel.isFired = false;//=setBulletFired(false);

                    GamePanel.newBulletnew = null;
                }
            }
        } else if (this.x == view.getBackgroundTotalWidth() && !GamePanel.isFired && !this.resetBullet ) {
            this.x = 0;
            //setBulletFired(false);//=isFIred
            GamePanel.isFired = false;//=setBulletFired(false);
        }
    }

    private void isCollide(){
        if ( this.x  >= this.targetBird.getX()
                && this.y >= (this.targetBird.getY() )
                && this.y <= (this.targetBird.getY() + this.targetBird.getHeight())
                ){
            isCollision();
            Log.e("sadiq", "isCollide => targetBird hit success Bullet new");
        } else if ( this.x  >= this.targetBirdOne.getX()
                && this.y >= (this.targetBirdOne.getY() )
                && this.y <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){
            isCollisionOne();
            Log.e("sadiq","isCollide => targetBirdOne hit success Bullet new" );
        }
    }
    public void isCollisionOne(){
        if ( this.getX()  >= this.targetBirdOne.getX()
                && this.getY() >= (this.targetBirdOne.getY() )
                && this.getY() <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){

            this.x = this.view.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBirdOne.setY(520);
            this.targetBirdOne.targetBirdBoolean(true);

        }else if (this.getX() > (this.view.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
            GamePanel.totalChance += -1;
            //Log.e("totalChance0", "totalChance 0 BulletNew => " + GamePanel.totalChance);
            //isCollide = false;
        }

        if (GamePanel.totalChance == 0){ //for showing dialog box
            DialogBoxCustom.isGameOver = true;
            //Log.e("totalChance0", "Bullet New totalChance 0 BulletNew => "+ DialogBoxCustom.isGameOver);
        }
    }

    private void isCollision(){
        if ( this.x  >= this.targetBird.getX()
                && this.y >= (this.targetBird.getY() )
                && this.y <= (this.targetBird.getY() + this.targetBird.getHeight())
                ){

            this.x = this.view.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBird.setY(0);
            this.targetBird.targetBirdBoolean(true);

        }else if (this.x > (this.view.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
            GamePanel.totalChance += -1;
            //Log.e("totalChance0", "totalChance 0 BulletNew => " + GamePanel.totalChance);
            //isCollide = false;
        }

        if (GamePanel.totalChance == 0){ //for showing dialog box
            DialogBoxCustom.isGameOver = true;
            //Log.e("totalChance0", "Bullet New totalChance 0 BulletNew => "+ DialogBoxCustom.isGameOver);
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    /*public void isCollisionAnotherBird(){

        Log.e("touchDown","bullet getX => "+ this.getX() );
        Log.e("anotherTargetBird","anotherTargetBird getHeight => "+ anotherTargetBird.getHeight() );
        Log.e("anotherTargetBird","anotherTargetBird getY => "+ anotherTargetBird.getY() );
        Log.e("anotherTargetBird","anotherTargetBird getY => "+ anotherTargetBird.getHeight() );

        if ( this.getX()  >= anotherTargetBird.getX()
                && this.getY() >= (anotherTargetBird.getY() )
                && this.getY() <= (anotherTargetBird.getY() + anotherTargetBird.getHeight())
                ){

            x = view.getBackgroundTotalWidth();//1024;
            scoreValue += 5;
            anotherTargetBird.setY(0);
            anotherTargetBird.targetBirdBoolean(true);

        }else if (this.getX() > (view.getBackgroundTotalWidth()-10) && totalChance > 0 ){//&& !isCollide
            totalChance += -1;
            Log.e("totalChance0","totalChance 0 BulletNew => " + totalChance);
            //isCollide = false;
        }

        if (totalChance == 0){ //for showing dialog box
            DialogBoxCustom.isGameOver = true;
            Log.e("totalChance0", "Bullet New totalChance 0 BulletNew => "+ DialogBoxCustom.isGameOver);
        }
    }*/

    /*public void setBulletFired(boolean isFired){
        this.isFired = isFired;
    }*/

    /*public boolean getBulletFired(){
        return isFired;
    }*/

    /*public int getBulletHeight(){
        return this.bmp.getHeight();
    }*/

    /*public int getBulletWidth(){
        return this.bmp.getWidth();
    }*/

    /*public void setLeftRight(int left, int right, boolean move){
        this.left = left;
        this.right = right;
        this.move = move;
    }*/

}

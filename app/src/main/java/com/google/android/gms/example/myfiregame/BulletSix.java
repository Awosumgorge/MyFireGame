package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 9/6/2016.
 */
public class BulletSix {
    private boolean resetBullet = true; //for bullet Reset

    private GamePanel gamePanelViewSix;
    private Bitmap bulletSixBitMap;
    private int x = 0;
    private int y = 0;
    private TargetBirdOne targetBirdOne;
    private TargetBird targetBird;

    public BulletSix(GamePanel gamePanel, Bitmap bulletSixBitMap, int x, int y, TargetBird targetBird, TargetBirdOne targetBirdOne ){

        this.gamePanelViewSix = gamePanel;
        this.bulletSixBitMap = bulletSixBitMap;
        this.x = x;
        this.y = y;
        this.targetBirdOne = targetBirdOne;
        this.targetBird = targetBird;
    }

    public void onDraw(Canvas canvas){
        this.updateBullet();
        canvas.drawBitmap(this.bulletSixBitMap, this.x, this.y, null);
    }

    /**
     * Update the state of the sprite.
     */
    private void updateBullet(){

        if(GamePanel.isFired){

            if ( this.x < this.gamePanelViewSix.getBackgroundTotalWidth() && GamePanel.isFired){
                this.x += 20;

                isCollide();
                //isCollision();
                if ( this.x == ( this.gamePanelViewSix.getBackgroundTotalWidth() - 4) ){
                    this.x = this.gamePanelViewSix.getBackgroundTotalWidth();
                }

                if (this.x == this.gamePanelViewSix.getBackgroundTotalWidth() && GamePanel.bulletCount == 6 ){
                    this.resetBullet = false; //for bullet Reset
                    GamePanel.isFired = false;//=setBulletFired(false);


                    GamePanel.bulletSix = null;
                }
            }
        } else if (this.x == this.gamePanelViewSix.getBackgroundTotalWidth() && !GamePanel.isFired && !this.resetBullet ) {
            this.x = 0;
            GamePanel.isFired = false;//=setBulletFired(false);
        }
    }

    private void isCollide(){
        if ( this.x  >= this.targetBird.getX()
                && this.y >= (this.targetBird.getY() )
                && this.y <= (this.targetBird.getY() + this.targetBird.getHeight())
                ){
            isCollision();
            Log.e("sadiq", "isCollide => targetBird hit success Bullet 6");
        } else if ( this.x  >= this.targetBirdOne.getX()
                && this.y >= (this.targetBirdOne.getY() )
                && this.y <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){
            isCollisionOne();
            Log.e("sadiq","isCollide => targetBirdOne hit success Bullet 6" );
        }
    }

    private void isCollisionOne(){
        if ( this.x  >= this.targetBirdOne.getX()
                && this.y >= (this.targetBirdOne.getY() )
                && this.y <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){

            this.x = this.gamePanelViewSix.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBirdOne.setY(520);
            this.targetBirdOne.targetBirdBoolean(true);

        }else if (this.x > (this.gamePanelViewSix.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
            GamePanel.totalChance += -1;
        }

        if (GamePanel.totalChance == 0){ //for showing dialog box
            DialogBoxCustom.isGameOver = true;
        }
    }

    private void isCollision(){
        if ( this.x  >= this.targetBird.getX()
                && this.y >= (this.targetBird.getY() )
                && this.y <= (this.targetBird.getY() + this.targetBird.getHeight())
                ){

            this.x = this.gamePanelViewSix.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBird.setY(0);
            this.targetBird.targetBirdBoolean(true);

        }else if (this.x > (this.gamePanelViewSix.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
            GamePanel.totalChance += -1;
            //Log.e("totalChance0", "totalChance 0 BulletNew => " + GamePanel.totalChance);
            //isCollide = false;
        }

        if (GamePanel.totalChance == 0){ //for showing dialog box
            DialogBoxCustom.isGameOver = true;
            //Log.e("totalChance0", "Bullet New totalChance 0 BulletNew => "+ DialogBoxCustom.isGameOver);
        }
    }

}

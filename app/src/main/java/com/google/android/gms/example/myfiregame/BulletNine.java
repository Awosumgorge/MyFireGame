package com.google.android.gms.example.myfiregame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 9/6/2016.
 */
public class BulletNine {
    private boolean resetBullet = true; //for bullet Reset

    private GamePanel gamePanelViewNine;
    private Bitmap bulletNineBitMap;
    private int x = 0;
    private int y = 0;
    private TargetBirdOne targetBirdOne;
    private TargetBird targetBird;

    public BulletNine(GamePanel gamePanel, Bitmap bulletNineBitMap, int x, int y, TargetBird targetBird, TargetBirdOne targetBirdOne ){

        this.gamePanelViewNine = gamePanel;
        this.bulletNineBitMap = bulletNineBitMap;
        this.x = x;
        this.y = y;
        this.targetBirdOne = targetBirdOne;
        this.targetBird = targetBird;
    }

    public void onDraw(Canvas canvas){
        this.updateBullet();
        canvas.drawBitmap(this.bulletNineBitMap, this.x, this.y, null);
    }

    /**
     * Update the state of the sprite.
     */
    private void updateBullet(){

        if(GamePanel.isFired){

            if ( this.x < this.gamePanelViewNine.getBackgroundTotalWidth() && GamePanel.isFired){
                this.x += 20;

                isCollide();
                //isCollision();
                if ( this.x == ( this.gamePanelViewNine.getBackgroundTotalWidth() - 4) ){
                    this.x = this.gamePanelViewNine.getBackgroundTotalWidth();
                }

                if (this.x == this.gamePanelViewNine.getBackgroundTotalWidth() && GamePanel.bulletCount >= 9 ){
                    this.resetBullet = false; //for bullet Reset
                    GamePanel.isFired = false;//=setBulletFired(false);

                    GamePanel.bulletCount = 0;//-1;
                    GamePanel.bulletNine = null;
                }
            }
        } else if (this.x == this.gamePanelViewNine.getBackgroundTotalWidth() && !GamePanel.isFired && !this.resetBullet ) {
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
            Log.e("sadiq", "isCollide => targetBird hit success Bullet 9");
        } else if ( this.x  >= this.targetBirdOne.getX()
                && this.y >= (this.targetBirdOne.getY() )
                && this.y <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){
            isCollisionOne();
            Log.e("sadiq","isCollide => targetBirdOne hit success Bullet 9" );
        }
    }

    private void isCollisionOne(){
        if ( this.x  >= this.targetBirdOne.getX()
                && this.y >= (this.targetBirdOne.getY() )
                && this.y <= (this.targetBirdOne.getY() + this.targetBirdOne.getHeight())
                ){

            this.x = this.gamePanelViewNine.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBirdOne.setY(520);
            this.targetBirdOne.targetBirdBoolean(true);

        }else if (this.x > (this.gamePanelViewNine.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
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

            this.x = this.gamePanelViewNine.getBackgroundTotalWidth();//1024;
            GamePanel.scoreValue += 5;
            this.targetBird.setY(0);
            this.targetBird.targetBirdBoolean(true);

        }else if (this.x > (this.gamePanelViewNine.getBackgroundTotalWidth()-10) && GamePanel.totalChance > 0 ){//&& !isCollide
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

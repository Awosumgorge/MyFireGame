package com.google.android.gms.example.myfiregame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 8/23/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH =1024;//1024;//821;
    public static final int HEIGHT = 600;//768;//498;
    public static final int MOVESPEED = -5;

    public static int scoreValue = 0;
    public static int totalChance = 50;
    public static boolean isFired = false;

    public static Activity activity;

    private MainThread thread;
    private Background bg;
    private Player player;
    //private BulletNew bulletNew;// , bulletNew2 , bulletNew3 , bulletNew4 , bulletNew5;
    public static BulletNew newBulletnew;// , bulletNew2 , bulletNew3 , bulletNew4 , bulletNew5;
    private TargetBird targetBird;

    private int touchX[];
    private int touchY[];

    //private Bitmap bulletBitMap;
    private Bitmap targetBirdBitMap;

    private DialogBoxCustom gButton, gButton1;//for dialog box
    private Bitmap btnBitMap;//for dialog box

    private Paint textPaint;

    private TargetBirdOne targetBirdOne;
    private Bitmap targetBirdOneBitmap;

    //=============
    public static BulletOne bulletOne;
    public static BulletTwo bulletTwo;
    public static BulletThree bulletThree;
    public static BulletFour bulletFour;
    public static BulletFive bulletFive;
    public static BulletSix bulletSix;
    public static BulletSeven bulletSeven;
    public static BulletEight bulletEight;
    public static BulletNine bulletNine;

    private boolean boolIsFired = false;
    public static int bulletCount = 0;//-1;

    //======
    private BulletNew bulletNew = null; //0
    private Bitmap bulletBitMap;

    private BulletOne bulletOneNew = null; //1
    private Bitmap bulletOneBitMap;

    private BulletTwo bulletTwoNew = null; //2
    private Bitmap bulletTwoBitMap;

    private BulletThree bulletThreeNew = null; //3
    private Bitmap bulletThreeBitMap;

    private BulletFour bulletFourNew = null; //4
    private Bitmap bulletFourBitMap;

    private BulletFive bulletFiveNew = null; //5
    private Bitmap bulletFiveBitMap;

    private BulletSix bulletSixNew = null; //6
    private Bitmap bulletSixBitMap;

    private BulletSeven bulletSevenNew = null; //7
    private Bitmap bulletSevenBitMap;

    private BulletEight bulletEightNew = null; //8
    private Bitmap bulletEightBitMap;

    private BulletNine bulletNineNew = null; //9
    private Bitmap bulletNineBitMap;

    public GamePanel(Context context){
        super(context);

        //add the callBack in surfaceHolder events
        getHolder().addCallback(this);
        textPaint = new Paint();

        thread = new MainThread(getHolder(), this);

        btnBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.image_btn);//for dialog box

        //bulletBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

        targetBirdBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.twitter_circle_gray);
        targetBirdOneBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twitter_circle_gray);


        //make game panel focusable so it can be handel events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        createStar();
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionMasked();//event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerID = event.getPointerId(pointerIndex);
        int pointerCount = event.getPointerCount();

        touchX = new int[pointerCount];
        touchY = new int[pointerCount];

        for(int i = 0; i<pointerCount; i++){
            touchX[i] = (int) event.getX(i);
            touchY[i] = (int) event.getY(i);
        }

        switch (action){
            case MotionEvent.ACTION_DOWN:

                //this.bulletCount += 1;

                if ( !DialogBoxCustom.isGameOver ) {//for game over
                    //Log.e("onTouchDown", "DialogBoxCustom.isGameOver success if => " + DialogBoxCustom.isGameOver);

                    bulletBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1); //0
                    bulletOneBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1); //1
                    bulletTwoBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//2
                    bulletThreeBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//3
                    bulletFourBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//4
                    bulletFiveBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//5
                    bulletSixBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//6
                    bulletSevenBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//7
                    bulletEightBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//8
                    bulletNineBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);//9

                    /*BulletNew bulletNew = null; //0
                    Bitmap bulletBitMap;
                    bulletBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletOne bulletOneNew = null; //1
                    Bitmap bulletOneBitMap;
                    bulletOneBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletTwo bulletTwoNew = null; //2
                    Bitmap bulletTwoBitMap;
                    bulletTwoBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletThree bulletThreeNew = null; //3
                    Bitmap bulletThreeBitMap;
                    bulletThreeBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletFour bulletFourNew = null; //4
                    Bitmap bulletFourBitMap;
                    bulletFourBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletFive bulletFiveNew = null; //5
                    Bitmap bulletFiveBitMap;
                    bulletFiveBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletSix bulletSixNew = null; //6
                    Bitmap bulletSixBitMap;
                    bulletSixBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletSeven bulletSevenNew = null; //7
                    Bitmap bulletSevenBitMap;
                    bulletSevenBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletEight bulletEightNew = null; //8
                    Bitmap bulletEightBitMap;
                    bulletEightBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);

                    BulletNine bulletNineNew = null; //9
                    Bitmap bulletNineBitMap;
                    bulletNineBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire1);*/

                    if (touchX[0] > 10 && touchX[0] < 70 && touchY[0] > (GamePanel.HEIGHT/2)
                            && touchY[0] < ((GamePanel.HEIGHT/2) + 70) && this.bulletCount < 50
                            ) {
                        //if (!getBulletFired()) { //not true
                        if (!boolIsFired) { //not true
                            boolIsFired = true;//for if condition only
                            setBulletFired(true);

                            this.multiFireCase();

                            /*switch (this.bulletCount){
                                case 0:
                                    //Log.e("SwitchCase","case : 0");
                                    bulletNew = new BulletNew(this, bulletBitMap, 0, (GamePanel.HEIGHT/2) + 30 , targetBird );//, //anotherTargetBird
                                    newBulletnew = bulletNew;
                                    break;

                                case 1:
                                    //Log.e("SwitchCase","case : 1");
                                    bulletOneNew = new BulletOne( this, bulletOneBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletOne = bulletOneNew;
                                    break;

                                case 2:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletTwoNew = new BulletTwo( this, bulletTwoBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletTwo = bulletTwoNew;
                                    break;

                                case 3:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletThreeNew = new BulletThree( this, bulletThreeBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletThree = bulletThreeNew;
                                    break;

                                case 4:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletFourNew = new BulletFour( this, bulletFourBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletFour = bulletFourNew;
                                    break;

                                case 5:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletFiveNew = new BulletFive( this, bulletFiveBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletFive = bulletFiveNew;
                                    break;

                                case 6:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletSixNew = new BulletSix( this, bulletSixBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletSix = bulletSixNew;
                                    break;

                                case 7:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletSevenNew = new BulletSeven( this, bulletSevenBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletSeven = bulletSevenNew;
                                    break;

                                case 8:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletEightNew = new BulletEight( this, bulletEightBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletEight = bulletEightNew;
                                    break;

                                case 9:
                                    //Log.e("SwitchCase","case : 2");
                                    bulletNineNew = new BulletNine( this, bulletNineBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird );
                                    this.bulletNine = bulletNineNew;
                                    break;

                                default:
                                    Log.e("SwitchCase","Default");
                                    break;
                            }*/
                            this.bulletCount += 1;
                            boolIsFired = false;
                        }
                    }
                }//!DialogBoxCustom.isGameOver (if bracket)*/
                else if ( DialogBoxCustom.isGameOver && GamePanel.totalChance == 0 ){
                    if (touchX[0] > (int)(getBackgroundTotalWidth()/2.923) && touchX[0] < (getBackgroundTotalWidth()/2.41)
                            && touchY[0] > 300 && touchY[0] < 400

                            ){
                        GamePanel.totalChance = 50;
                        GamePanel.scoreValue = 0;
                        //this.targetBird.setY(0);
                        //this.targetBird.setX((this.bg.getTotalWidth() - (this.targetBird.getWidth() + 10)));

                        this.bulletCount = -1;

                        this.targetBird.targetBirdBoolean(true);
                        DialogBoxCustom.isGameOver = false;
                    }
                } //DialogBoxCustom.isGameOver && BulletNew.totalChance == 0 (else if bracket)
                break;
            case MotionEvent.ACTION_UP:
                //Log.e("touchDown", "touchDown Up !DialogBoxCustom.isGameOver => " + bulletNew.getBulletFired());
                if ( !DialogBoxCustom.isGameOver ) {//for game over
                    if (touchX[0] > 10 && touchX[0] < 70 && touchY[0] > 300 && touchY[0] < 370) {
                        //bulletNew.update(bulletBitMap);
                        //bulletNew.setBulletFired(false);
                        //setBulletFired(false);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void multiFireCase(  ){
        switch (this.bulletCount){
            case 0:
                //Log.e("SwitchCase","case : 0");
                bulletNew = new BulletNew(this, bulletBitMap, 0, (GamePanel.HEIGHT/2) + 30 , targetBird, targetBirdOne );//, //anotherTargetBird
                newBulletnew = bulletNew;
                break;

            case 1:
                //Log.e("SwitchCase","case : 1");
                bulletOneNew = new BulletOne( this, bulletOneBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletOne = bulletOneNew;
                break;

            case 2:
                //Log.e("SwitchCase","case : 2");
                bulletTwoNew = new BulletTwo( this, bulletTwoBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletTwo = bulletTwoNew;
                break;

            case 3:
                //Log.e("SwitchCase","case : 2");
                bulletThreeNew = new BulletThree( this, bulletThreeBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletThree = bulletThreeNew;
                break;

            case 4:
                //Log.e("SwitchCase","case : 2");
                bulletFourNew = new BulletFour( this, bulletFourBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletFour = bulletFourNew;
                break;

            case 5:
                //Log.e("SwitchCase","case : 2");
                bulletFiveNew = new BulletFive( this, bulletFiveBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletFive = bulletFiveNew;
                break;

            case 6:
                //Log.e("SwitchCase","case : 2");
                bulletSixNew = new BulletSix( this, bulletSixBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletSix = bulletSixNew;
                break;

            case 7:
                //Log.e("SwitchCase","case : 2");
                bulletSevenNew = new BulletSeven( this, bulletSevenBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletSeven = bulletSevenNew;
                break;

            case 8:
                //Log.e("SwitchCase","case : 2");
                bulletEightNew = new BulletEight( this, bulletEightBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletEight = bulletEightNew;
                break;

            case 9:
                //Log.e("SwitchCase","case : 2");
                bulletNineNew = new BulletNine( this, bulletNineBitMap, 0, (GamePanel.HEIGHT/2) + 30, targetBird, targetBirdOne );
                this.bulletNine = bulletNineNew;
                break;

            default:
                Log.e("SwitchCase", "Default");
                break;
        }
    }

    public void update(){
        bg.update();
    }

    /*public void update(){
        //if (player.getPlaying()){
            //bg.update();
            //player.update();
        //}
        //bullet.update();
    }*/

    @Override
    public void draw(Canvas canvas){

        /*final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT * 1.f);*/
        final float scaleFactorX = getWidth()/WIDTH;
        final float scaleFactorY = getHeight()/HEIGHT;

        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);

            bg.draw(canvas);

            player.draw(canvas);//player = gun

            if ( newBulletnew != null){
                newBulletnew.onDraw(canvas);
            }

            //BulletOne class
            if (this.bulletOne != null ){
                this.bulletOne.onDraw(canvas);
            }

            //BulletTwo Class
            if (this.bulletTwo != null ){
                this.bulletTwo.onDraw(canvas);
            }

            //BulletThree Class
            if (this.bulletThree != null ){
                //Log.e("here", "bullet three");
                this.bulletThree.onDraw(canvas);
            }

            //BulletFour Class
            if (this.bulletFour != null ){
                this.bulletFour.onDraw(canvas);
            }

            //BulletFive Class
            if (this.bulletFive != null ){
                this.bulletFive.onDraw(canvas);
            }

            //BulletSix Class
            if (this.bulletSix != null ){
                this.bulletSix.onDraw(canvas);
            }

            //bulletSeven Class
            if (this.bulletSeven != null ){
                this.bulletSeven.onDraw(canvas);
            }

            //BulletEight Class
            if (this.bulletEight != null ){
                this.bulletEight.onDraw(canvas);
            }

            //BulletNine Class
            if (this.bulletNine != null ){
                this.bulletNine.onDraw(canvas);
            }

            targetBird.onDraw(canvas);
            targetBirdOne.onDraw(canvas);

            //for dialog box
            if (GamePanel.totalChance == 0 && DialogBoxCustom.isGameOver) {
                textPaint.setColor(Color.RED);//for dialog box
                textPaint.setTextSize(35);//for dialog box
                canvas.drawText("My Fire Game ", 350, 200, textPaint);//for dialog box
                canvas.drawText("Your Game is Over!!! ", 350, 250, textPaint);//for dialog box
                gButton.draw(canvas);//for dialog box
                gButton1.draw(canvas);//for dialog box
            }

            textPaint.setColor(Color.RED);
            textPaint.setTextSize(20);
            canvas.drawText("Your Score : ", 10, 40, textPaint);
            canvas.drawText(String.valueOf(GamePanel.scoreValue), 125, 40, textPaint);

            canvas.drawText("Total Chance : ", 165, 40, textPaint);
            canvas.drawText(String.valueOf(GamePanel.totalChance), 300, 40, textPaint);

            canvas.restoreToCount(savedState);
        }
    }

    public void createStar(){                                   //950
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.img));
        bg.setVector(-5);

        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.twitter_circle_gray), 64, 64, 1);

        targetBird = new TargetBird(this, targetBirdBitMap, (bg.getTotalWidth() - 74 ), 0);
        targetBirdOne = new TargetBirdOne(this, targetBirdOneBitmap, (bg.getTotalWidth() - 74 ), 500 );
        //bulletNew = new BulletNew(this, bulletBitMap, 0, (GamePanel.HEIGHT/2) + 30 , targetBird );//, //anotherTargetBird

        gButton = new DialogBoxCustom( 100, 50, btnBitMap, 350, 300 );//for dialog box
        //gButton = new DialogBoxCustom( 100, 50, btnBitMap, (int)(getBackgroundTotalWidth()/2.923), (getBackgroundTotalHeight()/2) );//for dialog box
        gButton1 = new DialogBoxCustom(100, 50, btnBitMap, 500, 300 );//for dialog box
        //gButton1 = new DialogBoxCustom(100, 50, btnBitMap, (int)(getBackgroundTotalWidth()/2.046), (getBackgroundTotalHeight()/2) );//for dialog box
    }

    /**
     * backGround GetTotalBackgroudHeight
     */
    public int getBackgroundTotalHeight(){
        return bg.getTotalHeight();
    }

    /**
     * backGround GetTotalBackgroudWidth
     */
    public int getBackgroundTotalWidth(){
        return bg.getTotalWidth();
    }

    /**
     * boolean for bullet
     * @param isFired
     */
    public void setBulletFired(boolean isFired){
        this.isFired = isFired;
    }






    /**
     * boolean for bullet
     */
    /*public boolean getBulletFired(){ return isFired; }*/

    /**
     * backGround GetX
     */
    /*public int getStarshipX(){ //return  bulletNew.getX(); return  bg.getX(); }*/

    /**
     * backGround GetY
     */
    /*public int getStarshipY(){ //return bulletNew.getY(); return bg.getY(); }*/

}

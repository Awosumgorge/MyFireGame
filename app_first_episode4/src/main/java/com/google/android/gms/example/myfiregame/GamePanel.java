package com.google.android.gms.example.myfiregame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sadiq on 8/23/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 600;//1024;//821;
    public static final int HEIGHT = 1024;//768;//498;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    //private Background bg;
    private Player player;

    public GamePanel(Context context){
        super(context);

        //add the callBack in surfaceHolder events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make game panel focusable so it can be handel events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.game_background));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.twitter_circle_gray), 65, 65, 1);
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

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (!player.getPlaying()){
                player.setPlaying(true);
            } else {
                player.setUp(true);
            }
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP){
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update(){
        if (player.getPlaying()){
            //bg.update();
            player.update();
        }
    }

    @Override
    public void draw(Canvas canvas){

        Log.e("GamePanel", "getWIdth" + getWidth());
        Log.e("GamePanel", "getHeight" + getHeight());

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if (canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            //bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}

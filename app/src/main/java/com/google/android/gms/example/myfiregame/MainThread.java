package com.google.android.gms.example.myfiregame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by sadiq on 8/23/2016.
 */
public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    //private DialogPanel gamePanel;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
    //public MainThread(SurfaceHolder surfaceHolder, DialogPanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long frameCount = 0;
        long targetTime =1000/FPS; //this should be milli sec

        while (running){
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e){
            } finally {
                try{
                    if (canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;//how manu nano sec it took the update game one
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            } catch (Exception e){}

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                Log.e("MainThread", "averageFPs" + averageFPS);
            }
        }
    }

    public void setRunning(boolean b){
        running = b;
    }

}
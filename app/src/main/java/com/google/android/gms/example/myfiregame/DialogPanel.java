package com.google.android.gms.example.myfiregame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import CustomDialogBox.DialogBoxCustom;

/**
 * Created by sadiq on 8/26/2016.
 */
public class DialogPanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private Paint textPaint;
    //private Background bg;

    private DialogBoxCustom gButton, gButton1;
    Bitmap btnBitMap;


    public DialogPanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        textPaint = new Paint();
        //thread = new MainThread(getHolder(), this);

        btnBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.image_btn);


        setFocusable(true);
    }

    public void createCustomDialog(){
        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.img));
        //dialogCustomBox = new DialogCustomBox(this, activity,  250, 200);
        gButton = new DialogBoxCustom(100, 50, btnBitMap, 350, 300);
        gButton1 = new DialogBoxCustom(100, 50, btnBitMap, 500, 300);
    }

    @Override
    public void draw(Canvas canvas){

        final float scaleFactorX = getWidth()/(GamePanel.WIDTH*1.f);
        final float scaleFactorY = getHeight()/(GamePanel.HEIGHT*1.f);

        if (canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            //bg.draw(canvas);

            textPaint.setColor(Color.RED);
            textPaint.setTextSize(35);
            canvas.drawText("My Fire Game ", 350, 200, textPaint);
            canvas.drawText("Your Game is Over!!! ", 350, 250, textPaint);

            gButton.draw(canvas);
            gButton1.draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        createCustomDialog();

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
}

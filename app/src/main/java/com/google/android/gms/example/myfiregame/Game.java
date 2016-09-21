package com.google.android.gms.example.myfiregame;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class Game extends Activity {

    FrameLayout main_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //title Turn off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        main_frame = (FrameLayout) findViewById(R.id.main_frame);

        //screen resolution
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //full screen
        //setContentView(new GamePanel(this));

        loopStarPattern();

        GamePanel gamePanel = new GamePanel(this);
        main_frame.addView(gamePanel);
        //setContentView( new DialogPanel(this) );
    }

    /** draw this pattern using loop
     * 1234
     * 2341
     * 3412
     * 4123
     */
    private void practiceNestedLoop(){
        int counterInc = 1;
        Log.e("firstTime", "firstTime ");

        for (int i = 1; i <= 4; i++) { //4 rows

            for (int j = 1; j <= 4; j++) { //4 columns

                if ( i == 2 && j == 4 || i == 3 && j == 3 || i == 4 && j == 2){//
                   counterInc = 1;
                    //Log.e("abc", "abc i == 2 && j == 4 counterInc => " + counterInc);
                }
                Log.e("counterInc", "" + counterInc);
                counterInc++;
            }
            if (i == 1 ){
                counterInc = 2;
            } else if ( i == 2 ){
                counterInc = 3;
            } else if ( i == 3 ){
                counterInc = 4;
            }
        } //end parent i loop
    }

    /** draw this pattern using loop
     * *
     * **
     * ***
     * ****
     */
    private void loopStarPattern(){

        for (int row = 1; row <= 4; row++) {

            for(int column =1; column <= row; column++){
                Log.e("yahoo","*");
            }
        }
    }
}

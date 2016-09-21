package CustomDialogBox;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

/**
 * Created by sadiq on 8/26/2016.
 */
public class DialogBoxCustom {
    public static boolean isGameOver = false;

    private Matrix btn_matrix = new Matrix();
    private RectF btn_rect;
    private float width;
    private float height;
    private Bitmap bg;
    private int x = 0;
    private int y = 0;

    public DialogBoxCustom(float width, float height, Bitmap bg, int x, int y) {
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.x = x;
        this.y = y;

        btn_rect = new RectF(0, 0, width, height);
    }

    public void setPosition(float x, float y) {
        btn_matrix.setTranslate(x, y);
        btn_matrix.mapRect(btn_rect);
    }

    public void draw(Canvas canvas) {
        setPosition(this.x, this.y);
        canvas.drawBitmap(bg, btn_matrix, null);
    }

}

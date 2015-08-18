package com.fred.longd.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author fred
 * @since 15/8/12 下午10:57
 * @version v1.0
 */
public class MyProgressDialog extends View {

    private Paint paint = new Paint();

    public MyProgressDialog(Context context) {
        super(context);
    }

    public MyProgressDialog(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(20, 20, 400, 40);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(rectF, 8, 8, paint);
//        canvas.draw

    }
}

package com.example.user.myradiobutton;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RudioButton extends View {

    private Paint paint = new Paint();
    private Paint textPaint = new Paint();
    private Paint buttontoRotate = new Paint();
    private Paint frstInnerCircle = new Paint();
    private Paint amdistCircle = new Paint();
    private int numbersColor;
    private int lineColor;
    private int firstCircleColor;
    private int amidstCircleColor;
    private Path _path = new Path();
    private String numbers = "0 . 10 . 20 . 30 . 40 . 50 . 60 . 70 . 80 . 90 . 100 .";
    private double angle = -30;
    private float _cx;
    private float _cy;
    private double angles;
    private final int CORRECTION_NUMBER = 45;
    private int  volumeCount;


    public RudioButton(Context context) {
        super(context);
    }

    public RudioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        amidstCircleColor = Color.GREEN;
        numbersColor = Color.WHITE;
        firstCircleColor = Color.RED;
        lineColor = Color.BLUE;
        init(firstCircleColor,lineColor,numbersColor,amidstCircleColor);

    }

    private void init(int fCirc,int lin,int text,int amdist) {
        paint.setColor(firstCircleColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);

        buttontoRotate.setColor(lineColor);
        buttontoRotate.setStrokeWidth(3);
        buttontoRotate.setAntiAlias(true);

        textPaint.setTextSize(30);
        textPaint.setColor(numbersColor);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);

        amdistCircle.setStyle(Paint.Style.FILL);
        amdistCircle.setColor(amidstCircleColor);

        frstInnerCircle.setColor(Color.rgb(246, 40, 43));
        frstInnerCircle.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("Width " + widthMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        _cx = event.getX();
        _cy = event.getY();
        angles = (Math.toDegrees(Math.atan2(getHeight() / 2 - _cy, getWidth() / 2 - _cx)));
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (event.getEventTime() - event.getDownTime() < 200 && angles > -30) {
                    angle = angles;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (angles > -30) {
                    angle=angles;
                }
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.rgb(20, 20, 20));

        _path.addCircle(getWidth() / 2, getHeight() / 2, 150, Path.Direction.CW);
        canvas.drawTextOnPath(numbers, _path, 350, -20, textPaint);
        pintAndRotate((float) angle, canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, frstInnerCircle);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 95, amdistCircle);

         volumeCount = (int) (CORRECTION_NUMBER + angle) / 2;
        canvas.drawText( volumeCount + "", getWidth() / 2 - 10, getHeight() / 2, textPaint);
    }

    private void pintAndRotate(float degrees, Canvas canvas) {
        canvas.save();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 150, paint);
        canvas.rotate(degrees, getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(getWidth()/2-75,getHeight()/2,60,buttontoRotate);
        canvas.restore();
    }

    public int getCurrentValue()
    {
        return  volumeCount;
    }




}

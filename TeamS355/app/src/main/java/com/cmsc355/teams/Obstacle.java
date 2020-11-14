package com.cmsc355.teams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Obstacle {

    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private Paint paint;

//    private AccessibilityService context;
//    private WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//    private Display display = wm.getDefaultDisplay();
//
//
//    private Point size = new Point();

    public Obstacle(Context context, double positionX, double positionY, double width, double height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionX, (float) positionY, (float) (positionX+width), (float) (positionY+height), paint);
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getPositionX(){
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight(){
        return height;
    }
}

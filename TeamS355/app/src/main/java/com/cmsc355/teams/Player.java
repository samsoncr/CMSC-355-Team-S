package com.cmsc355.teams;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 500.0; //max speed of character movement
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;

//    private AccessibilityService context;
//    private WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//    private Display display = wm.getDefaultDisplay();
//
//
//    private Point size = new Point();

    private int width = 1100;
    private int height = 1500;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float) positionY, (float) radius, paint);

    }

    public void update(JoyStick joyStick) {
        velocityX = joyStick.getActuatorX()*MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;
        if(positionX + velocityX > 0 && positionX + velocityX < width){
            positionX += velocityX;
        }
        if(positionY + velocityY > 0 && positionY + velocityY < height){
            positionY += velocityY;
        }
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}

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
    private double velocityX;
    private double velocityY;
    private double accelerationX;
    private double accelerationY;
    private double windowHeight;
    private double windowWidth;


//    private AccessibilityService context;
//    private WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//    private Display display = wm.getDefaultDisplay();
//
//
//    private Point size = new Point();


    public Obstacle(Context context, double positionX, double positionY, double width, double height, double velocityX, double velocityY, double accelerationX, double accelerationY, double windowHeight, double windowWidth) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionX, (float) positionY, (float) (positionX+width), (float) (positionY+height), paint);
    }


    public void update(){
        if(velocityX < 0){
            velocityX -= accelerationX;
        }
        else if(velocityX > 0){
            velocityX += accelerationX;
        }
        if(velocityY < 0){
            velocityY -= accelerationY;
        }
        else if(velocityY > 0){
            velocityY += accelerationY;
        }
        if(positionX + velocityX < 0 || positionX + width + velocityX > windowWidth){
            velocityX = -velocityX;
        }
        if(positionY + velocityY < 0 || positionY + height + velocityY > windowHeight - 150){
            velocityY = -velocityY;
        }
        positionX += velocityX;
        positionY += velocityY;
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

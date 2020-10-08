package com.cmsc355.teams;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Character {

    private boolean upBtnPressed = false;
    private boolean downBtnPressed = false;
    private boolean leftBtnPressed = false;
    private boolean rightBtnPressed = false;
    private Bitmap image;
    private int x,y, velocityX, velocityY;

    public Character (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
        velocityX = 0;
        velocityY = 0;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(){
        updateVelocity();
        y += velocityY;
        x += velocityX;
    }

    public void triggerUpBtn(){
        upBtnPressed = !upBtnPressed;
    }
    public void triggerDownBtn(){
        downBtnPressed = !downBtnPressed;
    }
    public void triggerLeftBtn(){
        leftBtnPressed = !leftBtnPressed;
    }
    public void triggerRightBtn(){
        rightBtnPressed = !rightBtnPressed;
    }

    public void updateVelocity(){
        if(upBtnPressed && downBtnPressed){
            velocityY = 0;
        }
        else if(upBtnPressed){
            velocityY = -10;
        }
        else if(downBtnPressed){
            velocityY = 10;
        }
        else{
            velocityY = 0;
        }

        if(leftBtnPressed && rightBtnPressed){
            velocityX = 0;
        }
        else if(leftBtnPressed){
            velocityX = -10;
        }
        else if(rightBtnPressed){
            velocityX = 10;
        }
        else{
            velocityX = 0;
        }
    }

}

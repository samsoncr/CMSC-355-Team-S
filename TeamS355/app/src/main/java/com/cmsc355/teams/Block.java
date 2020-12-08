package com.cmsc355.teams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Block {
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private Paint paint;
    private double velocityX;
    private double velocityY;

//    private AccessibilityService context;
//    private WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//    private Display display = wm.getDefaultDisplay();
//
//
//    private Point size = new Point();

    public Block(Context context, double positionX, double positionY, double width, double height, double velocityX, double velocityY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.emerald);
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionX, (float) positionY, (float) (positionX+width), (float) (positionY+height), paint);
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void update(double playerPositionX, double playerPositionY, double playerRadius, Player player){
//        double playerPositionX, double playerPositionY, double playerRadius
        if(positionX + velocityX < 0 || positionX + width + velocityX > 1100){
            velocityX = -velocityX;
        }
        if(positionY + velocityY < 0 || positionY + height + velocityY > 1500){
            velocityY = -velocityY;
        }
        collideWithPlayer(playerPositionX, playerPositionY, playerRadius, player);
        positionX += velocityX;
        positionY += velocityY;
    }

    public void collideWithPlayer(double playerPositionX, double playerPositionY, double playerRadius, Player player){
        collideWithPlayerLeft(playerPositionX, playerPositionY, playerRadius, player);
        collideWithPlayerRight(playerPositionX, playerPositionY, playerRadius, player);
        collideWithPlayerTop(playerPositionX, playerPositionY, playerRadius, player);
        collideWithPlayerBottom(playerPositionX, playerPositionY, playerRadius, player);
    }
//
////    slopeX = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
////    slopeY = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
//
    public void collideWithPlayerLeft(double playerPositionX, double playerPositionY, double playerRadius, Player player){
        if(playerPositionX < positionX - playerRadius && playerPositionX > positionX + velocityX - playerRadius){
            double intersect = playerPositionY + ((playerPositionY + velocityY)-playerPositionY)/((playerPositionX + velocityX) - playerPositionX)*(positionX - playerRadius - playerPositionX);
            if(intersect > positionY - playerRadius && intersect < positionY + height + playerRadius) {
                player.setPositionX(playerPositionX + velocityX);
            }
        }
    }

    public void collideWithPlayerRight(double playerPositionX, double playerPositionY, double playerRadius, Player player){
        if(playerPositionX > positionX + width + playerRadius && playerPositionX < positionX + width + velocityX + playerRadius){
            double intersect = playerPositionY + ((playerPositionY + velocityY)-playerPositionY)/((playerPositionX + velocityX) - playerPositionX)*(positionX + width + playerRadius - playerPositionX);
            if(intersect > positionY - playerRadius && intersect < positionY + height + playerRadius) {
                player.setPositionX(playerPositionX + velocityX);
            }
        }
    }
    public void collideWithPlayerTop(double playerPositionX, double playerPositionY, double playerRadius, Player player){
        if(playerPositionY < positionY - playerRadius && playerPositionY > positionY + velocityY - playerRadius){
            double intersect = playerPositionX + ((playerPositionX + velocityX)-playerPositionX)/((playerPositionY + velocityY) - playerPositionY)*(positionY - playerRadius - playerPositionY);
            if(intersect > positionX - playerRadius && intersect < positionX + width + playerRadius) {
                player.setPositionY(playerPositionY + velocityY);
            }
        }
    }

    public void collideWithPlayerBottom(double playerPositionX, double playerPositionY, double playerRadius, Player player){
        if(playerPositionY > positionY + height + playerRadius && playerPositionY < positionY + height + velocityY + playerRadius){
            double intersect = playerPositionX + ((playerPositionX + velocityX)-playerPositionX)/((playerPositionY + velocityY) - playerPositionY)*(positionY + height + playerRadius - playerPositionY);
            if(intersect > positionX - playerRadius && intersect < positionX + width + playerRadius) {
                player.setPositionY(playerPositionY + velocityY);
            }
        }
    }

    public double getVelocityX(){
        return velocityX;
    }

    public double getVelocityY(){
        return velocityY;
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

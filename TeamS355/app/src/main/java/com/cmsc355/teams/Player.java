package com.cmsc355.teams;

import android.animation.Animator;
import android.app.Activity;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

//Test number 5 for Continuous Integration On ChristopherSamson branch

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 500.0; //max speed of character movement
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final double width;
    private final double height;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;
    private double slopeX = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
    private double slopeY = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
    private boolean gameOver = false;
    private double windowHeight;
    private double windowWidth;



//    private int width = 1100;
//    private int height = 1500;

    public Player(Context context, double positionX, double positionY, double radius, double windowHeight, double windowWidth) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //For obtaining screen size
        this.width = size.x;
        this.height = size.y;


    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float) positionY, (float) radius, paint);


    }

    public int getStatusBarHeight(Context context){
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
    public int getNavigationBarHeight(Context context){
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

//        velocityX = joyStick.getActuatorX()*MAX_SPEED;
//        velocityY = joyStick.getActuatorY()*MAX_SPEED;
//        if((velocityX + positionX) < (width - radius) && (velocityX + positionX) > (0 + radius)){
//            positionX += velocityX;
//        }
//        if((velocityY + positionY) < (height + statuBarHeight - bottomBarHeight - radius) && (velocityY + positionY) > 0 + radius){
//            positionY += velocityY;
//        }
        //positionX += velocityX;
        //positionY += velocityY;
    public void update(JoyStick joyStick, ArrayList<Block> blocks, ArrayList<Obstacle> obstacles, ArrayList<RotateObstacle> rotateObstacles) {
        velocityX = joyStick.getActuatorX()*MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;
        slopeX = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        slopeY = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
        for(Obstacle obstacle : obstacles){
            collideWithObstacle(obstacle.getPositionX(), obstacle.getPositionY(), obstacle.getWidth(), obstacle.getHeight());

        }

        for(RotateObstacle rotateObstacle : rotateObstacles){
            //collideWithObstacle(rotateObstacle.getPositionX(), rotateObstacle.getPositionY(), rotateObstacle.getWidth(), rotateObstacle.getHeight());
        }

        for(Block block : blocks){
            collideWithBlock(block.getPositionX(), block.getPositionY(), block.getWidth(), block.getHeight(), block.getVelocityX(), block.getVelocityY());
//            Log.i("blockposition", block.getPositionX()+"");
        }

        if(positionX - radius + velocityX < 0 || positionX + radius + velocityX > windowWidth){

            velocityX = 0;
        }
        if(positionY - radius + velocityY < 0 || positionY + radius + velocityY > windowHeight - 150){
            velocityY = 0;
        }
        positionX += velocityX;
        positionY += velocityY;
        if(positionX > windowWidth || positionX < 0 || positionY < 0 || positionY > windowHeight - 150){
            gameOver = true;
        }
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void collideWithBlock(double blockX, double blockY, double blockWidth, double blockHeight, double blockVelocityX, double blockVelocityY){
        collideLeftBlock(blockX, blockY, blockHeight, blockVelocityX);
        collideRightBlock(blockX, blockY, blockWidth, blockHeight, blockVelocityX);
        collideTopBlock(blockX, blockY, blockWidth, blockVelocityY);
        collideBottomBlock(blockX, blockY, blockWidth, blockHeight, blockVelocityY);
//        Log.i("collideWithBlock", "collideWithBlock");
    }
    public void collideLeftBlock(double blockX, double blockY, double blockHeight, double blockVelocityX){
        if(positionX < blockX - radius && positionX + velocityX > blockX - radius){
            double intersect = positionY + slopeX*(blockX - radius - positionX);
            if(intersect > blockY - radius && intersect < blockY + blockHeight + radius) {
                if(blockVelocityX < 0){
                    velocityX = 0;
                }
                else{
                    velocityX = blockVelocityX;
                }

//                Log.i("collideLeft", "collideLeft");
            }
//            Log.i("collideLeft", "collideLeft");
        }

    }
    public void collideRightBlock(double blockX, double blockY, double blockWidth, double blockHeight, double blockVelocityX){
        if(positionX > blockX + blockWidth + radius && positionX + velocityX < blockX + blockWidth + radius){
            double intersect = positionY + slopeX*(blockX + blockWidth + radius - positionX);
//            Log.i("Collision?", "before");

            if(intersect > blockY - radius && intersect < blockY + blockHeight + radius) {
                if(blockVelocityX > 0){
                    velocityX = 0;
                }
                else{
                    velocityX = blockVelocityX;
                }
//                Log.i("Collision?", "Collided");
            }
        }
    }
    public void collideTopBlock(double blockX, double blockY, double blockWidth, double blockVelocityY){
        if(positionY < blockY - radius && positionY + velocityY > blockY - radius){
            double intersect = positionX + slopeY*(blockY - radius - positionY);
            if(intersect > blockX - radius && intersect < blockX + blockWidth + radius){
//                Log.i("Collision?", "Collided");
                if(blockVelocityY < 0){
                    velocityY = 0;
                }
                else{
                    velocityY = blockVelocityY;
                }
            }
        }
    }
    public void collideBottomBlock(double blockX, double blockY, double blockWidth, double blockHeight, double blockVelocityY){
        if(positionY > blockY + blockHeight + radius && positionY + velocityY < blockY + blockHeight + radius){
            double intersect = positionX + slopeY*(blockY + blockHeight + radius - positionY);
            if(intersect > blockX - radius && intersect < blockX + blockWidth + radius){
//                Log.i("Collision?", "Collided");
                if(blockVelocityY > 0){
                    velocityY = 0;
                }
                else{
                    velocityY = blockVelocityY;
                }
            }
        }
    }

    public void collideWithObstacle(double obstacleX, double obstacleY, double obstacleWidth, double obstacleHeight){
        if(positionX < obstacleX + obstacleWidth + radius && positionX > obstacleX - radius && positionY > obstacleY - radius && positionY < obstacleY + obstacleHeight + radius){
            gameOver = true;
        }
    }
//-------------------------------------------------------------------
    public boolean CollisionWithTopLeftCorner(double x, double y, double radius){
        double slope = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        double linearIntersect;

        if(x < positionX){
            linearIntersect = positionY + slope*(x-positionX);
        }
        else if(positionX < x){
            linearIntersect = positionY - slope*(x-positionX);
        }
        else{
            linearIntersect = positionY;
        }
        double b = ((2*slope)*(linearIntersect - radius)-(2*radius));
        double a = (slope*slope) + 1;
        double c = radius*radius*(linearIntersect-radius)*(linearIntersect-radius)-(radius*radius);
        double x1Intercept = (-b + Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y1Intercept = (slope*x1Intercept) + linearIntersect;

        if(x1Intercept < x && y1Intercept < y){
            return true;
        }

        double x2Intercept = (-b - Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y2Intercept = (slope*x1Intercept) + linearIntersect;

        if(x2Intercept < x && y2Intercept < y){
            return true;
        }

        return false;
    }
    public boolean CollisionWithTopRightCorner(double x, double y, double radius){
        double slope = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        double linearIntersect;

        if(x < positionX){
            linearIntersect = positionY + slope*(x-positionX);
        }
        else if(positionX < x){
            linearIntersect = positionY - slope*(x-positionX);
        }
        else{
            linearIntersect = positionY;
        }
        double b = ((2*slope)*(linearIntersect - radius)-(2*radius));
        double a = (slope*slope) + 1;
        double c = radius*radius*(linearIntersect-radius)*(linearIntersect-radius)-(radius*radius);
        double x1Intercept = (-b + Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y1Intercept = (slope*x1Intercept) + linearIntersect;

        if(x1Intercept > x && y1Intercept < y){
            return true;
        }

        double x2Intercept = (-b - Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y2Intercept = (slope*x1Intercept) + linearIntersect;

        if(x2Intercept > x && y2Intercept < y){
            return true;
        }

        return false;
    }
    public boolean CollisionWithBottomLeftCorner(double x, double y, double radius){
        double slope = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        double linearIntersect;

        if(x < positionX){
            linearIntersect = positionY + slope*(x-positionX);
        }
        else if(positionX < x){
            linearIntersect = positionY - slope*(x-positionX);
        }
        else{
            linearIntersect = positionY;
        }
        double b = ((2*slope)*(linearIntersect - radius)-(2*radius));
        double a = (slope*slope) + 1;
        double c = radius*radius*(linearIntersect-radius)*(linearIntersect-radius)-(radius*radius);
        double x1Intercept = (-b + Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y1Intercept = (slope*x1Intercept) + linearIntersect;

        if(x1Intercept < x && y1Intercept > y){
            return true;
        }

        double x2Intercept = (-b - Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y2Intercept = (slope*x1Intercept) + linearIntersect;

        if(x2Intercept < x && y2Intercept > y){
            return true;
        }

        return false;
    }
    public boolean CollisionWithBottomRightCorner(double x, double y, double radius){
        double slope = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        double linearIntersect;

        if(x < positionX){
            linearIntersect = positionY + slope*(x-positionX);
        }
        else if(positionX < x){
            linearIntersect = positionY - slope*(x-positionX);
        }
        else{
            linearIntersect = positionY;
        }
        double b = ((2*slope)*(linearIntersect - radius)-(2*radius));
        double a = (slope*slope) + 1;
        double c = radius*radius*(linearIntersect-radius)*(linearIntersect-radius)-(radius*radius);
        double x1Intercept = (-b + Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y1Intercept = (slope*x1Intercept) + linearIntersect;

        if(x1Intercept > x && y1Intercept > y){
            return true;
        }

        double x2Intercept = (-b - Math.sqrt(((b*b)-(4*a*c))))/(2*a);
        double y2Intercept = (slope*x1Intercept) + linearIntersect;

        if(x2Intercept > x && y2Intercept > y){
            return true;
        }

        return false;
    }
    public void SlideOnCircle(double x, double y, double circleRadius){

    }
//------------------------------------------------------------
    public void setVelocityX(double newVelocityX){
        velocityX = newVelocityX;
    }
    public void setVelocityY(double newVelocityY){
        velocityY = newVelocityY;
    }
    public double getPositionX(){
        return positionX;
    }
    public double getPositionY(){
        return positionY;
    }
    public double getRadius(){
        return radius;
    }


    public boolean getGameOver(){
        return gameOver;
    }

}

package com.cmsc355.teams;

import android.app.Activity;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

//Test number 5 for Continuous Integration On ChristopherSamson branch

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 500.0; //max speed of character movement
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final double width;
    private final double height;
    private final int bottomBarHeight;
    private final int statuBarHeight;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;
    private double slopeX = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
    private double slopeY = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
    private boolean gameOver = false;


//    private int width = 1100;
//    private int height = 1500;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.width = size.x;
        this.height = size.y;
        this.statuBarHeight = getStatusBarHeight(context);
        this.bottomBarHeight = getNavigationBarHeight(context);

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
    public void update(JoyStick joyStick, ArrayList<Block> blocks, ArrayList<Obstacle> obstacles) {
        velocityX = joyStick.getActuatorX()*MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;
        slopeX = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        slopeY = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
        for(Obstacle obstacle : obstacles){
            collideWithObstacle(obstacle.getPositionX(), obstacle.getPositionY(), obstacle.getWidth(), obstacle.getHeight());
        }

        for(Block block : blocks){
            collideWithBlock(block.getPositionX(), block.getPositionY(), block.getWidth(), block.getHeight());
//            Log.i("blockposition", block.getPositionX()+"");
        }
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void collideWithBlock(double blockX, double blockY, double blockWidth, double blockHeight){
        collideLeftBlock(blockX, blockY, blockWidth, blockHeight);
        collideRightBlock(blockX, blockY, blockWidth, blockHeight);
        collideTopBlock(blockX, blockY, blockWidth, blockHeight);
        collideBottomBlock(blockX, blockY, blockWidth, blockHeight);
//        Log.i("collideWithBlock", "collideWithBlock");
    }
    public void collideLeftBlock(double blockX, double blockY, double blockWidth, double blockHeight){
        if(positionX < blockX - radius && positionX + velocityX > blockX - radius){
            double intersect = positionY + slopeX*(blockX - radius - positionX);
            if(intersect > blockY - radius && intersect < blockY + blockHeight + radius) {
                setVelocityX(0);
//                Log.i("collideLeft", "collideLeft");
            }
//            Log.i("collideLeft", "collideLeft");
        }

    }
    public void collideRightBlock(double blockX, double blockY, double blockWidth, double blockHeight){
        if(positionX > blockX + blockWidth + radius && positionX + velocityX < blockX + blockWidth + radius){
            double intersect = positionY + slopeX*(blockX + blockWidth + radius - positionX);
//            Log.i("Collision?", "before");

            if(intersect > blockY - radius && intersect < blockY + blockHeight + radius) {
                setVelocityX(0);
//                Log.i("Collision?", "Collided");
            }
        }
    }
    public void collideTopBlock(double blockX, double blockY, double blockWidth, double blockHeight){
        if(positionY < blockY - radius && positionY + velocityY > blockY - radius){
            double intersect = positionX + slopeY*(blockY - radius - positionY);
            if(intersect > blockX - radius && intersect < blockX + blockWidth + radius){
                Log.i("Collision?", "Collided");
                setVelocityY(0);
            }
        }
    }
    public void collideBottomBlock(double blockX, double blockY, double blockWidth, double blockHeight){
        if(positionY > blockY + blockHeight + radius && positionY + velocityY < blockY + blockHeight + radius){
            double intersect = positionX + slopeY*(blockY + blockHeight + radius - positionY);
            if(intersect > blockX - radius && intersect < blockX + blockWidth + radius){
                Log.i("Collision?", "Collided");
                setVelocityY(0);
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

    public boolean getGameOver(){
        return gameOver;
    }

}

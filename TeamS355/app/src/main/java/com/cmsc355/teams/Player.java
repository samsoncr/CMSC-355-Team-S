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

//Test number 2 for Continuous Integration On ChristopherSamson branch

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
    public void update(JoyStick joyStick, ArrayList<Block> blocks) {
        velocityX = joyStick.getActuatorX()*MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;
        for(Block block : blocks){
            if(positionX + velocityX > 0 && positionX + velocityX < width){
                if(!xCollisionWithRectangle(block.getPositionX(), block.getPositionY(), block.getWidth(), block.getHeight())){
                    positionX += velocityX;
                    Log.i("updating", "updating");
                }
            }
            Log.i("update", "something");
            if(positionY + velocityY > 0 && positionY + velocityY < height){
                if(!yCollisionWithRectangle(block.getPositionX(), block.getPositionY(), block.getWidth(), block.getHeight())){
                    positionY += velocityY;
                    Log.i("updating", "updating");
                }
            }
        }
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean xCollisionWithRectangle(double x, double y, double width, double height){
        double slope = ((positionY + velocityY)-positionY)/((positionX + velocityX) - positionX);
        if(positionX < x && positionX + velocityX > x){
            double intersect = positionY + slope*(x-positionX);
            if(intersect > y && intersect < y + height){
                Log.i("Collision?", "Collided");
                return true;
        }
        } else if (positionX > x + width && positionX + velocityX < x + width) {
            double intersect = positionY - slope*(positionX-(x+width));
            if(intersect > y && intersect < y + height){
                Log.i("Collision?", "Collided");
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean yCollisionWithRectangle(double x, double y, double width, double height){
        Log.i("Collision?", "start");

        double slope = ((positionX + velocityX)-positionX)/((positionY + velocityY) - positionY);
        if(positionY < y && positionY + velocityY > y){
            double intersect = positionX + slope*(y-positionY);
            if(intersect > x && intersect < x + width){
                Log.i("Collision?", "Collided");
                return true;
            }
//            else if(CollisionWithCircle(x, y, radius)){
//                    SlideOnCircle(x, y, radius);
//                    return true;
//            }
//            else if(CollisionWithCircle(x+width, y, radius)){
//                SlideOnCircle(x, y, radius);
//                return true;
//            }
        } else if (positionY > y + height && positionY +velocityY < y + height) {
            double intersect = positionX - slope*(positionY-(y+height));
            if(intersect > x && intersect < x + width){
                Log.i("Collision?", "Collided");
                return true;
            }
//            else if(CollisionWithCircle(x, y+height, radius)){
//                SlideOnCircle(x, y, radius);
//                return true;
//            }
//            else if(CollisionWithCircle(x+width, y+height, radius)){
//                SlideOnCircle(x, y, radius);
//                return true;
//            }
        } else {
            return false;
        }
        return false;
    }

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
}

package com.cmsc355.teams;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

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

    public void update(JoyStick joyStick) {

        velocityX = joyStick.getActuatorX()*MAX_SPEED;
        velocityY = joyStick.getActuatorY()*MAX_SPEED;
        if((velocityX + positionX) < (width - radius) && (velocityX + positionX) > (0 + radius)){
            positionX += velocityX;
        }
        if((velocityY + positionY) < (height + statuBarHeight - bottomBarHeight - radius) && (velocityY + positionY) > 0 + radius){
            positionY += velocityY;
        }
        //positionX += velocityX;
        //positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}

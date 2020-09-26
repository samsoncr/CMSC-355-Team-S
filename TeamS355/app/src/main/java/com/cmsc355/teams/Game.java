package com.cmsc355.teams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Game manages all objects in the game and is responsible for updating all states and render all object to the screen
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;

    public Game(Context context) {
        super(context);
        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        gameLoop = new GameLoop(this, surfaceHolder);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
    }
    // UPS FUNCTION
    public void drawUPS(Canvas canvas){
        //get average UPS from gameLoop class
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        // set string color
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.emerald);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }
    // FPS FUNCTION
    public void drawFPS(Canvas canvas){
        //get average FPS from gameLoop class
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        //Set string color and size
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.emerald);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {

    }
}

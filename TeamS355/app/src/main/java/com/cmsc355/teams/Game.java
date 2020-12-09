package com.cmsc355.teams;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

/**
 * Game manages all objects in the game and is responsible for updating all states and render all object to the screen
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final JoyStick joystick;
    private final Player player;
    private GameLoop gameLoop;
//    private final Block block;
    private final ArrayList<Block> blocks;
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<RotateObstacle> rotateObstacles;
    private boolean gameOver = false;
    private Context context;
    private MediaPlayer rapWest;
    private MediaPlayer gameOverSound;


    public Game(Context context) {
        super(context);
        this.context = context;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        // Initialize player
        player = new Player(getContext(),1000,500,30);
//        block = new Block(getContext(), 200, 200, 200, 200);
        blocks = new ArrayList<>();
        blocks.add(new Block(getContext(), 200, 200, 300, 300, 10, 10));
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(getContext(), 500, 500, 100, 100, 0.5, 0.5, 0.001, 0.001));
        obstacles.add(new Obstacle(getContext(), 500, 700, 100, 100, 0.75, 0.75, 0.001, 0.001));
        obstacles.add(new Obstacle(getContext(), 500, 900, 100, 100, 0.6, 0.35, 0.001, 0.001));
        obstacles.add(new Obstacle(getContext(), 500, 1100, 100, 100, 0.3, 0.9, 0.001, 0.001));

        rotateObstacles = new ArrayList<>();
        rotateObstacles.add(new RotateObstacle(getContext(), 45, 700, 400, 150, 150, 1, 1, 0.001, 0.001));
        rotateObstacles.add(new RotateObstacle(getContext(), -45, 900, 500, 150, 150, 1, 1, 0.001, 0.001));

        // Initialize game object
        joystick = new JoyStick(775, 1250, 100, 50);
        setFocusable(true);

        rapWest = MediaPlayer.create(context,R.raw.rap_west);
        rapWest.start();
        gameOverSound = MediaPlayer.create(context,R.raw.leszek_szary_game_over);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle different touch event actions
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double) event.getX(), (double) event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);
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

        joystick.draw(canvas);
        player.draw(canvas);
        for(Block block : blocks){
            block.draw(canvas);
        }
        for(Obstacle obstacle: obstacles){
            obstacle.draw(canvas);
        }
        for(RotateObstacle rotateObstacle : rotateObstacles){
            rotateObstacle.draw(canvas);
        }

    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        // Update game state
        joystick.update();
        for(Block block: blocks){
            block.update(player.getPositionX(), player.getPositionY(), player.getRadius(), player);
        }
        for(Obstacle obstacle: obstacles){
            obstacle.update();
        }
        for(RotateObstacle rotateObstacle: rotateObstacles){
            rotateObstacle.update();
        }
        player.update(joystick, blocks, obstacles, rotateObstacles);

        if(player.getGameOver()){
            rapWest.pause();
            gameOverSound.start();
            gameOver = true;
//            Intent i = new Intent(context, GameActivity.class);
//            context.sendBroadcast(i);
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }

}

































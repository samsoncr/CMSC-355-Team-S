package com.cmsc355.teams;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.view.GestureDetector.SimpleOnGestureListener;

public class PlayingDefaultMapActivity extends Activity {

    GameView gameView;
    FrameLayout game;
    RelativeLayout gameButtons;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("onCreate PlayingDefaultMapActivity");
//        Log.println(Log.INFO, "testTag", "onCreate PlayingDefaultMapActivity");
//        Log.d("testTag", "foo");
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(new GameView(this));

        gameView = new GameView(this);
        game = new FrameLayout(this);
        gameButtons = new RelativeLayout(this);

        Button moveUpBtn = new Button(this);
        moveUpBtn.setText("UP");
        moveUpBtn.setId(111111);

        Button moveDownBtn = new Button(this);
        moveDownBtn.setText("DOWN");
        moveDownBtn.setId(222222);

        Button moveLeftBtn = new Button(this);
        moveLeftBtn.setText("LEFT");
        moveLeftBtn.setId(333333);

        Button moveRightBtn = new Button(this);
        moveRightBtn.setText("RIGHT");
        moveRightBtn.setId(444444);

        Button previousPageBtn = new Button(this);
        previousPageBtn.setText("Previous");
        previousPageBtn.setId(555555);

        RelativeLayout.LayoutParams bUp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams bDown = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams bLeft = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams bRight = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams bPrevious = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        gameButtons.setLayoutParams(params);

        gameButtons.addView(moveUpBtn);
        gameButtons.addView(moveDownBtn);
        gameButtons.addView(moveLeftBtn);
        gameButtons.addView(moveRightBtn);
        gameButtons.addView(previousPageBtn);

        bUp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        bUp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        moveUpBtn.setLayoutParams(bUp);

        bDown.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bDown.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        moveDownBtn.setLayoutParams(bDown);

        bLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        moveLeftBtn.setLayoutParams(bLeft);

        bRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        moveRightBtn.setLayoutParams(bRight);

        bPrevious.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        bPrevious.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        previousPageBtn.setLayoutParams(bPrevious);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        game.addView(gameView);
        game.addView(gameButtons);
        setContentView(game);


        moveUpBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gameView.triggerUpBtn();
                return false;
            }
        });
        moveDownBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gameView.triggerDownBtn();
                return false;
            }
        });
        moveLeftBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gameView.triggerLeftBtn();
                return false;
            }
        });
        moveRightBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gameView.triggerRightBtn();
                return false;
            }
        });

    }

}
package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
        moveLeftBtn.setText("Left");
        moveLeftBtn.setId(333333);

        Button moveRightBtn = new Button(this);
        moveRightBtn.setText("");
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
        moveUpBtn.setLayoutParams(bUp);
        bDown.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        moveDownBtn.setLayoutParams(bDown);
        bLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        moveLeftBtn.setLayoutParams(bLeft);
        bRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        bRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        moveRightBtn.setLayoutParams(bRight);
        bPrevious.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        bPrevious.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        previousPageBtn.setLayoutParams(bRight);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        game.addView(gameView);
        game.addView(gameButtons);
        setContentView(game);


//        Button playActivityBtn = (Button) findViewById(R.id.playActivityBtn);
//        playActivityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent startPlayIntent = new Intent(getApplicationContext(), PlayActivity.class);
//                startActivity(startPlayIntent);
//            }
//        });
//
//        Button moveUpBtn = (Button) findViewById(R.id.moveUpBtn);
//        moveUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageView character = (ImageView) findViewById(R.id.character);
//                character.setY(character.getY() - 5);
//
//            }
//        });
//
//        Button moveDownBtn = (Button) findViewById(R.id.moveDownBtn);
//        moveDownBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageView character = (ImageView) findViewById(R.id.character);
//                character.setY(character.getY() + 5);
//
//            }
//        });
//
//        Button moveLeftBtn = (Button) findViewById(R.id.moveLeftBtn);
//        moveLeftBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageView character = (ImageView) findViewById(R.id.character);
//                character.setX(character.getX() - 5);
//            }
//        });
//
//        Button moveRightBtn = (Button) findViewById(R.id.moveRightBtn);
//        moveRightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImageView character = (ImageView) findViewById(R.id.character);
//                character.setX(character.getX() + 5);
//            }
//        });


    }


}
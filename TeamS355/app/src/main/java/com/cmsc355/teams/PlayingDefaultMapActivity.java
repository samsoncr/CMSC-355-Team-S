package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PlayingDefaultMapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate PlayingDefaultMapActivity");
        Log.println(Log.INFO, "testTag", "onCreate PlayingDefaultMapActivity");
        Log.d("testTag", "foo");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

        setContentView(new Button(this), WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);


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
package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //allows the user to click this button to go to the main menu
        Button mainMenuBtn = (Button)findViewById(R.id.mainMenuBtn);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlayIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startPlayIntent);
            }
        });

        //allows the user to click the map to choose the activity the user wanted to play
        Button playMapActivityBtn = (Button)findViewById(R.id.playMapActivityBtn);
        playMapActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlayIntent = new Intent(getApplicationContext(), PlayingDefaultMapActivity.class);
                startActivity(startPlayIntent);
            }
        });


        Button gameLoopBtn = (Button)findViewById(R.id.gameLoopBtn);
        gameLoopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlayIntent = new Intent(getApplicationContext(), PlayingActivity_B.class);
                startActivity(startPlayIntent);
            }
        });

    }
}
package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Audio setup
        AudioManager audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
        final MediaPlayer village_main_sound = MediaPlayer.create(this,R.raw.village_main_theme);
        village_main_sound.start();
        village_main_sound.setLooping(true);

        //allows the user to click this button to go to the main menu
        Button mainMenuBtn = (Button)findViewById(R.id.mainMenuBtn);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                village_main_sound.pause();
                Intent startPlayIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startPlayIntent);
            }
        });

        //allows the user to click the map to choose the activity the user wanted to play
        Button playMapActivityBtn = (Button)findViewById(R.id.playMapActivityBtn);
        playMapActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                village_main_sound.pause();
                Intent startPlayIntent = new Intent(getApplicationContext(), PlayingDefaultMapActivity.class);
                startActivity(startPlayIntent);
            }
        });

        //JoyStick controller Launch button
        Button joyStickBtn = (Button)findViewById(R.id.joyStickBtn);
        joyStickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setContentView(new Game(getApplicationContext()));
                village_main_sound.pause();
                Intent startPlayIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startPlayIntent);
            }
        });

    }
}
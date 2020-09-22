package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PlayingDefaultMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_default_map);

        //ImageView character = (ImageView)findViewById(R.id.character);

        Button playActivityBtn = (Button)findViewById(R.id.playActivityBtn);
        playActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlayIntent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(startPlayIntent);
            }
        });

        Button moveUpBtn = (Button)findViewById(R.id.moveUpBtn);
        moveUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView character = (ImageView)findViewById(R.id.character);
                character.setY(character.getY()-5);
            }
        });

        Button moveDownBtn = (Button)findViewById(R.id.moveDownBtn);
        moveDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView character = (ImageView)findViewById(R.id.character);
                character.setY(character.getY()+5);
            }
        });

        Button moveLeftBtn = (Button)findViewById(R.id.moveLeftBtn);
        moveLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView character = (ImageView)findViewById(R.id.character);
                character.setX(character.getX()-5);
            }
        });

        Button moveRightBtn = (Button)findViewById(R.id.moveRightBtn);
        moveRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView character = (ImageView)findViewById(R.id.character);
                character.setX(character.getX()+5);
            }
        });

    }

}
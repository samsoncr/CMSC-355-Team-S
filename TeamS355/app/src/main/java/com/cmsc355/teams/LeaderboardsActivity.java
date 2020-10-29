package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeaderboardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        //allows the user to see the previous scores on the game
        Button mainMenuBtn = (Button)findViewById(R.id.mainMenuBtn);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlayIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startPlayIntent);
            }
        });

    }

}
package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
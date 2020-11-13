package com.cmsc355.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(getApplicationContext());
        setContentView(game);
//        finish();
//        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(i);
    }

//    BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            mReceiver.onReceive(context, intent);
//
//            finish();
//        }
//    };

}
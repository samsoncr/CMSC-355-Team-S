package com.cmsc355.teams;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

//UI should be able to allow the user to see which buttons to clearly press to start game/go to
//other screens such as the leader board
public class MainActivity extends AppCompatActivity {
//This is the fixed line.
    //Put the variables here and the modifiers as private for encapsulation For ex: private Toolbar toolbar
    //Then initialize the variable in th onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set background music from raw resource folder and placed it in loop
        //background music automatically plays when game is opened

        AudioManager audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

        final MediaPlayer sexyJazzLoop= MediaPlayer.create(this,R.raw.sexy_jazz_loop);
        sexyJazzLoop.start();
        //sexy_jazz_loop.setLooping(true);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //The following are buttons in the main menu
        Button playActivityBtn = (Button)findViewById(R.id.playActivityBtn);
        playActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), PlayActivity.class);
                sexyJazzLoop.pause(); //pause main menu music
                startActivity(startIntent);
            }
        });



        Button settingsActivityBtn = (Button)findViewById(R.id.settingsActivityBtn);
        settingsActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                sexyJazzLoop.pause(); //pause main menu music
                startActivity(startIntent);
            }
        });

        Button creativeActivityBtn = (Button)findViewById(R.id.creativeActivityBtn);
        creativeActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), CreativeActivity.class);
                sexyJazzLoop.pause(); //pause main menu music
                startActivity(startIntent);
            }
        });

        Button mapShareActivityBtn = (Button)findViewById(R.id.mapShareActivityBtn);
        mapShareActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MapShareActivity.class);
                sexyJazzLoop.pause(); //pause main menu music
                startActivity(startIntent);
            }
        });
        //undid the comment because it messed up the sound whenever you would press
        //the leader board button
        Button leaderboardsActivityBtn = (Button)findViewById(R.id.leaderboardsActivityBtn);
        leaderboardsActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                sexyJazzLoop.pause();
                startActivity(startIntent);
            }
        });

    /*} //had to comment this out because it messed up the sound and leader board button
    //Better way of doing a onclick listener because doing it in the onCreate method is gonna slow
    //down the app and its a old way of implementing button clicks
    public void openLeaderboard(View view){
        Intent intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
        startActivity(intent); */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Leaderboards(View view) {
    }
}
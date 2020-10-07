package com.cmsc355.teams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;

import androidx.core.content.res.ResourcesCompat;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Character character;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        Log.d("GameView", "about to create MaintThread");
        thread = new MainThread(getHolder(), this);
        Log.d("GameView", thread.toString());
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("GameView", "surfaceCreated is being called");
        thread.setRunning(true);
        thread.start();
        this.character = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.square));
//        this.character = new Character((ResourcesCompat.getDrawable(this.resources, R.drawable.character, null) as VectorDrawable).toBitmap());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        character.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            character.draw(canvas);
        }
    }


}

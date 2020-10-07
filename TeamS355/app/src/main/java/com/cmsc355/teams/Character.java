package com.cmsc355.teams;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Character {

    private Bitmap image;
    private int x,y;

    public Character (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(){
        y++;
    }

}

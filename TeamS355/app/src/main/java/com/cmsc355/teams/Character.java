package com.cmsc355.teams;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Character {

    private Bitmap image;

    public Character (Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, 100, 100, null);
    }

}

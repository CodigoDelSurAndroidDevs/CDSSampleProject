package com.codigodelsur.androidsampleproject.util;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by marcosambrosi on 11/17/15.
 */
public class ColorUtil {
    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(50, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}

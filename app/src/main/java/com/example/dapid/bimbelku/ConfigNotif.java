package com.example.dapid.bimbelku;

import android.graphics.Bitmap;

/**
 * Created by dapid on 29/04/16.
 */
public class ConfigNotif {
    public static String[] nama;
    public static Bitmap[] image;

    public ConfigNotif(int i){
        nama = new String[i];
        image = new Bitmap[i];
    }
}

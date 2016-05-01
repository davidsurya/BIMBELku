package com.example.dapid.bimbelku;

import android.graphics.Bitmap;

/**
 * Created by dapid on 29/04/16.
 */
public class Config {
    public static String[] nama;
    public static String[] email;
    public static String[] alamat;
    public static String[] keahlian;
    public static String[] tingkat;
    public static String[] rating;
    public static Bitmap[] bitmaps;

    public Config (int i){
        nama = new String[i];
        email = new String[i];
        alamat = new String[i];
        keahlian = new String[i];
        tingkat = new String[i];
        rating = new String[i];
        bitmaps = new Bitmap[i];
    }
}

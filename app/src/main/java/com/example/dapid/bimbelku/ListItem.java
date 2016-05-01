package com.example.dapid.bimbelku;

/**
 * Created by dapid on 29/04/16.
 */

import android.graphics.Bitmap;

public class ListItem {
    private String name;
    private String les;
    private Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLes() {
        return les;
    }

    public void setLes(String les) {
        this.les = les;
    }
}

package com.example.dapid.bimbelku;

/**
 * Created by dapid on 29/04/16.
 */

import android.graphics.Bitmap;

public class ListItem {
    private String nama;
    private String keahlian;
    private String rating;
    private String email;
    private String alamat;
    private String tingkat;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public String getRating(){
        return rating;
    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getTingkat(){
        return tingkat;
    }

    public void setTingkat(String tingkat){
        this.tingkat = tingkat;
    }
}

package com.example.dapid.bimbelku;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class SeeProfileFragment extends Fragment {
    private String nama;
    private String email;
    private String alamat;
    private String keahlian;
    private String tingkat;
    private String rating;
    private Bitmap images;

    public SeeProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_see_profile, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Profil Tentor");
        this.images = getArguments().getParcelable("foto");
        this.nama = getArguments().getString("nama");
        this.email = getArguments().getString("email");
        this.alamat = getArguments().getString("alamat");
        this.keahlian = getArguments().getString("keahlian");
        this.tingkat = getArguments().getString("tingkat");
        this.rating = getArguments().getString("rating");

        ImageView imageView = (ImageView) v.findViewById(R.id.img);
        TextView txtNama = (TextView) v.findViewById(R.id.txtNama);
        TextView txtEmail = (TextView) v.findViewById(R.id.txtEmail);
        TextView txtAlamat = (TextView) v.findViewById(R.id.txtAlamat);
        TextView txtKeahlian = (TextView) v.findViewById(R.id.txtKeahlian);
        TextView txtTingkat = (TextView) v.findViewById(R.id.txtTingkat);
        TextView txtRating = (TextView) v.findViewById(R.id.rating);

        imageView.setImageBitmap(images);
        txtNama.setText(nama);
        txtEmail.setText(email);
        txtAlamat.setText(alamat);
        txtKeahlian.setText(keahlian);
        txtTingkat.setText(tingkat);
        txtRating.setText(rating);

        String tipe = getArguments().getString("tipe");
        if(tipe.equalsIgnoreCase("1")){
            FloatingActionButton floatingActionButton;
            floatingActionButton = (FloatingActionButton) v.findViewById(R.id.request);
            floatingActionButton.setVisibility(View.INVISIBLE);
        }

        return v;
    }
}
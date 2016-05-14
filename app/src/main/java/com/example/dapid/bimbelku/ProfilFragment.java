package com.example.dapid.bimbelku;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
    private String nama;
    private String email;
    private Bitmap foto;

    public ProfilFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Profil");
        this.nama = getArguments().getString("nama");
        this.email = getArguments().getString("email");
        this.foto = getArguments().getParcelable("foto");

        ImageView imageView = (ImageView) v.findViewById(R.id.foto);
        EditText editTextNama = (EditText) v.findViewById(R.id.nama);
        EditText editTextEmail = (EditText) v.findViewById(R.id.email);

        imageView.setImageBitmap(foto);
        editTextNama.setText(nama);
        editTextEmail.setText(email);

        // Inflate the layout for this fragment
        return v;
    }

}

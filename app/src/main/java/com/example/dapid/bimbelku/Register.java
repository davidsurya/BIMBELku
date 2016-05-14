package com.example.dapid.bimbelku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private static String TAG_URL = "http://bimbelku.esy.es/api/send_daftar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar BIMBELku");

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

        Button btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pg = new ProgressDialog(v.getContext());
                pg.setMessage("Memproses..");
                pg.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pg.dismiss();
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Anda telah terdaftar. Silahkan login terlebih dahulu", Snackbar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    }
                                });

                        snackbar.show();
                    }}, 2500);
            }
        });
    }

    private class postRegister extends AsyncTask<Void, Void, Void> {
        String response;

        @Override
        protected Void doInBackground(Void... params) {
            HashMap<String, String> param;
            param = new HashMap<>();
            param.put("nik", "4");
            param.put("email", "004@bimbelku.com");
            param.put("nama", "Ofani");
            param.put("password", "004");
            param.put("alamat", "Magelang, Jawa Tengah");
            param.put("tingkat", "SMA");
            param.put("keahlian", "Jaringan");
            param.put("tipe", "1");
            param.put("rating", "4");

            WebRequest webRequest = new WebRequest();
            response = webRequest.makeWebServiceCall(TAG_URL, WebRequest.POSTRequest, param);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast toast = Toast.makeText(getApplicationContext(), this.response, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
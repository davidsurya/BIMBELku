package com.example.dapid.bimbelku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class Login extends AppCompatActivity {
    private TextView txtDaftar;
    private Button btnMasuk;
    private EditText txtUsername;
    private EditText txtPassword;

    private String username;
    private String nama;
    private String tipe;
    private Bitmap foto;
    private String password;

    private String temp_username;
    private String temp_password;

    //url
    private static String TAG_URL ="http://www.bimbelku.esy.es/api/get_userbyemail.php?email=";
    private final static String TAG_URL_IMG ="http://www.bimbelku.esy.es/foto/";

    private final static String TAG_BIMBELKU = "bimbelkudata";
    private final static String TAG_EMAIL = "email";
    private final static String TAG_NAMA= "nama";
    private final static String TAG_TIPE = "tipe";
    private final static String TAG_FOTO = "foto";
    private final static String TAG_PASSWORD = "password";

    private JSONArray bimbelku;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.txtDaftar = (TextView) findViewById(R.id.txtDaftar);
        this.btnMasuk = (Button) findViewById(R.id.btnMasuk);

        this.txtUsername = (EditText) findViewById(R.id.username);
        this.txtPassword = (EditText) findViewById(R.id.password);


        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getLogin().execute();
            }
        });
    }

    private class getLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Login.this);
            pd.setMessage("Autentikasi");
            pd.show();

            temp_username = txtUsername.getText().toString();
            temp_password = txtPassword.getText().toString();
            username = "";
            password = "";
            TAG_URL = TAG_URL + "\'" + temp_username + "\'";
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(TAG_URL, WebRequest.GETRequest);
            if(jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    bimbelku = jsonObj.getJSONArray(TAG_BIMBELKU);

                    if (bimbelku.length() > 0){
                        JSONObject c = bimbelku.getJSONObject(0);
                        username = c.getString(TAG_EMAIL);
                        nama = c.getString(TAG_NAMA);
                        tipe = c.getString(TAG_TIPE);
                        password = c.getString(TAG_PASSWORD);
                        String url_img = c.getString(TAG_FOTO);
                        foto = getImage(TAG_URL_IMG+url_img+".jpg");
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TAG_URL = "http://www.bimbelku.esy.es/api/get_userbyemail.php?email=";
            pd.dismiss();

            if (temp_username.equals(username) && temp_password.equals(password)){
                Intent intent = new Intent(Login.this, Tentor.class);
                intent.putExtra("email", username);
                intent.putExtra("nama", nama);
                intent.putExtra("tipe", tipe);
                intent.putExtra("foto", foto);
                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "User dan password salah !", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        private Bitmap getImage(String bitmapUrl){
            URL url;
            Bitmap image = null;
            try {
                url = new URL(bitmapUrl);
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }catch(Exception e){}
            return image;
        }
    }
}

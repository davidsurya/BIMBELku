package com.example.dapid.bimbelku;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Config config;

    //url
    private final static String TAG_URL ="http://www.bimbelku.esy.es/api/get_tentorbyrating.php";
    private final static String TAG_URL_IMG ="http://www.bimbelku.esy.es/foto/";

    //json name
    private final static String TAG_BIMBELKU = "bimbelkudata";
    private final static String TAG_EMAIL = "email";
    private final static String TAG_NAMA = "nama";
    private final static String TAG_ALAMAT = "alamat";
    private final static String TAG_TINGKAT = "tingkat";
    private final static String TAG_KEAHLIAN = "keahlian";
    private final static String TAG_RATING = "rating";
    private final static String TAG_FOTO = "foto";

    JSONArray bimbelkudata = null;

    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recommend, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Rekomendasi");

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(layoutManager);

        // Calling async task to get json
        new GetTentor().execute();

        return v;
    }

    private class GetTentor extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getContext());
            pd.setMessage("Sedang mengunduh data tentor");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(TAG_URL, WebRequest.GETRequest);

            if(jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    bimbelkudata = jsonObj.getJSONArray(TAG_BIMBELKU);
                    config = new Config(bimbelkudata.length());

                    for(int i = 0; i < bimbelkudata.length(); i++){
                        JSONObject c = bimbelkudata.getJSONObject(i);
                        String nama = c.getString(TAG_NAMA);
                        String email = c.getString(TAG_EMAIL);
                        String alamat = c.getString(TAG_ALAMAT);
                        String keahlian = c.getString(TAG_KEAHLIAN);
                        String tingkat = c.getString(TAG_TINGKAT);
                        String rating = c.getString(TAG_RATING);
                        String url_image = c.getString(TAG_FOTO);

                        Config.nama[i] = nama;
                        Config.email[i] = email;
                        Config.alamat[i] = alamat;
                        Config.keahlian[i] = keahlian;
                        Config.tingkat[i] = tingkat;
                        Config.rating[i] = rating;
                        Config.bitmaps[i] = getImage(TAG_URL_IMG+url_image+".jpg");
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
            pd.dismiss();
            adapter = new CardAdapter(Config.bitmaps, Config.nama, Config.email, Config.alamat, Config.keahlian, Config.tingkat, Config.rating);
            recyclerView.setAdapter(adapter);
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
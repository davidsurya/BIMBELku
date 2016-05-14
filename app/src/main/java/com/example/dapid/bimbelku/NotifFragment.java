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


public class NotifFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ConfigNotif config;

    private final static String TAG_URL ="http://www.bimbelku.esy.es/api/get_pemberitahuan.php?receiver=";
    private final static String TAG_URL_IMG ="http://www.bimbelku.esy.es/foto/";

    private final static String TAG_BIMBELKU = "bimbelkudata";
    private final static String TAG_NIK = "nik";
    private final static String TAG_NAMA = "nama";

    private String id;

    private JSONArray bimbelku;
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_notif, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Pemberitahuan");
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        id = getArguments().getString("id");

        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(layoutManager);


        // Calling async task to get json
        new GetNotif().execute();

        return  v;
    }

    private class GetNotif extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getContext());
            pd.setMessage("Mengunduh pemberitahuan");
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(TAG_URL+id+"&tipe=0", WebRequest.GETRequest);
            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    bimbelku = jsonObj.getJSONArray(TAG_BIMBELKU);

                    config = new ConfigNotif(bimbelku.length());

                    for (int i = 0; i < bimbelku.length(); i++){
                        JSONObject c = bimbelku.getJSONObject(i);
                        String url_img = c.getString(TAG_NIK);
                        ConfigNotif.nama[i] = c.getString(TAG_NAMA);
                        ConfigNotif.image[i] = getImage(TAG_URL_IMG+url_img+".jpg");

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
            adapter = new NotifAdapter(ConfigNotif.image, ConfigNotif.nama);
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
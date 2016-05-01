package com.example.dapid.bimbelku;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NotifFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_notif, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Pemberitahuan");
        return  v;
    }
}
package com.example.dapid.bimbelku;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Config config;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notif, container, false);
        recyclerView = (RecyclerView) container.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(layoutManager);
        String[] name = {"unity", "educomp", "2016", "Merdeka"};
        String[] les  = {"bahasa", "ekonomi", "akuntansi", "PKn"};
        config = new Config(4);

        for (int i = 0; i < 4; i++){
            Config.name[i] = name[i];
            Config.les[i] = les[i];
        }

        adapter = new CardAdapter(Config.name, Config.les);

        recyclerView.setAdapter(adapter);
        return v;
    }

}

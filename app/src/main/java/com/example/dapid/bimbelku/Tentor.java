package com.example.dapid.bimbelku;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Tentor extends AppCompatActivity {

    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToogle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentor);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BIMBELku");

        RecommendFragment rekomendasi = new RecommendFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, rekomendasi);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                drawer.closeDrawers();
                switch (item.getItemId()){
                    case R.id.rekomendasi:
                        RecommendFragment rekomendasi = new RecommendFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, rekomendasi);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.pesan:
                        Toast toast = Toast.makeText(getApplicationContext(), "Comming Soon !!", Toast.LENGTH_LONG);
                        toast.show();
                        return true;
                    case R.id.pemberitahuan:
                        NotifFragment pemberitahuan = new NotifFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, pemberitahuan);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.tentang:
                        AboutFragment tentang  = new AboutFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, tentang);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                }
                return false;
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.addDrawerListener(mDrawerToogle);
        mDrawerToogle.syncState();

        fab = (FloatingActionButton) findViewById(R.id.editprofil);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfilFragment profil = new ProfilFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, profil);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                drawer.closeDrawers();
            }
        });
        /*
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);

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
        */
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

}

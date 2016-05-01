package com.example.dapid.bimbelku;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tentor extends AppCompatActivity {

    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToogle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private FragmentTransaction fragmentTransaction;

    private String tipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentor);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BIMBELku");

        String email = getIntent().getStringExtra("email");
        String nama = getIntent().getStringExtra("nama");
        this.tipe = getIntent().getStringExtra("tipe");
        Bitmap foto = getIntent().getParcelableExtra("foto");
        Log.d("tipe", tipe);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if(tipe.equalsIgnoreCase("0"))
            navigationView.getMenu().getItem(2).setVisible(false);
        View hview = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView)hview.findViewById(R.id.imageprofile);
        TextView txtNama = (TextView) hview.findViewById(R.id.txtName);
        TextView txtEmail = (TextView) hview.findViewById(R.id.txtEmail);

        imageView.setImageBitmap(foto);
        txtNama.setText(nama);
        txtEmail.setText(email);

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
                    case R.id.bantuan:
                        Bantuan bantuan = new Bantuan();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, bantuan);
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
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void openFragment(ListItem currentItem){
        Bundle bundle = new Bundle();
        bundle.putParcelable("foto", currentItem.getImage());
        bundle.putString("nama", currentItem.getNama());
        bundle.putString("email", currentItem.getEmail());
        bundle.putString("alamat", currentItem.getAlamat());
        bundle.putString("keahlian", currentItem.getKeahlian());
        bundle.putString("tingkat", currentItem.getTingkat());
        bundle.putString("rating", currentItem.getRating());
        bundle.putString("tipe", tipe);

        SeeProfileFragment profile = new SeeProfileFragment();
        profile.setArguments(bundle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, profile);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        drawer.closeDrawers();
    }
}
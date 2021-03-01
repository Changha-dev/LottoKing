package com.mentenseoul.todaylottoking;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mentenseoul.todaylottoking.Fragment1;
import com.mentenseoul.todaylottoking.Fragment2;
import com.mentenseoul.todaylottoking.Fragment3;

public class MainActivity extends AppCompatActivity {

    com.mentenseoul.todaylottoking.Fragment1 fragment1;
    com.mentenseoul.todaylottoking.Fragment2 fragment2;
    com.mentenseoul.todaylottoking.Fragment3 fragment3;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() { //내 아이디
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus){}});

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        fragment1 = new com.mentenseoul.todaylottoking.Fragment1();
        fragment2 = new com.mentenseoul.todaylottoking.Fragment2();
        fragment3 = new com.mentenseoul.todaylottoking.Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

                                return true;
                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                                return true;
                            case R.id.tab3:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();

                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}
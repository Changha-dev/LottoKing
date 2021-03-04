package com.mentenseoul.lottoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
//    Fragment4 fragment4;
    Roulette roulette;

    FloatingActionButton fab3;
    private AdView adView;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(this, new OnInitializationCompleteListener() { //내 아이디
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus){}});

        adView = findViewById(R.id.adView);
        fab3 = findViewById(R.id.fab3);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        container = findViewById(R.id.container);


        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        roulette = new Roulette();

//        fragment4 = new Fragment4();
        fab3.hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                                adView.setVisibility(adView.VISIBLE);
                                fab3.hide();
                                return true;
                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                                adView.setVisibility(adView.VISIBLE);
                                fab3.hide();
                                return true;
                            case R.id.tab3:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                                    adView.setVisibility(adView.GONE);
                                fab3.hide();
                                return true;
                            case R.id.roulette:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, roulette).commit();
                                adView.setVisibility(adView.VISIBLE);
                                    return true;
                        }
                        return false;
                    }
                }
        );

//        fab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
//               fab3.hide();
//            }
//        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//                // todo
//            } else {
//                Toast.makeText(this, "" + result.getContents(), Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
}
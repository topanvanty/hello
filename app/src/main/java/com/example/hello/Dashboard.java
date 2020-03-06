package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.btnNV);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framLay, new homeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.home:
                        fragment = new homeFragment();
                        break;
                    case R.id.fav:
                        fragment = new favoriteFragment();
                        break;
                    case R.id.set:
                        fragment = new settingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framLay,fragment).commit();
                return true;
            }
        });


    }
}

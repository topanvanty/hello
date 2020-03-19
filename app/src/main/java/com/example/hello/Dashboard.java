package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {


    Button btnLog;
    EditText etEmail;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.btnNV);

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferen = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat", "false");
                editor.apply();
                finish();
            }
        });



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

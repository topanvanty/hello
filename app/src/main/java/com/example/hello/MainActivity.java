package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref ;
    Database db;
    RelativeLayout rellay1, rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
        rellay1.setVisibility(View.VISIBLE);
        rellay2.setVisibility(View.VISIBLE);
        }
    };

    EditText tvEmail ,tvPass;
    Button btnLogin, btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String cek = preferences.getString("ingat","");
        if (cek.equals("true")){
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }


        db = new Database(this);

        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 500);

        tvEmail = (EditText)findViewById(R.id.tvEmail);
        tvPass = (EditText)findViewById(R.id.tvPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnDaftar = (Button)findViewById(R.id.btnDaftar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferen = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat", "true");
                editor.apply();

                String user = tvEmail.getText().toString();
                String pwd = tvPass.getText().toString();
                Boolean res = db.checkUser(user, pwd);
                if (res==true) {
                    Toast.makeText(getApplicationContext(), "SUKSES LOGIN", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_LONG).show();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Daftar.class);
                startActivity(i);
                finish();
            }
        });
    }
}

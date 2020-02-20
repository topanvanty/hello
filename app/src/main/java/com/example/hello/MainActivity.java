package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail,txtPass;
    Button btnLogin;
    TextView tvDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);
         
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email= txtEmail.getText().toString();
                pass = txtPass.getText().toString();

                if (email.equals("")){
                    Toast.makeText(MainActivity.this,"Harus Masukan Email",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(MainActivity.this,"Harus Masukan Password",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(MainActivity.this,Dashboard.class);
                    startActivity(i);
                    finish();
                }


            }
        });

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Daftar.class);
                startActivity(i);
                finish();
            }
        });
    }
}

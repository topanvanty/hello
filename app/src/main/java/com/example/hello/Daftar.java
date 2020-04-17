    package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Daftar extends AppCompatActivity {

    Database db;
    EditText txtEmail,txtPass,txtCPass;
    TextView tvBLogin;
    Button btnRegis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        db = new Database(this);
        txtEmail= (EditText)findViewById(R.id.txtPass);
        txtPass = (EditText)findViewById(R.id.txtPass);
        txtCPass = (EditText)findViewById(R.id.txtCPass);
        btnRegis = (Button)findViewById(R.id.btnRegis);
        tvBLogin = (TextView)findViewById(R.id.tvBLogin);
        tvBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Daftar.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtEmail.getText().toString().trim();
                String pwd = txtPass.getText().toString().trim();
                String cnf_pwd = txtCPass.getText().toString().trim();

                //VAlidasi
                if (txtEmail.getText().toString().isEmpty()){
                    Toast.makeText(Daftar.this,"Harus Mengisi Email", Toast.LENGTH_SHORT).show();
                    return;
                }if (txtPass.getText().toString().isEmpty()){
                    Toast.makeText(Daftar.this,"Harus Mengisi Password", Toast.LENGTH_SHORT).show();
                    return;
                }if (txtCPass.getText().toString().isEmpty()){
                    Toast.makeText(Daftar.this,"Harus Mengisi Password Lagi", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(Daftar.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Daftar.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(Daftar.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Daftar.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


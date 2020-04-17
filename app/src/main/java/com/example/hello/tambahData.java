package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class tambahData extends AppCompatActivity {

    private EditText nameET,nimET,prodiET;
    private Button addBtn,backBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);
            nameET= (EditText) findViewById(R.id.nameET);
            nimET= (EditText) findViewById(R.id.nimET);
            prodiET= (EditText) findViewById(R.id.prodiET);
            addBtn = (Button) findViewById(R.id.addBtn);
            backBtn = (Button) findViewById(R.id.backBtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mahasiswa mhs  = new mahasiswa();
                    mhs.setNama(nameET.getText().toString());
                    mhs.setNim(nimET.getText().toString());
                    mhs.setProdi(prodiET.getText().toString());
                    new Firebase().tambah(mhs, new Firebase.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<mahasiswa> mhs, List<String> keys) {

                        }

                        @Override
                        public void DataIsInsert() {
                            Toast.makeText(tambahData.this,"Data" + "berhasil",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void DataIsUpdate() {

                        }

                        @Override
                        public void DataIsDelete() {

                        }
                    });
                }
            });

                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(tambahData.this,Dashboard.class);
                        startActivity(i);
                        finish();
                    }
                });
    }
}

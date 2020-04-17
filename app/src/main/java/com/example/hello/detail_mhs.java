package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class detail_mhs extends AppCompatActivity {

    private EditText nameET,nimET,prodiET;
    private Button updateBtn,deleteBtn,backBtn,hBtn;
    private String key,nama,nim,prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mhs);

        key = getIntent().getStringExtra("key");
        nama = getIntent().getStringExtra("nama");
        nim = getIntent().getStringExtra("nim");
        prodi = getIntent().getStringExtra("prodi");

        nameET= (EditText) findViewById(R.id.nameET);
        nameET.setText(nama);
        nimET= (EditText) findViewById(R.id.nimET);
        nimET.setText(nim);
        prodiET= (EditText) findViewById(R.id.prodiET);
        prodiET.setText(prodi);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        hBtn= (Button) findViewById(R.id.hBtn);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswa mhs = new mahasiswa();
                mhs.setNama(nameET.getText().toString());
                mhs.setNim(nimET.getText().toString());
                mhs.setProdi(prodiET.getText().toString());

                new Firebase().updateData(key, mhs, new Firebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<mahasiswa> mhs, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsert() {

                    }

                    @Override
                    public void DataIsUpdate() {
                        Toast.makeText(detail_mhs.this,"Data mahasiswa" + "Berhasil di Update",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDelete() {

                    }
                });

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Firebase().deleteData(key, new Firebase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<mahasiswa> mhs, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsert() {

                    }

                    @Override
                    public void DataIsUpdate() {

                    }

                    @Override
                    public void DataIsDelete() {
                        Toast.makeText(detail_mhs.this,"Data mahasiswa" + "Berhasil di Hapus",Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
        hBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detail_mhs.this,Dashboard.class);
                startActivity(i);
                finish();
            }
        });
    }
}

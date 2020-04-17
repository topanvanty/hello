package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class dataRecylerview extends AppCompatActivity {

    private RecyclerView    mRecyclerView;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_recylerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.mhsRV);
        new Firebase().readMhs(new Firebase.DataStatus() {
            @Override
            public void DataIsLoaded(List<mahasiswa> mhs, List<String> keys) {
                new RecylerViewConfig().setConfig(mRecyclerView,dataRecylerview.this,mhs,keys);
            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDelete() {

            }
        });
    }

}

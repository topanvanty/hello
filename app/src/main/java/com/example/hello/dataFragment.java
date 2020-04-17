package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class dataFragment extends Fragment {
    private Button btnPlus,btnLihat;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment,container,false);

        btnPlus = view.findViewById(R.id.btnPlus);
        btnLihat = view.findViewById(R.id.btnLihat);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), tambahData.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dataRecylerview.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }
}

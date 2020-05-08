package com.example.hello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class homeFragment extends Fragment {

    private Button btnCap,btnMap;
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment,container,false);
        btnCap = view.findViewById(R.id.btnCap);
        btnMap = view.findViewById(R.id.btnMap);

        btnCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Camera.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), map.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }

}

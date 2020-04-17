package com.example.hello;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecylerViewConfig {
    private Context mContext;
    private mhsAdapter mMhsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<mahasiswa>mMhsList,List<String> keys ){
        mContext = context;
        mMhsAdapter = new mhsAdapter(mMhsList,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMhsAdapter);
    }

    class MhsItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mNim;
        private TextView mProdi;

        private String key;

        public MhsItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.data_fragment,parent, false));
            mName  =(TextView) itemView.findViewById(R.id.name_textview);
            mNim  =(TextView) itemView.findViewById(R.id.nim_textview);
            mProdi  =(TextView) itemView.findViewById(R.id.prodi_textview);

        }
            public void bind(mahasiswa mhs, String key){
            mName.setText(mhs.getNama());
            mNim.setText(mhs.getNim());
            mProdi.setText(mhs.getProdi());
            this.key = key;
            }
    }
    class mhsAdapter extends RecyclerView.Adapter<MhsItemView>{
        private List<mahasiswa>mMhsList;
        private List<String> mKeys;

        public mhsAdapter(List<mahasiswa> mMhsList, List<String> mKeys) {
            this.mMhsList = mMhsList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public MhsItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MhsItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MhsItemView holder, int position) {
            holder.bind(mMhsList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mMhsList.size();
        }
    }
}

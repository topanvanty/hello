package com.example.hello;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Firebase {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceMhs;
    private List<mahasiswa> mhs = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<mahasiswa> mhs,List<String>keys);
        void DataIsInsert();
        void DataIsUpdate();
        void DataIsDelete();
    }

    public Firebase() {
       mDatabase = FirebaseDatabase.getInstance();
       mReferenceMhs = mDatabase.getReference("mahasiswa");
    }
     public void readMhs(final DataStatus dataStatus){
        mReferenceMhs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mhs.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                   keys.add(keyNode.getKey());
                   mahasiswa Mahasiswa  = keyNode.getValue(mahasiswa.class);
                   mhs.add(Mahasiswa);
                }
                dataStatus.DataIsLoaded(mhs,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
     }
     public void tambah(mahasiswa mhs, final DataStatus dataStatus){
        String key  = mReferenceMhs.push().getKey();
        mReferenceMhs.child(key).setValue(mhs).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInsert();
            }
        });
     }
    public void updateData(String key, mahasiswa mhs , final DataStatus dataStatus){
        mReferenceMhs.child(key).setValue(mhs).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdate();
            }
        });
    }
    public void deleteData(String key,final DataStatus dataStatus){
        mReferenceMhs.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDelete();
            }
        });
    }
}

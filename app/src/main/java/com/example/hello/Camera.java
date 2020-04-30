package com.example.hello;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Camera extends AppCompatActivity {

    ImageView imgView;
    Button btnCam,btnSave;
    OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ActivityCompat.requestPermissions(Camera.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        imgView = findViewById(R.id.image_view);
        btnCam = findViewById(R.id.btnCamera);
        btnSave = findViewById(R.id.btnSave);

        //reqcam
        if (ContextCompat.checkSelfPermission(Camera.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Camera.this,new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
                Bitmap  bitmap = drawable.getBitmap();

                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath()+"/test");
                dir.mkdir();
                File file =new File (dir, System.currentTimeMillis()+".jpg");
                try {
                    outputStream = new FileOutputStream(file);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                Toast.makeText(getApplicationContext(), "Image Save",Toast.LENGTH_SHORT).show();
                try {
                   outputStream.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 ){
            //dapat gambar
            Bitmap img =(Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(img);
        }
    }
}

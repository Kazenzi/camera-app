package com.example.snapkaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarMain;
    //    ImageView imageView;
    TextView textView;
    // ExtendedFloatingActionButton extendedFloatingActionButton;
    Button btnCamera;
    public static int index=0;
    public final String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +"/facebook/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.ViewPager);
        btnCamera=findViewById(R.id.btnCamera);

        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCamera();

            }
        });
    }

    private void OpenCamera() {
        index++;
        String file=directory+index +".jpg";
        File newFile=new File(file);

        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri outPutUri= Uri.fromFile(newFile);

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,outPutUri);
        startActivity(cameraIntent);
    }
}
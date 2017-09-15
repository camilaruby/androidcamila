package com.example.logonrm.androidcamila;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaixarImagem extends AppCompatActivity implements View.OnClickListener{

    private Button btnBaixarImagem;
    private ImageView imgCarro;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baixar_imagem);

        btnBaixarImagem = (Button) findViewById(R.id.btnBaixarImagem);
        imgCarro  = (ImageView) findViewById(R.id.imgCarro);

        btnBaixarImagem.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "We need permission!", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
            //Cria dentro de pictures pasta androidcamila

        Bitmap bitmap = ((BitmapDrawable)imgCarro.getDrawable()).getBitmap();

        FileOutputStream out = null;
        try {
            File folder = new File(Environment.getExternalStorageDirectory() + "/Pictures/androidcamila");
            if(!folder.exists())
                folder.mkdir();
            String file = Environment.getExternalStorageDirectory() + "/Pictures/androidcamila/test.jpg";
            out = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out))
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

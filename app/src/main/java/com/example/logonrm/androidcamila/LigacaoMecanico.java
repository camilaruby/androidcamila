package com.example.logonrm.androidcamila;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LigacaoMecanico extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligacao_mecanico);

        final Button btnLigar = (Button) findViewById(R.id.btnLigar);


        btnLigar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        String telefone = edtTelefone.getText().toString();

        Uri uri = Uri.parse("tel:" + telefone);

        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(LigacaoMecanico.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LigacaoMecanico.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(intent);

    }


}

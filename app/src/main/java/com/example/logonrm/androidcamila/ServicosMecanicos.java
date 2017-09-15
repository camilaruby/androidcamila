package com.example.logonrm.androidcamila;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.logonrm.androidcamila.Dao.VeiculoDao;
import com.example.logonrm.androidcamila.model.Veiculo;

import java.util.List;

public class ServicosMecanicos extends AppCompatActivity implements View.OnClickListener{

    private VeiculoDao vecDB;
    private Spinner spPlaca;
    private Button btnEnviarServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_mecanicos);
    }

    @Override
    public void onClick(View view) {
        vecDB = new VeiculoDao(this);

        List<Veiculo> veiculosAll = vecDB.getAll();
    }
}

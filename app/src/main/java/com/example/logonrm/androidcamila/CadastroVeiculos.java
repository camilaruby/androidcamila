package com.example.logonrm.androidcamila;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.logonrm.androidcamila.Adapter.VeiculoAdapter;
import com.example.logonrm.androidcamila.DBOpenHelper.DBOpenHelper;
import com.example.logonrm.androidcamila.Dao.UsuarioDao;
import com.example.logonrm.androidcamila.Dao.VeiculoDao;
import com.example.logonrm.androidcamila.model.Usuario;
import com.example.logonrm.androidcamila.model.Veiculo;

import java.util.List;

public class CadastroVeiculos extends AppCompatActivity {

    private static final String TAG = CadastroVeiculos.class.getSimpleName();


    private VeiculoDao vecDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculos);
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.frame_veiculos_layout);

        RecyclerView veiculoView = (RecyclerView)findViewById(R.id.veiculos_lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        veiculoView.setLayoutManager(linearLayoutManager);
        veiculoView.setHasFixedSize(true);

        vecDB = new VeiculoDao(this);

        List<Veiculo> veiculosAll = vecDB.getAll();

        if(veiculosAll.size() > 0){
            veiculoView.setVisibility(View.VISIBLE);
            VeiculoAdapter  vecAdapter = new VeiculoAdapter(this, veiculosAll);
            veiculoView.setAdapter(vecAdapter);

        }else {
            veiculoView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no product in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.adicionarveiculos, null);

        final EditText titular  = (EditText)subView.findViewById(R.id.enter_Titular);
        final EditText cpf      = (EditText)subView.findViewById(R.id.enter_Cpf);
        final EditText placa    = (EditText)subView.findViewById(R.id.enter_Placa);
        final EditText cor      = (EditText)subView.findViewById(R.id.enter_cor);
        final EditText modelo   = (EditText)subView.findViewById(R.id.enter_modelo);




        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.mensagem_editar_veiculo);
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton(R.string.mensagem_editar_veiculo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String value1    = titular.getText().toString();
                final String value2    = cpf.getText().toString();
                final String value3    = placa.getText().toString();
                final String value4    = cor.getText().toString();
                final String value5    = modelo.getText().toString();

                if(TextUtils.isEmpty(value1) || TextUtils.isEmpty(value2) || TextUtils.isEmpty(value3) || TextUtils.isEmpty(value4) || TextUtils.isEmpty(value5)){
                    Toast.makeText(CadastroVeiculos.this, R.string.mensagem_erro, Toast.LENGTH_LONG).show();
                }
                else{

                    UsuarioDao usuDB = new UsuarioDao(getBaseContext());


                    Veiculo newVec = new Veiculo(value1, value2, value3, value4, value5);
                    vecDB.addVeiculo(newVec);

                    //refresh the activity
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton(R.string.mensagem_cancelado, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CadastroVeiculos.this, R.string.mensagem_acao_cancelada, Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(vecDB != null){
            vecDB.close();
        }
    }
}

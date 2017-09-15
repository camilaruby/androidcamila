package com.example.logonrm.androidcamila;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logonrm.androidcamila.DBOpenHelper.DBOpenHelper;
import com.example.logonrm.androidcamila.Dao.UsuarioDao;
import com.example.logonrm.androidcamila.model.Usuario;

public class CadastroUsuario extends AppCompatActivity{

    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtConfirmaSenha;
    private Button   btnSalvarUsuario;

    Usuario usuario;
    SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        edtCadEmail      = (EditText) findViewById(R.id.edtCadEmail);
        edtCadSenha      = (EditText) findViewById(R.id.edtCadSenha);
        edtConfirmaSenha = (EditText) findViewById(R.id.edtCadConfirmaSenha);

    }

    public void salvarUsuario(View v){
        UsuarioDao db = new UsuarioDao(getBaseContext());

        String email = edtCadEmail.getText().toString();
        String senha = edtCadSenha.getText().toString();
        String confirmasenha = edtConfirmaSenha.getText().toString();
        String resultado;

        if(email.isEmpty() || senha.isEmpty() || confirmasenha.isEmpty()){
            Toast.makeText(this, R.string.mensagem_erro, Toast.LENGTH_SHORT).show();
            return;
        }

        resultado = db.addUsuario(email,senha);

        Intent intent = new Intent(CadastroUsuario.this, Login.class);
        startActivity(intent);

        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
    }


}

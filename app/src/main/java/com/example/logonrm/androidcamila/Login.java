package com.example.logonrm.androidcamila;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logonrm.androidcamila.Dao.UsuarioDao;
import com.example.logonrm.androidcamila.model.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends Activity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private CheckBox ckManterConectado;
    private TextView txtNovoUsuario;
    private static UsuarioDao usuarioDAO;
    private TextView btnLoginWebService;
    private TextView txtFirebase;
    private static final String  TAG = "Login";
    private static final String LOGIN_KEY = "login";


    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        ckManterConectado  = (CheckBox) findViewById(R.id.ckManterConectado);
        txtNovoUsuario     = (TextView) findViewById(R.id.txtNovoUsuario);
        btnLoginWebService = (TextView) findViewById(R.id.txtLoginWebService);
        txtFirebase = (TextView) findViewById(R.id.txtFirebase);



        btnLogar.setOnClickListener(this);
        txtNovoUsuario.setOnClickListener(this);

        ler();


        if (AccessToken.getCurrentAccessToken() != null) {
            goLoginPrincipal();
        }


        //FACEBOOK LOGIN BUTTON
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goLoginPrincipal();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Err sing up!", Toast.LENGTH_SHORT);

                //Ou Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT );
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtNovoUsuario:
                Intent intent = new Intent(Login.this, CadastroUsuario.class);
                startActivity(intent);
                break;
            case R.id.btnLogar:
                SharedPreferences sp = getSharedPreferences(LOGIN_KEY, MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();

                UsuarioDao db = new UsuarioDao(getBaseContext());
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();


                if (db.Login(email, senha)) {

                    if (ckManterConectado.isChecked()) {

                        e.putString("usuario", edtEmail.getText().toString());
                        e.putString("senha", edtSenha.getText().toString());
                        e.putBoolean("continuarConectado", ckManterConectado.isChecked());
                        e.apply();
                    } else {
                        e.putString("usuario", "");
                        e.putString("senha", "");
                        e.putBoolean("continuarConectado", false);

                    }

                    Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login.this, Principal.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "login invalido", Toast.LENGTH_LONG).show();
                }


                break;

            case R.id.txtLoginWebService:
                UsuarioTask task = new UsuarioTask();

                // task.executeOnExecutor(AsyncTask.THREAD_POOL_Executor,params);
                task.executeOnExecutor(UsuarioTask.SERIAL_EXECUTOR);
                break;
            case R.id.txtFirebase:
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Token: " + token);
                Toast.makeText(Login.this, token, Toast.LENGTH_SHORT).show();
                break;


        }

    }

    //FACEBOOOK
    private void goLoginPrincipal() {
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    //WEB SERVICE
    private class UsuarioTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();

                if (connection.getResponseCode() == 200) {
                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String linha = "";
                    StringBuilder resposta = new StringBuilder();

                    while ((linha = stream.readLine()) != null) {
                        resposta.append(linha);
                    }
                    connection.disconnect();
                    return resposta.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String user) {

            super.onPostExecute(user);
            Usuario usu = new Usuario();
            String username = edtEmail.getText().toString();
            String password = edtSenha.getText().toString();

            if (user != null) {


                try {
                    JSONObject json = new JSONObject(user);

                    String usuario = json.getString("usuario");
                    String senha = json.getString("senha");


                    usu.setEmail(usuario);
                    usu.setSenha(senha);


                    if (username.equals(usu.getEmail().toString()) && password.equals(usu.getSenha().toString())) {
                        Intent i = new Intent(Login.this, Principal.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(Login.this, "Erro ao abrir principal(WebService)", Toast.LENGTH_SHORT).show();
                    }

                    //  tvLogin.setText("usuario:" + usu.getUsuario() + "\nSenha :" + usu.getSenha());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(Login.this, "Erro ao realizar Login", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void ler() {
        SharedPreferences sp = getSharedPreferences(LOGIN_KEY, MODE_PRIVATE);

        boolean logar = sp.getBoolean("continuarConectado", false);
        if(logar) {
            ckManterConectado.setChecked(logar);
            edtEmail.setText(sp.getString("usuario", ""));
            edtSenha.setText(sp.getString("senha", ""));
            btnLogar.performClick();
        }
    }
}
package com.example.logonrm.androidcamila.DBOpenHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.logonrm.androidcamila.model.Usuario;

public class DBOpenHelper  extends SQLiteOpenHelper{

    public static final String DB_NAME = "bancodados";
    public static final String DB_TABLE = "usuario";
    public static final int DB_VERSAO = 1;

    public static final String COLUNA_ID    = "usu_id";
    public static final String COLUNA_EMAIL = "usu_email";
    public static final String COLUNA_SENHA = "usu_senha";

    public static final String DB_TABLE_VEC      = "veiculos";

    public static final String COLUNA_ID_VEICULO = "vec_id";
    public static final String COLUNA_TITULAR    = "vec_titular";
    public static final String COLUNA_CPF        = "vec_cpf";
    public static final String COLUNA_COR        = "vec_cor";
    public static final String COLUNA_MODELO     = "vec_modelo";
    public static final String COLUNA_PLACA      = "vec_placa";







    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        criarTabelaUsuario(db);
        criarTabelaVeiculo(db);


    }

    private void criarTabelaUsuario(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + DB_TABLE + " ( "
                + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_EMAIL + " VARCHAR(100) NOT NULL,"
                + COLUNA_SENHA + " VARCHAR(10) NOT NULL )";
        db.execSQL(QUERY);
    }

    private void criarTabelaVeiculo(SQLiteDatabase db){

        String QUERY_VEICULO = "CREATE TABLE " + DB_TABLE_VEC + " ( "
                + COLUNA_ID_VEICULO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_TITULAR    + " VARCHAR(100) NOT NULL,"
                + COLUNA_CPF        + " VARCHAR(15) NOT NULL,"
                + COLUNA_PLACA      + " VARCHAR(10) NOT NULL,"
                + COLUNA_COR        + " VARCHAR(20) NOT NULL,"
                + COLUNA_MODELO     + " VARCHAR(50) NOT NULL )";

        db.execSQL(QUERY_VEICULO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE_VEC);
        onCreate(db);
    }



}

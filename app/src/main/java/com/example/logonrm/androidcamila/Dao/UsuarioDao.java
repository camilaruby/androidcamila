package com.example.logonrm.androidcamila.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.logonrm.androidcamila.DBOpenHelper.DBOpenHelper;
import com.example.logonrm.androidcamila.model.Usuario;


public class UsuarioDao {


    DBOpenHelper openHelper;
    private static UsuarioDao usuarioDAO;
    public int uid;


    public UsuarioDao(Context context){
        openHelper = new DBOpenHelper(context);
    }
    public String addUsuario(String email, String senha) {

        long result;
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(openHelper.COLUNA_EMAIL, email);
        values.put(openHelper.COLUNA_SENHA, senha);

        result =db.insert(openHelper.DB_TABLE, null, values);
        db.close();
        if(result == -1){
            return"Erro ao inserir registro";
        }else{
            return "Registrado com sucesso";
        }

    }

    public boolean Login(String email, String senha) throws SQLException {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + openHelper.DB_TABLE + " WHERE usu_email =? AND usu_senha =?", new String[]{email,senha});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

    public Long getUserId(String username, String password) throws SQLException {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        int userId;
        Cursor mCursor = db.rawQuery("SELECT usu_id FROM usuario WHERE usu_email =? AND usu_senha =?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                userId = mCursor.getInt(0);
                uid = userId;


            }
        }
        return null;
    }
}



package com.example.logonrm.androidcamila.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.logonrm.androidcamila.DBOpenHelper.DBOpenHelper;
import com.example.logonrm.androidcamila.model.Usuario;
import com.example.logonrm.androidcamila.model.Veiculo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class VeiculoDao {

    DBOpenHelper openHelper;
    private static VeiculoDao veiculoDAO;

    public VeiculoDao(Context context){
        openHelper = new DBOpenHelper(context);
    }

    public String addVeiculo(Veiculo vec) {

        long result;
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(openHelper.COLUNA_TITULAR, vec.getTitular());
        values.put(openHelper.COLUNA_CPF, vec.getCpf());
        values.put(openHelper.COLUNA_PLACA, vec.getPlaca());
        values.put(openHelper.COLUNA_COR, vec.getCor());
        values.put(openHelper.COLUNA_MODELO, vec.getModelo());


        result =db.insert(openHelper.DB_TABLE_VEC, null, values);
        db.close();
        if(result == -1){
            return"Erro ao inserir registro";
        }else{
            return "Registrado com sucesso";
        }

    }

    public String updateVeiculo(Veiculo vec){
        long result;
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(openHelper.COLUNA_TITULAR, vec.getTitular());
        values.put(openHelper.COLUNA_CPF, vec.getCpf() );
        values.put(openHelper.COLUNA_PLACA, vec.getPlaca());
        values.put(openHelper.COLUNA_COR, vec.getCor());
        values.put(openHelper.COLUNA_MODELO, vec.getModelo());

       result = db.update(openHelper.DB_TABLE_VEC, values, openHelper.COLUNA_ID_VEICULO	+ "	= ?", new String[] { String.valueOf(vec.getId())});

        db.close();
        if(result == -1){
            return"Erro ao atualizar registro";
        }else{
            return "Registrado atualizado com sucesso";
        }
    }


    public List<Veiculo> getAll(){

        String QUERY = "SELECT * FROM " + openHelper.DB_TABLE_VEC;


        SQLiteDatabase db = openHelper.getReadableDatabase();

        List<Veiculo> vetorveiculos = new ArrayList<>();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){

            do{
                Veiculo vec = new Veiculo();

                vec.setId(Integer.parseInt(cursor.getString(0)));
                vec.setTitular(cursor.getString(1));
                vec.setCpf(cursor.getString(2));
                vec.setPlaca(cursor.getString(3));
                vec.setCor(cursor.getString(4));
                vec.setModelo(cursor.getString(5));

                vetorveiculos.add(vec);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return vetorveiculos;

    }


    public void deleteVeiculo(int id){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.delete(openHelper.DB_TABLE_VEC, openHelper.COLUNA_ID_VEICULO	+ "	= ?", new String[] { String.valueOf(id)});
    }

    public void close(){
        openHelper.close();
    }






}

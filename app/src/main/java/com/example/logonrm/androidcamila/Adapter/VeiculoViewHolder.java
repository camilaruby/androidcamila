package com.example.logonrm.androidcamila.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logonrm.androidcamila.R;

/**
 * Created by camila on 07/09/17.
 */

public class VeiculoViewHolder extends RecyclerView.ViewHolder {

    public  TextView titular;
    public  TextView cpf;
    public  TextView placa;
    public  TextView cor;
    public  TextView modelo;


    public  ImageView deletar;
    public  ImageView editar;

    public VeiculoViewHolder(View itemView) {
        super(itemView);

        titular = (TextView)itemView.findViewById(R.id.txtTitular);
        cpf     = (TextView)itemView.findViewById(R.id.txtCPF);
        placa   = (TextView)itemView.findViewById(R.id.txtPlaca);
        cor     = (TextView)itemView.findViewById(R.id.txtCor);
        modelo  = (TextView)itemView.findViewById(R.id.txtModelo);


        editar  = (ImageView)itemView.findViewById(R.id.Imgeditar);
        deletar = (ImageView)itemView.findViewById(R.id.Imgdeletar);

    }
}

package com.example.logonrm.androidcamila.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logonrm.androidcamila.Dao.VeiculoDao;
import com.example.logonrm.androidcamila.R;
import com.example.logonrm.androidcamila.model.Usuario;
import com.example.logonrm.androidcamila.model.Veiculo;

import java.util.List;

/**
 * Created by camila on 07/09/17.
 */

public class VeiculoAdapter extends RecyclerView.Adapter<VeiculoViewHolder> {

    private Context context;
    private List<Veiculo> listaVeiculos;

    private VeiculoDao vecDb;

    public VeiculoAdapter(Context context, List<Veiculo> listaVeiculos) {

        this.context = context;
        this.listaVeiculos = listaVeiculos;
        vecDb = new VeiculoDao(context);

    }


    @Override
    public VeiculoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cadastro_veiculos, parent, false);
        return new VeiculoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VeiculoViewHolder holder, int position) {
        final Veiculo veiculo = listaVeiculos.get(position);

        holder.titular.setText(veiculo.getTitular());
        holder.cpf.setText(veiculo.getCpf());
        holder.placa.setText(veiculo.getPlaca());
        holder.cor.setText(veiculo.getCor());
        holder.modelo.setText(veiculo.getModelo());



        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               editTaskDialog(veiculo);

            }
        });

        holder.deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                vecDb.deleteVeiculo(veiculo.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

    }



    @Override
    public int getItemCount() {
        return listaVeiculos.size();
    }



    private void editTaskDialog(final Veiculo veiculo){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.adicionarveiculos, null);

        final EditText titular = (EditText)subView.findViewById(R.id.enter_Titular);
        final EditText cpf = (EditText)subView.findViewById(R.id.enter_Cpf);
        final EditText placa = (EditText)subView.findViewById(R.id.enter_Placa);
        final EditText cor = (EditText)subView.findViewById(R.id.enter_cor);
        final EditText modelo = (EditText)subView.findViewById(R.id.enter_modelo);


        if(veiculo != null){

            titular.setText(veiculo.getTitular());
            cpf.setText(veiculo.getCpf());
            placa.setText(veiculo.getPlaca());
            cor.setText(veiculo.getCor());
            modelo.setText(veiculo.getModelo());

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.mensagem_adicionar_novo_veiculo);
        builder.setView(subView);
        builder.create();


        builder.setPositiveButton(R.string.mensagem_adicionar_novo_veiculo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String value1 = titular.getText().toString();
                final String value2 = cpf.getText().toString();
                final String value3 = placa.getText().toString();
                final String value4 = cor.getText().toString();
                final String value5 = modelo.getText().toString();


                if(TextUtils.isEmpty(value1) || TextUtils.isEmpty(value2) || TextUtils.isEmpty(value3) || TextUtils.isEmpty(value4) || TextUtils.isEmpty(value5) ){
                    Toast.makeText(context, R.string.mensagem_erro, Toast.LENGTH_LONG).show();
                }
                else{
                    vecDb.updateVeiculo(new Veiculo(veiculo.getId(), value1, value2, value3, value4, value5));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton(R.string.mensagem_cancelado, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, R.string.mensagem_acao_cancelada, Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}

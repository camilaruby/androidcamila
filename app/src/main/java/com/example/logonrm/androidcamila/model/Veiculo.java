package com.example.logonrm.androidcamila.model;

/**
 * Created by logonrm on 02/09/2017.
 */

public class Veiculo {


    private int id;

    private String titular;
    private String cpf;
    private String cor;
    private String modelo;
    private String placa;


    public Veiculo(){

    }

    public Veiculo(int id, String titular, String cpf, String placa, String cor,String modelo){
        this.id         = id;
        this.titular    = titular;
        this.cpf        = cpf;
        this.placa      = placa;
        this.cor        = cor;
        this.modelo     = modelo;

    }

    public Veiculo(String titular, String cpf, String placa, String cor,String modelo){

        this.titular    = titular;
        this.cpf        = cpf;
        this.placa      = placa;
        this.cor        = cor;
        this.modelo     = modelo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }







}

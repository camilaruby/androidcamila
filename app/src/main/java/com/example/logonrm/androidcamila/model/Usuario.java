package com.example.logonrm.androidcamila.model;

/**
 * Created by logonrm on 02/09/2017.
 */

public class Usuario {
    private int id;
    private String email;
    private String senha;

    public Usuario(){

    }
    public Usuario(int id, String email, String senha){
        this.id = id;
        this.email = email;
        this.senha = senha;

    }
    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }




}

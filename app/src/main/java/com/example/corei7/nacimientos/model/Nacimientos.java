package com.example.corei7.nacimientos.model;

import com.google.gson.annotations.SerializedName;



public class Nacimientos {


    private String departamento;
    private String municipio;
    @SerializedName("total_general")
    private int nacidos;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getNacidos() {
        return nacidos;
    }

    public void setNacidos(int nacidos) {
        this.nacidos = nacidos;
    }






}

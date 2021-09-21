package com.hairsoft.entity;

import java.util.List;

public class Salao {

    public int Id;
    public String Nome;
    public String Cnpj;
    public List<Colaboradores> colaboradores;
    public List<Clientes> clientes;

    public Salao(){

    }

    public Salao(int Id, String Nome, String Cnpj){
        this.Id = Id;
        this.Nome = Nome;
        this.Cnpj = Cnpj;
    }

    @Override
    public String toString() {
        return "Salao{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}

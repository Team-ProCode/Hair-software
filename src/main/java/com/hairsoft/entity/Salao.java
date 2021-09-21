package com.hairsoft.entity;

import java.util.ArrayList;
import java.util.List;

public class Salao {

    public Integer ID;
    public String Nome;
    public String Cnpj;
    public List<Colaboradores> colaboradores;
    public List<Clientes> clientes;

    public Salao(){

    }

    public Salao(int Id, String Nome, String Cnpj){
        this.ID = Id;
        this.Nome = Nome;
        this.Cnpj = Cnpj;
    }

    public static int gerarId(ArrayList<Salao> salaos){
        int Id = 0;
        if (salaos.isEmpty()) {
            return Id;
        } else {
            for(Salao salao: salaos) {
                if (salao.ID == Id) {
                    ++Id;
                }
            }
            return Id;
        }
    }

    @Override
    public String toString() {
        return "Salao{" +
                "Id=" + ID +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}

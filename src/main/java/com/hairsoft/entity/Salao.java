package com.hairsoft.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public static boolean cnpjExist(ArrayList<Salao> salaos, String CNPJ){
        if (salaos.isEmpty()) {
            return false;
        } else {
            for(Salao salao: salaos) {
                if (salao.Cnpj.equals(CNPJ)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean existSalao(ArrayList<Salao> salaos, String pesquisa){
        if (salaos.isEmpty()) {
            return false;
        } else {
            for(Salao salao: salaos) {
                if ((salao.ID + ": " + salao.Nome).equals(pesquisa)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Salao buscaSalao(ArrayList<Salao> salaos, String pesquisa){
        Salao salaoPesquisa;
        for(Salao salao: salaos) {
            if ((salao.ID + ": " + salao.Nome).equals(pesquisa)) {
                salaoPesquisa = new Salao(salao.ID, salao.Nome, salao.Cnpj);
                return  salaoPesquisa;
            }
        }
        salaoPesquisa = new Salao(0, "Null", "Null");
        return  salaoPesquisa;
    }

    @Override
    public String toString() {
        return "Salao{" +
                "Id=" + ID +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}

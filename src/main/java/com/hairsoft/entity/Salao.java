package com.hairsoft.entity;

import java.util.ArrayList;

public class Salao{

    public Integer id_salao;
    public String nome_salao;
    public String cnpj_salao;
    public Integer id_user;

    public Salao(){

    }

    public Salao(int Id, String Nome, String Cnpj, int Id_User){
        this.id_salao = Id;
        this.nome_salao = Nome;
        this.cnpj_salao = Cnpj;
        this.id_user = Id_User;
    }

    public Salao( String Nome, String Cnpj, int Id_User){
        this.nome_salao = Nome;
        this.cnpj_salao = Cnpj;
        this.id_user = Id_User;
    }

    public Integer getId_salao() {
        return id_salao;
    }

    public void setId_salao(Integer id_salao) {
        this.id_salao = id_salao;
    }

    public String getNome_salao() {
        return nome_salao;
    }

    public void setNome_salao(String nome_salao) {
        this.nome_salao = nome_salao;
    }

    public String getCnpj_Salao() {
        return cnpj_salao;
    }

    public void setCnpj_Salao(String cnpj_Salao) {
        this.cnpj_salao = cnpj_Salao;
    }

    public Integer getId_user(){
        return  id_user;
    }

    public void setId_user(int id_user){
        this.id_user = id_user;
    }

    public static boolean cnpjExist(ArrayList<Salao> salaos, String CNPJ){
        if (salaos.isEmpty()) {
            return false;
        } else {
            for(Salao salao: salaos) {
                if (salao.cnpj_salao.equals(CNPJ)) {
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
                if ((salao.id_salao + ": " + salao.nome_salao).equals(pesquisa)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Salao buscaSalao(ArrayList<Salao> salaos, String pesquisa){
        Salao salaoPesquisa;
        for(Salao salao: salaos) {
            if ((salao.id_salao + ": " + salao.nome_salao).equals(pesquisa)) {
                salaoPesquisa = new Salao(salao.id_salao, salao.nome_salao, salao.cnpj_salao, salao.id_user);
                return  salaoPesquisa;
            }
        }
        salaoPesquisa = new Salao();
        return  salaoPesquisa;
    }

    @Override
    public String toString() {
        return "Salao{" +
                "Id=" + id_salao +
                ", Nome='" + nome_salao + '\'' +
                '}';
    }
}

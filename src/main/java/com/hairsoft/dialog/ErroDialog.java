package com.hairsoft.dialog;

public class ErroDialog {

    public String Title;
    public String Messege;

    public String getTitleErroIsEmpty() {
        return "Registro incompleto";
    }

    public String getMenssageErroIsEmpty() {
        return "Verifique se os campos de inserção estão corretos";
    }

    public String getTitleErroSys() {
        return "Erro no sistema";
    }

    public String getMenssageErroSys() {
        return "Não foi possivel completar a ação!";
    }

    public String getTitleErroCNPJ(){
        return "Erro na validação!";
    }

    public String getMessegeErroCNPJ(){
        return "Insira um CNPJ váldo!";
    }

}

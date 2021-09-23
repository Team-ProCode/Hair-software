package com.hairsoft.dialog;

public class ErroDialog {

    public String Title;
    public String Messege;

    //Mainscreen Errors
    public String getTitleErroIsEmpty() {
        return "Registro incompleto";
    }

    public String getMessageErroIsEmpty() {
        return "Verifique se os campos de inserção estão corretos";
    }

    public String getTitleErroSys() {
        return "Erro no sistema";
    }

    public String getMensageErroSys() {
        return "Não foi possivel completar a ação!";
    }

    public String getTitleErroCNPJ(){
        return "Erro de validação!";
    }

    public String getMessageErroCNPJ(){
        return "Insira um CNPJ valido!";
    }

}

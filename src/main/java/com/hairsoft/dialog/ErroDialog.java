package com.hairsoft.dialog;

public class ErroDialog {

    public String Title;
    public String Messege;

    public String getTitiloErroIsEmpty() {
        return "Registro incompleto";
    }

    public String getMensagemErroIsEmpty() {
        return "Verifique se os campos de inserção estão corretos";
    }

    public String getTitleErroSys() {
        return "Erro no sistema";
    }

    public String getMensagemErroSys() {
        return "Não foi possivel completar a ação!";
    }

}

package com.hairsoft.dialog;

import javafx.scene.control.Alert;


public class ErroDialog {

public class ErroDialog {

    public static void alertDialog(String Title, String Messege){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Title);
        alert.setHeaderText(Messege);
        alert.showAndWait();
    }

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

//    LoginController Errors
    public String getTitleErroLogin() {
    return "Erro de validação!";
}

    public String getMessegeErroLogin(){
        return "Usuario/E-mail ou Senha não encontrados!";
    }

    public String getTitleErroCallScreen(){
        return "Erro na execução da instancia!";
    }

    public String getMessageErroCallScreen(){
        return "Contate o suporte tecnico!";
    }

    public String getTitleRegisterWrong(){
        return "Registro incorreto!";
    }

    public String getMessageRegisterWrong(){
        return "Por favor insira um usuario valido!";
    }
}

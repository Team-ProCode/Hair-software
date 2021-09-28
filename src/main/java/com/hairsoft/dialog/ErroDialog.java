package com.hairsoft.dialog;

import javafx.scene.control.Alert;

public class ErroDialog {

    public String Title;
    public String Messege;

    public static void alertDialog(String Title, String Messege){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Title);
        alert.setHeaderText(Messege);
        alert.showAndWait();
    }

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

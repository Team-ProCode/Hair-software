package com.hairsoft.dialog;

import javafx.scene.control.Alert;

public class Dialog {

    public static void alertDialog(String Title, String Message){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(Message);
        alert.showAndWait();
    }

//    MongoDB Messages
    public String getTitleDB(){
     return "MongoDB message";
    }
    public String getMessageDB(){
        return "Successfully connected!";
    }
}

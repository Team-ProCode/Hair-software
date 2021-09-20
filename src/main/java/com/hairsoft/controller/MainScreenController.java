package com.hairsoft.controller;

import com.hairsoft.entity.Usuario;
import com.hairsoft.hairsoft.MainScreenApp;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    ArrayList<Usuario> usuarios = new ArrayList<>();
    String Nome, Email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarios = MainScreenApp.usuarios;
        Nome = MainScreenApp.Nome;
        Email = MainScreenApp.Email;
        lblWellcomeName.setText("Bem vindo(a) " + Nome);
    }

    @FXML
    private Menu mbAntendimento;

    @FXML
    private Menu mbConfig;

    @FXML
    private Menu mbHelp;

    @FXML
    private Avatar avatar;

    @FXML
    private Label lblWellcomeName;

}

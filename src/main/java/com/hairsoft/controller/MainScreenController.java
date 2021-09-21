package com.hairsoft.controller;

import com.hairsoft.entity.Usuario;
import com.hairsoft.hairsoft.MainScreenApp;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Tab tabHome, tabFeatures;
    @FXML private Avatar avatar;
    @FXML private Label lblWellcomeName;
    @FXML private ComboBox<String> cmbSalaoHome;
    @FXML private Button btnAdicionarSalao, btnEditarSalao;
    @FXML private Pane paneSalão;
    @FXML private TextField txfIdSalao, txfNomeSalao, txfCnpjSalao;
    @FXML private Button btnDeletarSalao, btnSalvarSalao;
    @FXML private Label lblUsuario;
    @FXML private Avatar avatar1;
    @FXML private ComboBox<String> cmbSalaoBar;

    @FXML void btnAdicionarSalao_click(ActionEvent event) {
        paneContainerSalao.setVisible(true);
    }

    @FXML
    void btnDeletarSalao_click(ActionEvent event) {

    }

    @FXML
    void btnEditarSalao_click(ActionEvent event) {

    }

    @FXML
    void btnSalvarSalao_click(ActionEvent event) {

    }

}

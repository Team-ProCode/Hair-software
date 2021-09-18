package com.hairsoft.hairsoft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.hairsoft.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

	public ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
    @FXML
    public AnchorPane paneLog, paneReg;

    @FXML
    public TextField txfRegUsuario, txfRegEmail, txfEmail;

    @FXML
    public PasswordField txfSenha, txfRegSenha;

    @FXML
    public Button btnCancel, btnConfirm, btnRegister, btnLogin;
    
    @FXML
    public void btnCancel_click(ActionEvent event) {
    	register_off();
    }

    @FXML
    public void btnConfirm_click(ActionEvent event) {
        inserir();
    }

    public void inserir(){
        Alert alert;
        try{
            String nome, email, senha;
            nome = txfRegUsuario.getText();
            email = txfRegEmail.getText();
            senha = txfRegSenha.getText();

            if(Usuario.equalEmail(usuarios , email)){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ops");
                alert.setHeaderText("Email ja cadastrado em usuarios!!");
                alert.showAndWait();
            }else{
                usuarios.add(new Usuario(Usuario.gerarId(usuarios),nome, email, senha ));
                register_off();
            }
        }catch(Exception e){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operação não pode ser realizada.");
            alert.setContentText("Verifique se digitou corretamente!!");
            alert.showAndWait();
        }
    }

    public void logar(){
        Alert alert;
        try {
            String email, senha;
            email = txfEmail.getText();
            senha = txfSenha.getText();

            for(Usuario usuario: usuarios) {
                if (usuario.email.equals(email) && usuario.senha.equals(senha)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("gozado");
                    alert.setHeaderText("AEEEEEe");
                    alert.setContentText("Deu certo gay!!");
                    alert.showAndWait();
                    return;
                }
            }

            throw new IOException();
        } catch (IOException var4) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operação não pode ser realizada.");
            alert.setContentText("Digite um numero para pesquisar!!");
            alert.showAndWait();
        } catch (Exception var5) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operação não pode ser realizada.");
            alert.setContentText("Verifique se digitou corretamente!!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnLogin_click(ActionEvent event) {
        logar();
    }

    @FXML
    void btnRegister_click(ActionEvent event) {
        register_on();
    }
    
    public void register_on() {
        paneLog.setVisible(false);
        paneReg.setVisible(true);

        txfEmail.clear();
        txfSenha.clear();

        btnLogin.setVisible(false);
        btnRegister.setVisible(false);
    }

    public void register_off(){
        paneLog.setVisible(true);
        paneReg.setVisible(false);

        txfRegUsuario.clear();
        txfRegEmail.clear();
        txfRegSenha.clear();

        btnLogin.setVisible(true);
        btnRegister.setVisible(true);
    }

}



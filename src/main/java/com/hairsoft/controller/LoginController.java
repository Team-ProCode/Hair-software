package com.hairsoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.hairsoft.dao.UsuarioDAO;
import com.hairsoft.dialog.ErroDialog;
import com.hairsoft.method.Validation;
import com.hairsoft.entity.Usuario;
import com.hairsoft.hairsoft.LoginApp;
import com.hairsoft.hairsoft.MainScreenApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    public ArrayList<Usuario> usuarios = new ArrayList<>();

    ErroDialog dialog = new ErroDialog();

    public void initialize(URL url, ResourceBundle rb){    }

	//public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
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
        try{
            String nome, email, senha;
            nome = txfRegUsuario.getText();
            email = txfRegEmail.getText();
            senha = txfRegSenha.getText();

            if(nome.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getTitleRegisterWrong());
            }else if(!Validation.isValidEmail(email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterValidEmail());
            }else if(UsuarioDAO.existEmail(email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterEmailExists());
            }else if(!Validation.isValidSenha(senha)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageValidPassword());
            }else{
                Usuario usuario = new Usuario(nome, email, senha);
                new UsuarioDAO().inserir(usuario);
                register_off();
            }
        }catch(Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroGenericReg(), dialog.getMessageErroGenericReg());
        }
    }

    public void logar(){
        try {
            String email, senha;
            email = txfEmail.getText();
            senha = txfSenha.getText();

            if(UsuarioDAO.authentication(email, senha)){
                callScreen(UsuarioDAO.findEmail(email));
                LoginApp.getStage().close();
                return;
            }else{
                ErroDialog.alertDialog(dialog.getTitleErroLogin(), dialog.getMessegeErroLogin());
            }
            throw new IOException();
        } catch (IOException var4) {
            ErroDialog.alertDialog(dialog.getTitleErroLogin(), dialog.getMessegeErroLogin());
        }
    }

    public void callScreen(Usuario usuario){
        try{
            MainScreenApp screenApp = new MainScreenApp();
            System.out.println("Instancia Main Screen line:117");
            MainScreenApp.usuariosCall(usuario);
            System.out.println("Usuario call back line:121");
            screenApp.start(new Stage());
            System.out.println("Iniciando instancia line:123");
        }catch (Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroCallScreen(), dialog.getMessageErroCallScreen());
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



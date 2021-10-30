package com.hairsoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    public void initialize(URL url, ResourceBundle rb){
        usuarios.add(new Usuario(1, "Thy", "Thy@gmail.com", "Thy123"));

    }

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
        Alert alert;
        try{
            String nome, email, senha;
            nome = txfRegUsuario.getText();
            email = txfRegEmail.getText();
            senha = txfRegSenha.getText();

            if(nome.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getTitleRegisterWrong());
            }else if (Usuario.equalUser(usuarios, nome)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterUserExists());
            }
            else if(Usuario.equalEmail(usuarios , email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterEmailExists());
            }else if(!Validation.isValidEmail(email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterValidEmail());
            }else if(!Validation.isValidSenha(senha)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageValidPassword());
            }else{
                usuarios.add(new Usuario(Usuario.gerarId(usuarios),nome, email, senha ));
                register_off();
            }
        }catch(Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroGenericReg(), dialog.getMessageErroGenericReg());
        }
    }

    public void logar(){
        Alert alert;
        try {
            String userOrEmail, senha;
            userOrEmail = txfEmail.getText();
            senha = txfSenha.getText();

            if(Usuario.login(usuarios, userOrEmail, senha)){
                callScreen(userOrEmail);
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

    public void callScreen(String userOrEmail){
        MainScreenApp screenApp = new MainScreenApp();
        System.out.println("Instancia Main Screen line:117");
        try{
            MainScreenApp.usuariosCall(Usuario.returnUser(usuarios, userOrEmail));
            System.out.println("Usuario call back line:121");
            Stage stage = new Stage();
            screenApp.start(stage);
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



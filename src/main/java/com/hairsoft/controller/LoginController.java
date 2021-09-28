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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registro incorreto!");
                alert.setHeaderText("Nome de usuario ja esta sendo utilizado!");
                alert.showAndWait();
            }
            else if(Usuario.equalEmail(usuarios , email)){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registro incorreto!");
                alert.setHeaderText("Ja existe um usuario usando este Email!!");
                alert.showAndWait();
            }else if(!Validation.isValidEmail(email)){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registro incorreto!");
                alert.setHeaderText("Por favor insira um email valido!!");
                alert.showAndWait();
            }else if(!Validation.isValidSenha(senha)){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registro incorreto!");
                alert.setHeaderText("Senha precisa ter no maximo 8 caracteres!!");
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
            String userOrEmail, senha;
            userOrEmail = txfEmail.getText();
            senha = txfSenha.getText();

            for(Usuario usuario: usuarios) {
              
                if (Usuario.usuario.equals(userOrEmail) | Usuario.usuario.equals(userOrEmail) && usuario.senha.equals(senha)) {
                    System.out.println("Encontrou usuario line:108");
                    callScreen(usuario.email, usuario.usuario);
                    System.out.println("Chamou metodo line:110");
                    LoginApp.getStage().close();
                    System.out.println("Fechou tela de login line:112");
                    return;
                }


            }
            throw new IOException();
        } catch (IOException var4) {
            ErroDialog.alertDialog(dialog.getTitleErroLogin(), dialog.getMessegeErroLogin());
        }
    }

    public void callScreen(String Email, String Nome){
        MainScreenApp screenApp = new MainScreenApp();
        System.out.println("Intancia Main Screen line:136");
        try{

            MainScreenApp.usuariosCallBack(usuarios, Nome, Email);
            System.out.println("Usuario call back line:140");
            screenApp.start(new Stage());
            System.out.println("Iniciando instancia line:142");
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



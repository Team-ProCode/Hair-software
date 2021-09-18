package com.hairsoft.hairsoft;

import java.util.ArrayList;

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
    private AnchorPane paneLog, paneReg;

    @FXML
    private TextField txfRegUsuario, txfRegEmail, txfEmail;

    @FXML
    private PasswordField txfSenha, txfRegSenha;

    @FXML
    private Button btnCancel, btnConfirm, btnRegister, btnLogin;
    
    @FXML
    void btnCancel_click(ActionEvent event) {
    	register_off();
    }

    @FXML
    void btnConfirm_click(ActionEvent event) {
    	try {
    		inserir();
    	}catch(Exception e) {
			e.printStackTrace();
		}
    }

    public int gerarId(){
        int Id = 0;

        if (this.usuarios.isEmpty()) {
            return Id;
        } else {
            for(Usuario usuario: usuarios) {
                if (usuario.ID == Id) {
                    ++Id;
                }
            }
            return Id;
        }
    }

    public void inserir(){
        Alert alert;
        try{
            String nome, email, senha;
            nome = txfRegUsuario.getText();
            email = txfRegEmail.getText();
            senha = txfRegSenha.getText();

            usuarios.add(new Usuario(gerarId(),nome, email, senha ));

        }catch(Exception e){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operação não pode ser realizada.");
            alert.setContentText("Verifique se digitou corretamente!!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnLogin_click(ActionEvent event) {

    }

    @FXML
    void btnRegister_click(ActionEvent event) {
        register_on();
    }
    
    public void register_on() {
        paneLog.setVisible(false);
        paneReg.setVisible(true);

        btnLogin.setVisible(false);
        btnRegister.setVisible(false);
    }

    public void register_off(){
        paneLog.setVisible(true);
        paneReg.setVisible(false);

        btnLogin.setVisible(true);
        btnRegister.setVisible(true);
    }

}



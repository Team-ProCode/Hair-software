package com.hairsoft.controller;

import com.hairsoft.dialog.ErroDialog;
import com.hairsoft.entity.Salao;
import com.hairsoft.entity.Usuario;
import com.hairsoft.entity.UsuarioSalao;
import com.hairsoft.hairsoft.MainScreenApp;
import com.hairsoft.method.MainScreenMethod;

import com.gluonhq.charm.glisten.control.Avatar;
import com.hairsoft.method.ValidaCNPJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Tab tabHome, tabFeatures;
    @FXML private Avatar avatar, avatar1;
    @FXML private Label lblWellcomeName, lblUsuario;
    @FXML private ComboBox<String> cmbSalaoHome, cmbSalaoBar;
    @FXML private Button btnAdicionarSalao, btnEditarSalao, btnDeletarSalao, btnSalvarSalao;
    @FXML private Pane paneSalao;
    @FXML private TextField txfIdSalao, txfNomeSalao, txfCnpjSalao;

    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Salao> salaos = new ArrayList<>();
    public ArrayList<UsuarioSalao> usuarioSalaos = new ArrayList<>();

    public String Nome, Email;

    ErroDialog dialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listInMemory();
    }

    public void listInMemory(){
        usuarios = MainScreenApp.usuarios;

        Nome = MainScreenApp.Nome;
        Email = MainScreenApp.Email;

        lblWellcomeName.setText("Bem vindo(a) " + Nome);
    }

    //Este codigo esta com um erro, é preciso inserir um atributo salão dentro de um usuario
    public int idUsuario(){
        for(Usuario usuario: usuarios) {
            if (usuario.email.equals(Email) && usuario.usuario.equals(Nome)) {
                return usuario.getID();
            }
        }
        return 0;
    }

    public void addSalao() {
        try {
            int Id;
            String nomeSalao, CNPJ;

            Id = Integer.parseInt(txfIdSalao.getText());
            nomeSalao = txfNomeSalao.getText();
            CNPJ = txfCnpjSalao.getText();


            if (nomeSalao.isEmpty()){
                alertDialog(dialog.getTitiloErroIsEmpty(),dialog.getMensagemErroIsEmpty());
            }else if (CNPJ.isEmpty()){
                alertDialog(dialog.getTitiloErroIsEmpty(),dialog.getMensagemErroIsEmpty());
//          }else if (!ValidaCNPJ.isCNPJ(CNPJ)){
//                alertDialog(dialog.getTitiloErroIsEmpty(),dialog.getMensagemErroIsEmpty());
            }
            else {
                salaos.add(new Salao(Id, nomeSalao ,CNPJ));
                usuarioSalaos.add(new UsuarioSalao(idUsuario(), Id));
                System.out.println(usuarioSalaos.toArray().length);
            }
        }
        catch (Exception e){
            alertDialog(dialog.getTitleErroSys(),dialog.getMensagemErroSys());
        }
    }

    @FXML void btnAdicionarSalao_click(ActionEvent event) {
        paneContainerSalao.setVisible(true);
        txfIdSalao.setText(Integer.toString(Salao.gerarId(salaos)));
    }

    @FXML
    void btnDeletarSalao_click(ActionEvent event) {

    }

    @FXML
    void btnEditarSalao_click(ActionEvent event) {

    }

    @FXML
    void btnSalvarSalao_click(ActionEvent event) {
        addSalao();
        paneContainerSalao.setVisible(false);
    }

    public void alertDialog(String Title, String Messege){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(dialog.getTitiloErroIsEmpty());
        alert.setHeaderText(dialog.getMensagemErroIsEmpty());
        alert.showAndWait();
    }

}

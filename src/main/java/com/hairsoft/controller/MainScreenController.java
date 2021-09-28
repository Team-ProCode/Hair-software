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
import java.util.Objects;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Tab tabHome, tabFeatures;
    @FXML private Avatar avatar, avatar1;
    @FXML private Label lblWellcomeName, lblUsuario;
    @FXML private ComboBox<String> cmbSalaoHome, cmbSalaoBar;
    @FXML private Button btnAdicionarSalao, btnEditarSalao, btnDeletarSalao, btnCancelarSalao, btnSalvarSalao;
    @FXML private Pane paneSalao;
    @FXML private TextField txfIdSalao, txfNomeSalao, txfCnpjSalao;

    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Salao> salaos = new ArrayList<>();
    public ArrayList<Salao> salaosDUsuario = new ArrayList<>();
    public ArrayList<UsuarioSalao> usuarioSalaos = new ArrayList<>();

    public int ID;
    public String Nome, Email;

    public String Operacao;

    public int IDsalao;

    ErroDialog dialog = new ErroDialog();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionListis();
    }

    public void connectionListis(){
        listInMemoryUser();
    }


    public void listInMemoryUser(){

        usuarios = MainScreenApp.usuarios;
        Nome = MainScreenApp.Nome;
        Email = MainScreenApp.Email;
        ID = idUsuario();

        lblUsuario.setText(Nome);
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

    public void habilitarCamposSalao(){
        btnEditarSalao.setDisable(true);
        btnDeletarSalao.setDisable(true);

        paneContainerSalao.setVisible(true);
    }

    public void desabilitarCamposSalao(){
        btnAdicionarSalao.setDisable(false);
        btnEditarSalao.setDisable(false);

        txfIdSalao.setDisable(false);
        txfCnpjSalao.setDisable(false);
        paneContainerSalao.setVisible(false);
    }

    public void LimparCamposSalao(){
        txfNomeSalao.clear();
        txfCnpjSalao.clear();
    }

    public void addSalao() {
        try {
            int Id = Integer.parseInt(txfIdSalao.getText());
            String nomeSalao = txfNomeSalao.getText();
            String CNPJ = txfCnpjSalao.getText();

            if (nomeSalao.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (CNPJ.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (!ValidaCNPJ.isCNPJ(CNPJ) | Salao.cnpjExist(salaos, CNPJ)){
                ErroDialog.alertDialog(dialog.getTitleErroCNPJ(), dialog.getMessageErroCNPJ());
                txfCnpjSalao.clear();
            }
            else {
                salaos.add(new Salao(Id, nomeSalao ,CNPJ));
                usuarioSalaos.add(new UsuarioSalao(idUsuario(), Id));

                addCombSalao();
                LimparCamposSalao();
                desabilitarCamposSalao();
            }
        }
        catch (Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    public final void editarSalao(){
        try {
            int Id = Integer.parseInt(txfIdSalao.getText());

            if (txfNomeSalao.getText().equals("")){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else {
                for (Salao salao: salaos){
                    if(salao.ID == Id){
                        salao.setNome(txfNomeSalao.getText());
                    }
                }

                addCombSalao();
                LimparCamposSalao();
                desabilitarCamposSalao();
            }
        }
        catch (Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    public void deletarSalao(){
        try {
            int Id = Integer.parseInt(txfIdSalao.getText());

            salaos.remove(Id);

            btnDeletarSalao.setDisable(true);
            addCombSalao();
            LimparCamposSalao();
            desabilitarCamposSalao();

        }
        catch (Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    public void addCombSalao(){
        cmbSalaoHome.getItems().clear();
        cmbSalaoBar.getItems().clear();
        
      for (Salao salao: salaos){
            cmbSalaoHome.getItems().add(salao.ID + ": " + salao.Nome);
            cmbSalaoBar.getItems().add(salao.ID + ": " + salao.Nome);
        }
    }

    @FXML void btnAdicionarSalao_click(ActionEvent event) {
        logs("btnAdicionar ativado, line: 162");
        Operacao = "adicionar";
        txfIdSalao.setText(Integer.toString(Salao.gerarId(salaos)));

        btnDeletarSalao.setDisable(false);

        habilitarCamposSalao();
    }

    @FXML
    void btnEditarSalao_click(ActionEvent event) {
        logs("btnEditar ativado");
        try {
            if(Salao.existSalao(salaos, cmbSalaoHome.getValue().toString())){

                logs("Passou na condição IF do botão, line 180");
                Operacao = "editar";

                habilitarCamposSalao();

                btnDeletarSalao.setDisable(false);
                txfIdSalao.setDisable(true);
                txfCnpjSalao.setDisable(true);

                txfIdSalao.setText(Integer.toString(Salao.gerarId(salaos)));

                Salao salao = Salao.buscaSalao(salaos, cmbSalaoHome.getValue().toString());

                txfIdSalao.setText(salao.ID.toString());
                txfNomeSalao.setText(salao.getNome());
                txfCnpjSalao.setText(salao.Cnpj);
                txfCnpjSalao.setDisable(true);
            }
            else{
                ErroDialog.alertDialog("Advertencia", "Não foi possivel achar o salão");
            }
        }catch (Exception e){
            ErroDialog.alertDialog("Advertencia", "Não foi possivel achar o salão, selecione algo");
        }

    }


    @FXML
    void btnDeletarSalao_click(ActionEvent event) {
        deletarSalao();
    }

    @FXML
    void btnCancelarSalao_click(ActionEvent event) {
        desabilitarCamposSalao();
        LimparCamposSalao();
    }

    @FXML
    void btnSalvarSalao_click(ActionEvent event) {
        if(Operacao.equals("editar")){
            editarSalao();
        }else{
            addSalao();
        }
    }


    public void logs(String v){
        System.out.println(v);
    }



}

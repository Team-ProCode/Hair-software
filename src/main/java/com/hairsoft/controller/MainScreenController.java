package com.hairsoft.controller;

import com.hairsoft.dialog.ErroDialog;
import com.hairsoft.entity.Salao;
import com.hairsoft.entity.Usuario;
import com.hairsoft.entity.UsuarioSalao;
import com.hairsoft.hairsoft.MainScreenApp;

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

    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Salao> salaos = new ArrayList<>();
    public ArrayList<UsuarioSalao> usuarioSalaos = new ArrayList<>();

    public int ID;
    public String Nome, Email, Senha;

    public int IDsalao;

    ErroDialog dialog = new ErroDialog();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionListis();
    }

    public void connectionListis(){
        listInMemoryUser();
    }

    public int idUsuario(){
        for(Usuario usuario: usuarios) {
            if (usuario.email.equals(Email) && usuario.usuario.equals(Nome)) {
                Senha = usuario.getSenha();
                return usuario.getID();
            }
        }
        return 0;
    }

    void atualizaUsuario(){
        lblUsuario.setText(Nome);
        lblWellcomeName.setText("Bem vindo(a) " + Nome);

        lblUsuarioConfig.setText(Nome);
        lblEmailConfig.setText(Email);
        lblSenhaConfig.setText(Senha);
    }

    public void listInMemoryUser(){

        usuarios = MainScreenApp.usuarios;
        Nome = MainScreenApp.Nome;
        Email = MainScreenApp.Email;
        ID = idUsuario();

        atualizaUsuario();
    }

    @FXML private Tab tabHome, tabAgenda, tabFuncionarios,tabClientes, tabConfiguracao;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Home
    public String Operacao;

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Pane paneSalao;
    @FXML private Label lblWellcomeName, lblUsuario;
    @FXML private ComboBox<String> cmbSalaoHome, cmbSalaoBar;
    @FXML private Button btnAdicionarSalao, btnEditarSalao, btnDeletarSalao, btnCancelarSalao, btnSalvarSalao;
    @FXML private TextField txfIdSalao, txfNomeSalao, txfCnpjSalao;

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

    public void addCombSalao(){
        cmbSalaoHome.getItems().clear();
        cmbSalaoBar.getItems().clear();

        if(!salaos.isEmpty()){
            for (Salao salao: salaos){
                cmbSalaoHome.getItems().add(salao.ID + ": " + salao.Nome);
                cmbSalaoBar.getItems().add(salao.ID + ": " + salao.Nome);
            }
            tabAgenda.setDisable(false);
            tabFuncionarios.setDisable(false);
            tabClientes.setDisable(false);
        }else{
            tabAgenda.setDisable(true);
            tabFuncionarios.setDisable(true);
            tabClientes.setDisable(true);
        }

    }

    public void addSalao() {
        try {
            int Id = Integer.parseInt(txfIdSalao.getText());
            String nomeSalao = txfNomeSalao.getText();
            String CNPJ = txfCnpjSalao.getText();

            if (nomeSalao.isEmpty()){
                ErroDialog.erroDialogAlert(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (CNPJ.isEmpty()){
                ErroDialog.erroDialogAlert(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (!ValidaCNPJ.isCNPJ(CNPJ) | Salao.cnpjExist(salaos, CNPJ)){
                ErroDialog.erroDialogAlert(dialog.getTitleErroCNPJ(), dialog.getMessageErroCNPJ());
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
            ErroDialog.erroDialogAlert(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    public final void editarSalao(){
        try {
            int Id = Integer.parseInt(txfIdSalao.getText());

            if (txfNomeSalao.getText().equals("")){
                ErroDialog.erroDialogAlert(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
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
            ErroDialog.erroDialogAlert(dialog.getTitleErroSys(),dialog.getMensageErroSys());
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
            ErroDialog.erroDialogAlert(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    @FXML void btnAdicionarSalao_click(ActionEvent event) {
        logs("btnAdicionar ativado, line: 162");
        Operacao = "adicionar";
        txfIdSalao.setText(Integer.toString(Salao.gerarId(salaos)));

        btnDeletarSalao.setDisable(false);

        habilitarCamposSalao();
    }

    @FXML void btnEditarSalao_click(ActionEvent event) {
        logs("btnEditar ativado");
        try {
            if(Salao.existSalao(salaos, cmbSalaoHome.getValue())){

                logs("Passou na condição IF do botão, line 180");
                Operacao = "editar";

                habilitarCamposSalao();

                btnDeletarSalao.setDisable(false);
                txfIdSalao.setDisable(true);
                txfCnpjSalao.setDisable(true);

                txfIdSalao.setText(Integer.toString(Salao.gerarId(salaos)));

                Salao salao = Salao.buscaSalao(salaos, cmbSalaoHome.getValue());

                txfIdSalao.setText(salao.ID.toString());
                txfNomeSalao.setText(salao.getNome());
                txfCnpjSalao.setText(salao.Cnpj);
                txfCnpjSalao.setDisable(true);
            }
            else{
                ErroDialog.erroDialogAlert("Advertencia", "Não foi possivel achar o salão");
            }
        }catch (Exception e){
            ErroDialog.erroDialogAlert("Advertencia", "Não foi possivel achar o salão, selecione algo");
        }

    }

    @FXML void btnDeletarSalao_click(ActionEvent event) {
        deletarSalao();
    }

    @FXML void btnCancelarSalao_click(ActionEvent event) {
        desabilitarCamposSalao();
        LimparCamposSalao();
    }

    @FXML void btnSalvarSalao_click(ActionEvent event) {
        if(Operacao.equals("editar")){
            editarSalao();
        }else{
            addSalao();
        }
    }
    //End: Home
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Agenda
    //End: Agenda
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Funcionarios
    //End: Funcionario
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Clientes
    @FXML private Pane paneCliente;
    @FXML private TextField txfNomeCliente, txfEmailCliente, txfCpfCliente, txfFoneCliente, txfEnderecoCliente, txfNumeroCliente,txfCepCliente;
    @FXML private RadioButton radBhomem, radBmulher;
    @FXML private Button btnPesquisarCliente, btnAdicionarCliente, btnCancelarCliente, btnConfirmarCliente;
    @FXML private ToggleGroup groupGeneroCliente;

    public void habilitarCamposCliente(){
        paneCliente.setVisible(true);

        btnPesquisarCliente.setVisible(false);
        btnAdicionarCliente.setVisible(false);
    }

    public void desabilitarCamposCliente(){
        paneCliente.setVisible(false);

        btnPesquisarCliente.setVisible(true);
        btnAdicionarCliente.setVisible(true);
    }

    public void LimparCamposCliente(){
        txfNomeSalao.clear();
        txfCnpjSalao.clear();
    }

    @FXML
    void btnPesquisarCliente_click(ActionEvent event) {
        habilitarCamposCliente();
    }

    @FXML
    void btnAdicionarCliente_click(ActionEvent event) {
        habilitarCamposCliente();
    }

    @FXML
    void btnConfirmarCliente_click(ActionEvent event) {
        desabilitarCamposCliente();
    }

    @FXML
    void btnCancelarCliente_click(ActionEvent event) {
        desabilitarCamposCliente();
    }

    //End: Clientes
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Configuração

    @FXML private AnchorPane paneEditUsuario,paneSaveUsuario;

    @FXML private Button btnEditUsuario, btnSalvarUsuario;

    @FXML private TextField txfEmailConfig, txfSenhaConfig, txfUsuarioConfig;

    @FXML private Label lblUsuarioConfig, lblEmailConfig, lblSenhaConfig;

    @FXML
    void btnEditUsuario_click(ActionEvent event) {
        lblUsuarioConfig.setVisible(false);
        lblEmailConfig.setVisible(false);
        lblSenhaConfig.setVisible(false);

        txfUsuarioConfig.setText(Nome);
        txfEmailConfig.setText(Email);
        txfSenhaConfig.setText(Senha);

        paneSaveUsuario.setVisible(true);
        paneEditUsuario.setVisible(false);
    }

    @FXML
    void btnSalvarUsuario_click(ActionEvent event) {
        lblUsuarioConfig.setVisible(true);
        lblEmailConfig.setVisible(true);
        lblSenhaConfig.setVisible(true);

        Nome = txfUsuarioConfig.getText();
        Email = txfEmailConfig.getText();
        Senha = txfSenhaConfig.getText();

        atualizaUsuario();

        paneSaveUsuario.setVisible(false);
        paneEditUsuario.setVisible(true);
    }
    //End: Configuração
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------



    public void logs(String v){
        System.out.println(v);
    }

}

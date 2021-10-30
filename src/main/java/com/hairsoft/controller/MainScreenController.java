package com.hairsoft.controller;

import com.hairsoft.Connections.MongodbConnection;
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

    public static Usuario usuario = new Usuario();
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

    public void listInMemoryUser(){

        logs("Linha 49 erro");
        usuario = MainScreenApp.usuarios;

        logs("Linha 52 erro");
        Nome = usuario.getUsuario();
        Email = usuario.getEmail();
        ID = usuario.getID();

        atualizaUsuario();
    }

    void atualizaUsuario(){
        lblUsuario.setText(Nome);
        lblWellcomeName.setText("Bem vindo(a) " + Nome);

        lblUsuarioConfig.setText(Nome);
        lblEmailConfig.setText(Email);
        lblSenhaConfig.setText(Senha);
    }

    @FXML private Tab tabHome, tabAgenda, tabFuncionarios,tabClientes, tabConfiguracao;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Home
    public String Operacao;

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Pane paneSalao;
    @FXML private Label lblWellcomeName, lblUsuario;



    //End: Home
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Agenda
    //End: Agenda
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Funcionarios
    @FXML
    private AnchorPane paneFuncionario;

    @FXML private TextField txfNomeFuncionario;
    @FXML private ToggleGroup groupGeneroFuncionario;
    @FXML private RadioButton radBhomemFuncionario, radBmulherFuncionario;
    @FXML private TextField txfEmailFuncionario, txfCpfFuncionario, txfFoneFuncionario, txfEnderecoFuncionario, txfNumeroFuncionario, txfCepFuncionario;
    @FXML private Button btnConfirmarFuncionario, btnCancelarFuncionario;


    @FXML
    void btnConfirmarFuncionario_click(ActionEvent event) {

    }

    @FXML
    void btnCancelarFuncionario_click(ActionEvent event) {

    }
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

    @FXML private ComboBox<String> cmbSalaoHome, cmbSalaoBar;
    @FXML private Button btnAdicionarSalao, btnEditarSalao, btnDeletarSalao, btnCancelarSalao, btnSalvarSalao, btnTesteBanco;
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
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (CNPJ.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (!ValidaCNPJ.isCNPJ(CNPJ) | Salao.cnpjExist(salaos, CNPJ)){
                ErroDialog.alertDialog(dialog.getTitleErroCNPJ(), dialog.getMessageErroCNPJ());
                txfCnpjSalao.clear();
            }
            else {
                salaos.add(new Salao(Id, nomeSalao ,CNPJ));
                usuarioSalaos.add(new UsuarioSalao(ID, Id));

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

    @FXML void btnEditUsuario_click(ActionEvent event) {
        lblUsuarioConfig.setVisible(false);
        lblEmailConfig.setVisible(false);
        lblSenhaConfig.setVisible(false);

        txfUsuarioConfig.setText(Nome);
        txfEmailConfig.setText(Email);
        txfSenhaConfig.setText(Senha);

        paneSaveUsuario.setVisible(true);
        paneEditUsuario.setVisible(false);
    }

    @FXML void btnSalvarUsuario_click(ActionEvent event) {
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

    //End: Configuração
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    @FXML
    void btnTesteBanco_click(ActionEvent event) {
        MongodbConnection connection = new MongodbConnection();

        connection. inserir("Teste do Iserir");
        connection.mostrar();
    }


    public void logs(String v){
        System.out.println(v);
    }

}

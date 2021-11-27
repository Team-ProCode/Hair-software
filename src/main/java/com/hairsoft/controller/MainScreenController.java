package com.hairsoft.controller;

import com.hairsoft.dao.SalaoDAO;
import com.hairsoft.dao.UsuarioDAO;
import com.hairsoft.dialog.ErroDialog;
import com.hairsoft.entity.Salao;
import com.hairsoft.entity.Usuario;
import com.hairsoft.hairsoft.LoginApp;
import com.hairsoft.hairsoft.MainScreenApp;

import com.hairsoft.method.ValidaCNPJ;
import com.hairsoft.method.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    public ListView listViewFuncionarios;

    ErroDialog dialog = new ErroDialog();

    public ArrayList<Salao> salaos = new ArrayList<>();

    public int ID;
    public String Nome, Email, Senha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pushUser();
        atualizaUsuario();

        addCombSalao();
    }

    public void connectSalao(){
        try{
            salaos = new ArrayList<>(Objects.requireNonNull(SalaoDAO.findAll(ID)));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void pushUser(){
        try{
            Usuario usuario = MainScreenApp.usuarios;
            ID = usuario.id_user;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void atualizaUsuario(){
        try{
            Usuario usuario = UsuarioDAO.finallByCod(ID);

            Nome = usuario.name_user;
            Email = usuario.email_user;
            Senha = usuario.senha_user;

            lblUsuario.setText(Nome);
            lblWellcomeName.setText("Bem vindo(a) " + Nome);
            lblComprimentos.setText("É bom te ver " + Nome);


            txfUsuarioConfig.setText(Nome);
            txfEmailConfig.setText(Email);
            txfSenhaConfig.setText(Senha);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML private Tab tabHome, tabAgenda, tabFuncionarios,tabClientes, tabConfiguracao;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    //Start: Home
    public String Operacao;

    @FXML private AnchorPane paneContainerSalao;
    @FXML private Pane paneSalaoManipulation, paneSalaoFuncionario;
    ;
    @FXML private Label lblWellcomeName, lblUsuario, lblComprimentos;
    @FXML private TextField txfIdSalao, txfNomeSalao, txfCnpjSalao;

    @FXML private ComboBox<String> cmbSalaoHome, cmbSalaoBar;
    @FXML private Button btnAdicionarSalao, btnEditarSalao, btnDeletarSalao, btnCancelarSalao, btnSalvarSalao;

    public void habilitarCamposSalao(){
        if(Operacao.equals("adicionar")){
            txfIdSalao.setDisable(true);
            btnDeletarSalao.setDisable(true);
        }

        btnEditarSalao.setDisable(true);
        paneSalaoManipulation.setVisible(true);
    }

    public void desabilitarCamposSalao(){
        btnAdicionarSalao.setDisable(false);
        btnEditarSalao.setDisable(false);
        btnDeletarSalao.setDisable(false);

        txfIdSalao.setDisable(false);
        txfCnpjSalao.setDisable(false);
        paneSalaoManipulation.setVisible(false);
    }

    public void LimparCamposSalao(){
        txfNomeSalao.clear();
        txfCnpjSalao.clear();
    }

    public void addCombSalao(){
        cmbSalaoHome.getItems().clear();
        cmbSalaoBar.getItems().clear();

        try{
            connectSalao();
            if(!salaos.isEmpty()){
                for (Salao salao: salaos){
                    cmbSalaoHome.getItems().add(salao.id_salao + ": " + salao.nome_salao);
                    cmbSalaoBar.getItems().add(salao.id_salao + ": " + salao.nome_salao);
                }
                paneSalaoFuncionario.setVisible(true);

                tabAgenda.setDisable(false);
                tabFuncionarios.setDisable(false);
                tabClientes.setDisable(false);
            }else{
                paneSalaoFuncionario.setVisible(false);

                tabAgenda.setDisable(true);
                tabFuncionarios.setDisable(true);
                tabClientes.setDisable(true);
            }
        }catch (Exception ex){
            cmbSalaoHome.getItems().add("--Vazio--");
            ex.printStackTrace();
        }


    }

    public void addSalao() {
        try {
            logs("Entrou no Try do AddSalao");
            String nomeSalao = txfNomeSalao.getText();
            String CNPJ = txfCnpjSalao.getText();

            if (nomeSalao.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (CNPJ.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleErroIsEmpty(),dialog.getMessageErroIsEmpty());
            }else if (!ValidaCNPJ.isCNPJ(CNPJ)){
                ErroDialog.alertDialog(dialog.getTitleErroCNPJ(), dialog.getMessageErroCNPJ());
                txfCnpjSalao.clear();
            }else if (SalaoDAO.existCnpj(CNPJ, ID)){
                ErroDialog.alertDialog(dialog.getTitleErroCNPJ(), "Esse CNPJ ja esta sendo utilizado");
                txfCnpjSalao.clear();
            }else {
                new SalaoDAO().inserir(new Salao(nomeSalao ,CNPJ, ID));

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
                    if(salao.id_salao == Id){
                        System.out.println(salao);
                        salao.nome_salao = txfNomeSalao.getText();
                        new SalaoDAO().update(salao);
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

            for (Salao salao: salaos){
                if(salao.id_salao == Id){
                    new SalaoDAO().delete(salao);
                }
            }
            btnDeletarSalao.setDisable(true);
            addCombSalao();
            LimparCamposSalao();
            desabilitarCamposSalao();
        }
        catch (Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroSys(),dialog.getMensageErroSys());
        }
    }

    @FXML void btnAdicionarSalao_click(ActionEvent event) {
        logs("btnAdicionar ativado, line: 162");
        Operacao = "adicionar";

        txfIdSalao.setEditable(false);
        txfIdSalao.setText(String.valueOf(SalaoDAO.countSalao(ID)));

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

                Salao salao = Salao.buscaSalao(salaos, cmbSalaoHome.getValue().toString());

                txfIdSalao.setText(salao.id_salao.toString());
                txfNomeSalao.setText(salao.getNome_salao());
                txfCnpjSalao.setText(salao.cnpj_salao);
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

    @FXML private AnchorPane paneEditUsuario, paneSaveUsuario, paneUsuarioConfig;
    @FXML private Button btnEditUsuario, btnSalvarUsuario, btnLogout ;
    @FXML private TextField txfEmailConfig, txfSenhaConfig, txfUsuarioConfig;

    public Label lblWellcomeName1;

    public void edit(){
        try{
            Nome = txfUsuarioConfig.getText();
            Email = txfEmailConfig.getText();
            Senha = txfSenhaConfig.getText();

            if(Nome.isEmpty()){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getTitleRegisterWrong());
            }else if(!Validation.isValidEmail(Email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterValidEmail());
            }else if(UsuarioDAO.existEmail(Email)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageRegisterEmailExists());
            }else if(!Validation.isValidSenha(Senha)){
                ErroDialog.alertDialog(dialog.getTitleRegisterWrong(), dialog.getMessageValidPassword());
            }else{
                Usuario usuario = new Usuario(ID, Nome, Email, Senha);
                new UsuarioDAO().update(usuario);
                atualizaUsuario();

                txfUsuarioConfig.setEditable(false);
                txfSenhaConfig.setEditable(false);
                txfEmailConfig.setEditable(false);

                paneUsuarioConfig.setDisable(false);

                paneSaveUsuario.setDisable(false);
                paneEditUsuario.setDisable(true);
            }
        }catch(Exception e){
            ErroDialog.alertDialog(dialog.getTitleErroGenericReg(), dialog.getMessageErroGenericReg());
        }
    }

    @FXML void btnEditUsuario_click(ActionEvent event) {
        paneUsuarioConfig.setDisable(true);

        txfUsuarioConfig.setEditable(true);
        txfSenhaConfig.setEditable(true);
        txfEmailConfig.setEditable(true);

        paneSaveUsuario.setDisable(true);
        paneEditUsuario.setDisable(false);
    }

    @FXML void btnSalvarUsuario_click(ActionEvent event) {
        edit();
    }

    public void btnLogout_clck(ActionEvent actionEvent) {
        MainScreenApp.getStage().close();
        try{
            LoginApp loginApp = new LoginApp();
            loginApp.start(new Stage());
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }


    //End: Configuração
    //----------------------------------------------------------------------------------------------------------------------------------------------------------


    public void logs(String v){
        System.out.println(v);
    }


}

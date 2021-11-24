package com.hairsoft.hairsoft;

//import com.hairsoft.entity.Clientes;
//import com.hairsoft.entity.Colaboradores;
//import com.hairsoft.entity.Salao;
import com.hairsoft.entity.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenApp extends Application {

    private static Stage stage;

    public static Usuario usuarios = new Usuario();

    public static void usuariosCall(Usuario usuarioLogado){
        MainScreenApp.usuarios = usuarioLogado;
    }

    public static Usuario usuariosBack(){return usuarios;}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreenApp.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Hair Soft!");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setStage(Stage stage) {
        MainScreenApp.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}

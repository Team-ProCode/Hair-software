package com.hairsoft.hairsoft;

import com.hairsoft.entity.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenApp extends Application {

    private static Stage stage;

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static String Nome, Email;

    public static void usuariosCallBack(ArrayList<Usuario> user, String nome, String email){
        usuarios.addAll(user);
        Nome = nome;
        Email = email;
    }

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

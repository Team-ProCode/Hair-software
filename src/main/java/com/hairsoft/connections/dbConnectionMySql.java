package com.hairsoft.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnectionMySql {

    private static dbConnectionMySql Connection;

    private final String caminho = "localhost";
    private final String porta = "3306";
    private final String nome = "databaseHair";
    private final String usuario = "root";
    private final String senha = "Thyago123";

    private String Url = "jdbc:mysql://" + caminho + ":" + porta + "/" + nome + "?useTimezone=true&serverTimezone=GMT";

    public static dbConnectionMySql getInstance(){
        if(null == Connection){
            Connection = new dbConnectionMySql();
            return Connection;
        }
        return Connection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(Url, usuario, senha);
    }

    public static void main(String[] args) {
        try{
            System.out.println(getInstance().getConnection());
        }catch (Exception ex){
            ex.printStackTrace();
            System.err.println("Erro ao conectar com o DB " + ex);
        }
    }
}

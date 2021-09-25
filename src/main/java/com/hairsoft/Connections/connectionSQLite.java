package com.hairsoft.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionSQLite {

    private Connection connection;

    public boolean connect(){

        try{
            String url = "jdbc:sqlite:dados/BancoSQlite.db";

            this.connection = DriverManager.getConnection(url);
        }catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean disconnect(){
        try{
            if (!this.connection.isClosed()){
                this.connection.close();
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return true;
    }
}

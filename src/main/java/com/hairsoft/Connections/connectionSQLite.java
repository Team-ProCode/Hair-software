//package com.hairsoft.Connections;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class connectionSQLite {
//
//    private Connection connection;
//
//    public boolean connect(){
//        connection = null;
//        try{
//            String url = "jdbc:sqlite:dados/BancoSQlite.db";
//            this.connection = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
//
//        }catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//        return true;
//    }
//
//    public boolean disconnect(){
//        try{
//            if (!this.connection.isClosed()){
//                this.connection.close();
//            }
//        }catch(SQLException e){
//            System.err.println(e.getMessage());
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        connect();
//    }
//}

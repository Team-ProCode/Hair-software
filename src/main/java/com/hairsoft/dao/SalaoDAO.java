package com.hairsoft.dao;

import com.hairsoft.Connections.dbConnectionMySql;
import com.hairsoft.entity.Salao;
import com.hairsoft.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalaoDAO {

    public void inserir(Salao salao){
        try{
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "INSERT INTO salao(nome, cnpj, codigo_usuario) VALUES (? , ?, ? )";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1 , salao.nome_salao);
            statement.setString(2 , salao.cnpj_salao);
            statement.setInt(3 , salao.id_user);

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void delete(Salao salao){

        try{
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "delete from salao where codigo = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1 , salao.id_salao);

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void update(Salao salao){

        try{
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "update salao set nome = ? where codigo = ? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, salao.getNome_salao());
            statement.setInt(2, salao.getId_salao());

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Salao finallByCod(int cod) throws Exception {

        Connection connection = dbConnectionMySql.getInstance().getConnection();

        String sql = "select * from salao where codigo = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1 , cod);

        ResultSet resultSet = statement.executeQuery(sql);

        Salao salao = null;

        if(resultSet.next()) {
            salao = new Salao();
            salao.setId_salao(resultSet.getInt("codigo"));
            salao.setNome_salao(resultSet.getString("nome"));
            salao.setCnpj_Salao(resultSet.getString("email"));
            salao.setId_user(resultSet.getInt("codigo_usuario"));
        }
        connection.close();
        return salao;
    }

    public static int countSalao(int id_user) {
        try {
            int ult = 0;
            ArrayList<Salao> salaos = new ArrayList<>(Objects.requireNonNull(SalaoDAO.findAll(id_user)));
            if (!salaos.isEmpty()) {
                for(Salao salao: salaos) {
                    ult = salao.id_salao;
                }
            }
            return ++ult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    public static List<Salao> findAll(int id_user) {
        try {
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "SELECT * FROM salao WHERE codigo_usuario = " + id_user;

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);

            List<Salao> salaos = new ArrayList<>();

            while(resultSet.next()) {
                Salao salao = new Salao();
                salao.setId_salao(resultSet.getInt("codigo"));
                salao.setNome_salao(resultSet.getString("nome"));
                salao.setCnpj_Salao(resultSet.getString("cnpj"));
                salao.setId_user(resultSet.getInt("codigo_usuario"));
                salaos.add(salao);
            }
            connection.close();
            return salaos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean existCnpj(String cnpj, int id_user){
        try {
            ArrayList<Salao> salaos = new ArrayList<>(Objects.requireNonNull(SalaoDAO.findAll(id_user)));
            if (!salaos.isEmpty()) {
                for (Salao salao : salaos) {
                    if (salao.cnpj_salao.equals(cnpj)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

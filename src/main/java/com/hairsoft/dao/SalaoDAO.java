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

            statement.setString(1 , salao.getName_user());
            statement.setString(2 , salao.getEmail_user());
            statement.setInt(3 , salao.getId_user());

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void delete(Usuario usuario){

        try{
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "delete from usuario where codigo = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1 , usuario.id_user);

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void update(Usuario usuario){

        try{
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "update usuario nome = ?, email = ?, senha = ? where idPessoa = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1 , usuario.getName_user());
            statement.setString(2 , usuario.getEmail_user());
            statement.setString(3 , usuario.getSenha_user());
            statement.setInt(4 , usuario.getId_user());

            statement.execute();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Usuario finallByCod(int cod) throws Exception {

        Connection connection = dbConnectionMySql.getInstance().getConnection();

        String sql = "select * from usuario where codigo = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1 , cod);

        ResultSet resultSet = statement.executeQuery(sql);

        Usuario usuario = null;

        if(resultSet.next()) {
            usuario = new Usuario();
            usuario.setId_user(resultSet.getInt("codigo"));
            usuario.setName_user(resultSet.getString("nome"));
            usuario.setEmail_user(resultSet.getString("email"));
            usuario.setSenha_user(resultSet.getString("senha"));
        }
        connection.close();
        return usuario;
    }

    public static Usuario findEmail(String email) {
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>(Objects.requireNonNull(UsuarioDAO.findAll()));
            if (!usuarios.isEmpty()) {
                for (Usuario usuario : usuarios) {
                    if (usuario.email_user.equals(email)) {
                        return usuario;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Usuario> findAll() {
        try {
            Connection connection = dbConnectionMySql.getInstance().getConnection();

            String sql = "SELECT * FROM usuario";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery(sql);

            List<Usuario> usuarios = new ArrayList<>();

            while(resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_user(resultSet.getInt("codigo"));
                usuario.setName_user(resultSet.getString("nome"));
                usuario.setEmail_user(resultSet.getString("email"));
                usuario.setSenha_user(resultSet.getString("senha"));
                usuarios.add(usuario);
            }
            connection.close();
            return usuarios;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean existEmail(String email){
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>(Objects.requireNonNull(UsuarioDAO.findAll()));
            if (!usuarios.isEmpty()) {
                for (Usuario usuario : usuarios) {
                    if (usuario.email_user.equals(email)) {
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

    public static boolean authentication(String email, String senha){
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>(Objects.requireNonNull(UsuarioDAO.findAll()));
            if (!usuarios.isEmpty()) {
                for (Usuario usuario : usuarios) {
                    if (usuario.email_user.equals(email) && usuario.senha_user.equals(senha)) {
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

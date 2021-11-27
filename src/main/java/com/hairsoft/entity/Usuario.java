package com.hairsoft.entity;

import com.hairsoft.dao.UsuarioDAO;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {

	Usuario usuario;

	public Integer id_user;
	public String name_user;
	public String email_user;
	public String senha_user;

	public Usuario ()
	{
		
	}

	public Usuario(String usuario, String email, String senha) {
		this.name_user = usuario;
		this.email_user = email;
		this.senha_user = senha;
	}
	
	public Usuario(int id, String usuario, String email, String senha) {
		this.id_user = id;
		this.name_user = usuario;
		this.email_user = email;
		this.senha_user = senha;
	}

	public static int gerarId(ArrayList<Usuario> usuarios){
		int Id = 0;
		if (usuarios.isEmpty()) {
			return Id;
		} else {
			for(Usuario usuario: usuarios) {
				if (usuario.id_user == Id) {
					++Id;
				}
			}
			return Id;
		}
	}

	public static Usuario returnUser(ArrayList<Usuario> usuarios, String userOrEmail){
		Usuario userLogado = new Usuario();
		for(Usuario usuario: usuarios) {
			if (usuario.email_user.equals(userOrEmail) | usuario.name_user.equals(userOrEmail)) {
				userLogado = usuario;
				return userLogado;
			}
		}
		return userLogado;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setSenha_user(String senha_user) {
		this.senha_user = senha_user;
	}

	public String getSenha_user() {
		return senha_user;
	}

}

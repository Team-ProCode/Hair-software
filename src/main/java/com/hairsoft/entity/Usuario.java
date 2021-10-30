package com.hairsoft.entity;

import java.util.ArrayList;

public class Usuario {

	public Integer ID;
	public String usuario;
	public String email;
	public String senha;

	public Usuario ()
	{
		
	}
	
	public Usuario(int id, String usuario, String email, String senha) {
		this.ID = id;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}

	public static int gerarId(ArrayList<Usuario> usuarios){
		int Id = 0;
		if (usuarios.isEmpty()) {
			return Id;
		} else {
			for(Usuario usuario: usuarios) {
				if (usuario.ID == Id) {
					++Id;
				}
			}
			return Id;
		}
	}

	public static boolean equalUser(ArrayList<Usuario> usuarios, String user){
		if (usuarios.isEmpty()){
			return false;
		}else{
			for (Usuario usuario: usuarios){
				if (usuario.usuario.equals(user)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean equalEmail(ArrayList<Usuario> usuarios, String email){
		if (usuarios.isEmpty()) {
			return false;
		} else {
			for(Usuario usuario: usuarios) {
				if (usuario.email.equals(email)) {
					return true;
				}
			}
			return false;
		}
	}

	public static boolean login(ArrayList<Usuario> usuarios, String userOrEmail, String senha){
		for(Usuario usuario: usuarios) {
			if ((usuario.email.equals(userOrEmail) && usuario.senha.equals(senha)) || (usuario.usuario.equals(userOrEmail) && usuario.senha.equals(senha))) {
				return true;
			}
		}
		return false;
	}

	public static Usuario returnUser(ArrayList<Usuario> usuarios, String userOrEmail){
		Usuario userLogado = new Usuario();
		for(Usuario usuario: usuarios) {
			if (usuario.email.equals(userOrEmail) | usuario.usuario.equals(userOrEmail)) {
				userLogado = usuario;
				return userLogado;
			}
		}
		return userLogado;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}

package com.hairsoft.entity;

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

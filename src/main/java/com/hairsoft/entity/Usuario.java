package com.hairsoft.entity;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	public Integer ID;
	public String usuario;
	public String email;
	public String senha;
	public List<Salao> salaos;

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

	public void setSalaos(List<Salao> salaos) {
		this.salaos = salaos;
	}

	public List<Salao> getSalaos() {
		return salaos;
	}
}

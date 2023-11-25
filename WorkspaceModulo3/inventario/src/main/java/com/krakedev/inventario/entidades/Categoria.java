package com.krakedev.inventario.entidades;

public class Categoria {

	private int codigo;
	private String categoria;
	
	
	public Categoria( ) {
	
	}
	
	
	
	public Categoria(int codigo, String categoria) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", categoria=" + categoria + "]";
	}
	
	
	
	
}

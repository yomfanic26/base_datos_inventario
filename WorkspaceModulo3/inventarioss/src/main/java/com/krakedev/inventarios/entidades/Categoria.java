package com.krakedev.inventarios.entidades;

public class Categoria {
	private int codigo_cat;
	private String nombre;
	private Categoria categoria_padre;

	public Categoria() {
		super();
	}

	public Categoria(int codigo_cat, String nombre, Categoria categoria_padre) {
		super();
		this.codigo_cat = codigo_cat;
		this.nombre = nombre;
		this.categoria_padre = categoria_padre;
	}

	public int getCodigo_cat() {
		return codigo_cat;
	}

	public void setCodigo_cat(int codigo_cat) {
		this.codigo_cat = codigo_cat;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria_padre() {
		return categoria_padre;
	}

	public void setCategoria_padre(Categoria categoria_padre) {
		this.categoria_padre = categoria_padre;
	}

	@Override
	public String toString() {
		return "Categoria [codigo_cat=" + codigo_cat + ", nombre=" + nombre + ", categoria_padre=" + categoria_padre
				+ "]";
	}




	

}

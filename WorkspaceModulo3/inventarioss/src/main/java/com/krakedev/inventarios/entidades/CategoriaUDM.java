package com.krakedev.inventarios.entidades;

public class CategoriaUDM {
	private int codigoCateUDM;
	private String nombre;

	public CategoriaUDM() {
		super();
	}

	public CategoriaUDM(int codigoCateUDM, String nombre) {
		super();
		this.codigoCateUDM = codigoCateUDM;
		this.nombre = nombre;
	}

	public int getCodigo_cat_udm() {
		return codigoCateUDM;
	}

	public void setCodigo_cat_udm(int codigo_cat_udm) {
		this.codigoCateUDM = codigo_cat_udm;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CategoriaUDM [codigoCateUDM=" + codigoCateUDM + ", nombre=" + nombre + "]";
	}

	




	

}

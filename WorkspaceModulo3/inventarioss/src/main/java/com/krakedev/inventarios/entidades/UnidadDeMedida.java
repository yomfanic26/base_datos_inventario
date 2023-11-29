package com.krakedev.inventarios.entidades;

public class UnidadDeMedida {
	private String nombre;
	private String descripcion;
	private CategoriaUDM codigoCateUDM;


	public UnidadDeMedida() {
		super();
	}

	public UnidadDeMedida(String nombre, String descripcion, CategoriaUDM codigoCateUDM) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoCateUDM = codigoCateUDM;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaUDM getCodigo_ctr_udm() {
		return codigoCateUDM;
	}

	public void setCodigo_ctr_udm(CategoriaUDM codigoCateUDM) {
		this.codigoCateUDM = codigoCateUDM;
	}

	
	@Override
	public String toString() {
		return "UnidadDeMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", codigoCateUDM="
				+ codigoCateUDM + "]";
	}

	


	

}

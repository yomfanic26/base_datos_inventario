package com.krakedev.inventarios.entidades;

public class Proveedor {
	private String codigo_prov;
	private String tipo_documento;
	private String nombre;
	private String telefono;
	private String correo;
	private String direccion;

	public Proveedor() {
		super();
	}

	public Proveedor(String codigo_prov, String tipo_documento, String nombre, String telefono, String correo,
			String direccion) {
		super();
		this.codigo_prov = codigo_prov;
		this.tipo_documento = tipo_documento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	public String getCodigo_prov() {
		return codigo_prov;
	}

	public void setCodigo_prov(String codigo_prov) {
		this.codigo_prov = codigo_prov;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Proveedors [codigo_prov=" + codigo_prov + ", tipo_documento=" + tipo_documento + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + "]";
	}

}

package com.krakedev.entidades;

public class Cliente {
	private String cedula;

	public int getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijo(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	private int numeroHijos;

	public Cliente() {

	}

	public Cliente(String cedula, String nombre, int numeroHijos) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.numeroHijos = numeroHijos;

	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;

	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", numeroHijos=" + numeroHijos + ", nombre=" + nombre + "]";
	}

	

}
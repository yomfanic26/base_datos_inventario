package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.security.Timestamp;

public class HistorialStok {
	private int codigo  ;
	private Timestamp fecha;
	private String referencia ;
	private Producto producto;
	private int cantidad;
	


	public HistorialStok() {
		super();
	}



	public HistorialStok(int codigo, Timestamp fecha, String referencia, Producto producto, int cantidad) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.referencia = referencia;
		this.producto = producto;
		this.cantidad = cantidad;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public Timestamp getFecha() {
		return fecha;
	}



	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}



	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	@Override
	public String toString() {
		return "HistorialStok [codigo=" + codigo + ", fecha=" + fecha + ", referencia=" + referencia + ", producto="
				+ producto + ", cantidad=" + cantidad + "]";
	}



}

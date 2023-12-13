package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class Producto {
	private int codigo;
	private String nombre;
	private UnidadDeMedida unidadMedida;
	private BigDecimal precio;
	private boolean tieneIva;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;


	public Producto() {
		super();
	}


	public Producto(int codigo, String nombre, UnidadDeMedida unidadMedida, BigDecimal precio, boolean tieneIva,
			BigDecimal coste, Categoria categoria, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidadMedida = unidadMedida;
		this.precio = precio;
		this.tieneIva = tieneIva;
		this.coste = coste;
		this.categoria = categoria;
		this.stock = stock;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public UnidadDeMedida getUnidadMedida() {
		return unidadMedida;
	}


	public void setUnidadMedida(UnidadDeMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	public boolean isTieneIva() {
		return tieneIva;
	}


	public void setTieneIva(boolean tieneIva) {
		this.tieneIva = tieneIva;
	}


	public BigDecimal getCoste() {
		return coste;
	}


	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", precio="
				+ precio + ", tieneIva=" + tieneIva + ", coste=" + coste + ", categoria=" + categoria + ", stock="
				+ stock + "]";
	}

	

}

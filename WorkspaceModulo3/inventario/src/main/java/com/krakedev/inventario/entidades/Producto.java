package com.krakedev.inventario.entidades;

public class Producto {
	

	private String codigo;
	private Categoria categoria;
	private  double precio;
	private int stock;
	
	public Producto() {
	
	}
	
	
	public Producto(String codigo, Categoria categoria, double precio, int stock) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", categoria=" + categoria + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}
	
	
	
}

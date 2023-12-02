package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class DetallePedido {
	private int codigo;
	private Pedido cabecera;
	private Producto producto;
	private int cantidad;
	private BigDecimal subtotal;
	private int cantidad_recibida;

	public DetallePedido() {
		super();
	}

	public DetallePedido(int codigo, Pedido cabecera, Producto producto, int cantidad, BigDecimal subtotal,
			int cantidad_recibida) {
		super();
		this.codigo = codigo;
		this.cabecera = cabecera;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.cantidad_recibida = cantidad_recibida;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pedido getCabecera() {
		return cabecera;
	}

	public void setCabecera(Pedido cabecera) {
		this.cabecera = cabecera;
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

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public int getCantidad_recibida() {
		return cantidad_recibida;
	}

	public void setCantidad_recibida(int cantidad_recibida) {
		this.cantidad_recibida = cantidad_recibida;
	}

	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", cabecera=" + cabecera + ", producto=" + producto + ", cantidad="
				+ cantidad + ", subtotal=" + subtotal + ", cantidad_recibida=" + cantidad_recibida + "]";
	}


}

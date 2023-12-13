package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class DetalleVenta {
	private int codigo ;
	private Venta cabecera_venta;
	private Producto producto ;
	private int cantidad ;
	private BigDecimal precio_venta;
	private BigDecimal subtotal ;
	private BigDecimal subtotal_iva ;

	public DetalleVenta() {
		super();
	}

	public DetalleVenta(int codigo, Venta cabecera_venta, Producto producto, int cantidad, BigDecimal precio_venta,
			BigDecimal subtotal, BigDecimal subtotal_iva) {
		super();
		this.codigo = codigo;
		this.cabecera_venta = cabecera_venta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio_venta = precio_venta;
		this.subtotal = subtotal;
		this.subtotal_iva = subtotal_iva;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Venta getCabecera_venta() {
		return cabecera_venta;
	}

	public void setCabecera_venta(Venta cabecera_venta) {
		this.cabecera_venta = cabecera_venta;
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

	public BigDecimal getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(BigDecimal precio_venta) {
		this.precio_venta = precio_venta;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getSubtotal_iva() {
		return subtotal_iva;
	}

	public void setSubtotal_iva(BigDecimal subtotal_iva) {
		this.subtotal_iva = subtotal_iva;
	}

	@Override
	public String toString() {
		return "DetalleVenta [codigo=" + codigo + ", cabecera_venta=" + cabecera_venta + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precio_venta=" + precio_venta + ", subtotal=" + subtotal
				+ ", subtotal_iva=" + subtotal_iva + "]";
	}






}

package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Venta {
	private int codigo;
	private  Timestamp fecha;
	private BigDecimal totaSinlIva;
	private BigDecimal iva ;
	private BigDecimal total;
	
	public ArrayList<DetalleVenta> getDetalles() {
		return detalles;
	}

	

	private ArrayList <DetalleVenta> detalles;


	public Venta() {
		super();
	}


	public Venta(int codigo, Timestamp fecha, BigDecimal totaSinlIva, BigDecimal iva, BigDecimal total,
			ArrayList<DetalleVenta> detalles) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.totaSinlIva = totaSinlIva;
		this.iva = iva;
		this.total = total;
		this.detalles = detalles;
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

	public BigDecimal getTotaSinlIva() {
		return totaSinlIva;
	}

	public void setTotaSinlIva(BigDecimal totaSinlIva) {
		this.totaSinlIva = totaSinlIva;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public void setDetalles(ArrayList<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Venta [codigo=" + codigo + ", fecha=" + fecha + ", totaSinlIva=" + totaSinlIva + ", iva=" + iva
				+ ", total=" + total + "]";
	}




}

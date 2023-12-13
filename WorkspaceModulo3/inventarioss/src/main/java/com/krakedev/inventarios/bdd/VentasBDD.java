package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevEception;
import com.krakedev.inventarios.utils.ConexcionBDD;

public class VentasBDD {

	// insertar venta
	public void insertar(Venta venta) throws KrakedevEception {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDetalle = null;
		PreparedStatement psHis = null;


		Date fechaActual = new Date();
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
		ResultSet rsClave = null;

		int codigoCabecera = 0;
		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("INSERT INTO cabecera_venta (fecha,total_iva,iva,total)" + "VALUES (?,?,?,?) ",
					Statement.RETURN_GENERATED_KEYS);

			ps.setTimestamp(1, fechaHoraActual);
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.setInt(4, 0);

			ps.executeUpdate();
			// obtiene el codigo de la cabecera
			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			// recupero todo los detalles del pedido
			ArrayList<DetalleVenta> detallesVentass = venta.getDetalles();
			// barro los detalle y hago un insertVentas

			DetalleVenta det;
			for (int i = 0; i < detallesVentass.size(); i++) {
				det = detallesVentass.get(i);
				psDetalle = con.prepareStatement(
						"INSERT INTO detalle_venta (cabecera_venta,producto,cantidad,precio_venta,subtotal,subtotal_iva)"
								+ "VALUES (?,?,?,?,?,?)");
				psDetalle.setInt(1, codigoCabecera);
				psDetalle.setInt(2, det.getProducto().getCodigo());
				psDetalle.setInt(3, det.getCantidad());
				psDetalle.setBigDecimal(4, det.getProducto().getPrecio());
				BigDecimal pv = det.getProducto().getPrecio();
				BigDecimal cantidad = new BigDecimal(det.getCantidad());
				BigDecimal subtotal = pv.multiply(cantidad);
				psDetalle.setBigDecimal(5, subtotal);

				// asignar valor fijo de iva
				BigDecimal valorIVa = new BigDecimal(1.12);

				if (det.getProducto().isTieneIva() == true) {
					BigDecimal SubtotalIva = subtotal.multiply(valorIVa);
					psDetalle.setBigDecimal(6, SubtotalIva);

				} else {
					psDetalle.setBigDecimal(6, subtotal);

				}

				psDetalle.executeUpdate();

			}

			
			//Calculando el toal del valo de product con iva y sin iva
			BigDecimal sumaSinIva = new BigDecimal(0);
			BigDecimal sumaConIva = new BigDecimal(0);

			for (int i = 0; i < detallesVentass.size(); i++) {
				det = detallesVentass.get(i);

				if (det.getProducto().isTieneIva() == true) {
					sumaConIva = sumaConIva.add(det.getProducto().getPrecio());
				} else {
					sumaSinIva = sumaSinIva.add(det.getProducto().getPrecio());
				}
				
				
				//actulizar historial stock
			
				psHis = con
						.prepareStatement("INSERT INTO histotial_stock (fecha,referencia,producto,cantidad)"
								+ "VALUES(?,?,?,?)");
				
				psHis.setTimestamp(1, fechaHoraActual);
				psHis.setString(2, "Venta "+codigoCabecera);
				psHis.setInt(3, det.getProducto().getCodigo());
				int cantidadVendia = det.getCantidad()*-1;

				psHis.setInt(4, cantidadVendia);

				psHis.executeUpdate();
			}
			
			// Actualiza la cabecera del pedido

			BigDecimal total = sumaConIva.add(sumaSinIva);

			ps = con.prepareStatement(
					"UPDATE cabecera_venta SET total_iva = ?, iva = ?, total = ? WHERE codigo = ?");
			ps.setBigDecimal(1, sumaSinIva);
			ps.setBigDecimal(2, sumaConIva);
			ps.setBigDecimal(3, total);
			ps.setInt(4, codigoCabecera);
			ps.executeUpdate();
			
		

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al insertar la venta Detalle: " + e.getMessage());
		} catch (KrakedevEception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

}

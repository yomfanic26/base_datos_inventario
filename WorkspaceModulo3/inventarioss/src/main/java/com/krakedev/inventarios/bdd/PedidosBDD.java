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

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakedevEception;
import com.krakedev.inventarios.utils.ConexcionBDD;

public class PedidosBDD {

	// Recuperar todos los provedores

	/*
	 * public ArrayList<Pedido> buscar(String subcadena) throws KrakedevEception {
	 * ArrayList<Pedido> pedidos = new ArrayList<Pedido>(); Connection con = null;
	 * PreparedStatement ps = null; ResultSet rs = null; Pedido pedido = null;
	 * 
	 * try { con = ConexcionBDD.ObtenerConexion(); ps = con.
	 * prepareStatement("  select codigo_pro,pro.nombre as nombre_producto,udm.nombre as unidad_medida,"
	 * + "  udm.descripcion as descripcion_udm,cast(precio_venta as decimal (6,2)),"
	 * +
	 * "  tiene_iva, cast (coste as decimal (5,4)) , pro.categoria,cat.nombre as nombre_categoria,stock"
	 * + "	from productos pro, unidades_medidas udm, categorias cat" +
	 * "	where pro.unidad_medida = udm.nombre and pro.categoria = cat.codigo_cat"
	 * + "	and upper(pro.nombre) like ?;");
	 * 
	 * ps.setString(1, "%" + subcadena.toUpperCase() + "%"); rs = ps.executeQuery();
	 * while (rs.next()) { int codigo = rs.getInt("codigo_pro"); String
	 * nombreProducto = rs.getString("nombre_producto"); String unidadMedida =
	 * rs.getString("unidad_medida"); String descripcionUDM =
	 * rs.getString("descripcion_udm"); BigDecimal previoVenta =
	 * rs.getBigDecimal("coste"); boolean tieneIva = rs.getBoolean("tiene_iva");
	 * BigDecimal coste = rs.getBigDecimal("coste");
	 * 
	 * int codigoCategoria = rs.getInt("categoria"); String nombreCategoria =
	 * rs.getString("nombre_categoria"); int stock = rs.getInt("stock");
	 * 
	 * Categoria cat = new Categoria(); cat.setCodigo_cat(codigoCategoria);
	 * cat.setNombre(nombreCategoria);
	 * 
	 * UnidadDeMedida udm = new UnidadDeMedida(); udm.setNombre(unidadMedida);
	 * udm.setDescripcion(descripcionUDM);
	 * 
	 * producto = new Producto(); producto.setCodigo(codigo);
	 * producto.setNombre(nombreProducto); producto.setUnidadMedida(udm);
	 * producto.setPrecio(previoVenta); producto.setTieneIva(tieneIva);
	 * producto.setCoste(coste); producto.setCategoria(cat);
	 * producto.setStock(stock); productos.add(producto);
	 * 
	 * }
	 * 
	 * ; } catch (KrakedevEception e) { e.printStackTrace(); throw e; } catch
	 * (SQLException e) { e.printStackTrace(); throw new
	 * KrakedevEception("Error al consultar. Detalle: " + e.getMessage());
	 * 
	 * } return productos;
	 * 
	 * }
	 */
	// insertar proveedor
	public void insertar(Pedido pedido) throws KrakedevEception {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDetalle = null;

		// fecha del sitema
		Date fechaActual = new Date();
		// covierte fecha de sistem en mili segundos de tipo sql dare
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("INSERT INTO cabeceras_pedido (proveedor,fecha,estado)" + "VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pedido.getProveedor().getCodigo_prov());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();
			// obtiene el codigo de la cabecera
			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			// recupero todo los detalles del pedido
			ArrayList<DetallePedido> detallesPedidos = pedido.getDetalles();
			// barro los detalle y hago un insert

			DetallePedido det;
			for (int i = 0; i < detallesPedidos.size(); i++) {
				det = detallesPedidos.get(i);
				psDetalle = con.prepareStatement(
						"INSERT INTO detalles_pedido (cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida)"
								+ "VALUES (?,?,?,?,?)");
				psDetalle.setInt(1, codigoCabecera);
				psDetalle.setInt(2, det.getProducto().getCodigo());
				psDetalle.setInt(3, det.getCantidad());
				psDetalle.setInt(4, 0);
				BigDecimal pv = det.getProducto().getPrecio();
				BigDecimal cantidad = new BigDecimal(det.getCantidad());
				BigDecimal Subtotal = pv.multiply(cantidad);
				psDetalle.setBigDecimal(5, Subtotal);
				psDetalle.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al insertar el pedido Detalle: " + e.getMessage());
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

	// avtualizar pedido
	public void actualizar(Pedido pedido) throws KrakedevEception {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDetalle = null;
		PreparedStatement psHis = null;
		
		Date fechaActual = new Date ();
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		


		ResultSet rsClave = null;

		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("update  cabeceras_pedido set estado='R' where numero=?");

			ps.setInt(1, pedido.getCodigo());

			ps.executeUpdate();

			// recupero todo los detalles del pedido
			ArrayList<DetallePedido> detallesPedidos = pedido.getDetalles();

			DetallePedido det;
			for (int i = 0; i < detallesPedidos.size(); i++) {
				det = detallesPedidos.get(i);
				psDetalle = con
						.prepareStatement("UPDATE detalles_pedido SET cantidad_recibida=?, subtotal=? WHERE codigo=?");
				psDetalle.setInt(1, det.getCantidad_recibida());

				BigDecimal pv = det.getProducto().getPrecio();
				BigDecimal cantidad = new BigDecimal(det.getCantidad_recibida());
				BigDecimal Subtotal = pv.multiply(cantidad);
				psDetalle.setBigDecimal(2, Subtotal);
				psDetalle.setInt(3, det.getCodigo());
				

				psDetalle.executeUpdate();
				
				
				//
				psHis = con
						.prepareStatement("INSERT INTO histotial_stock (fecha,referencia,producto,cantidad)"
								+ "VALUES(?,?,?,?)");
				
				psHis.setTimestamp(1, fechaHoraActual);
				psHis.setString(2, "Pedido "+pedido.getCodigo());
				psHis.setInt(3, det.getProducto().getCodigo());
				psHis.setInt(4, det.getCantidad_recibida());
 
				psHis.executeUpdate();

				

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al actualizar el pedido Detalle: " + e.getMessage());
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

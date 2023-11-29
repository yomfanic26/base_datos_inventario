package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakedevEception;
import com.krakedev.inventarios.utils.ConexcionBDD;

public class ProductosBDD {

	// Recuperar todos los provedores

	public ArrayList<Producto> buscar(String subcadena) throws KrakedevEception {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto producto = null;

		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("  select codigo_pro,pro.nombre as nombre_producto,udm.nombre as unidad_medida,"
					+ "  udm.descripcion as descripcion_udm,cast(precio_venta as decimal (6,2)),"
					+ "  tiene_iva, cast (coste as decimal (5,4)) , pro.categoria,cat.nombre as nombre_categoria,stock"
					+ "	from productos pro, unidades_medidas udm, categorias cat"
					+ "	where pro.unidad_medida = udm.nombre and pro.categoria = cat.codigo_cat"
					+ "	and upper(pro.nombre) like ?;");

			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("codigo_pro");
				String nombreProducto = rs.getString("nombre_producto");
				String unidadMedida = rs.getString("unidad_medida");
				String descripcionUDM = rs.getString("descripcion_udm");
				BigDecimal previoVenta = rs.getBigDecimal("coste");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");

				int codigoCategoria = rs.getInt("categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				Categoria cat = new Categoria();
				cat.setCodigo_cat(codigoCategoria);
				cat.setNombre(nombreCategoria);

				UnidadDeMedida udm = new UnidadDeMedida();
				udm.setNombre(unidadMedida);
				udm.setDescripcion(descripcionUDM);

				producto = new Producto();
				producto.setCodigo(codigo);
				producto.setNombre(nombreProducto);
				producto.setUnidadMedida(udm);
				producto.setPrecio(previoVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(cat);
				producto.setStock(stock);
				productos.add(producto);

			}

			;
		} catch (KrakedevEception e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al consultar. Detalle: " + e.getMessage());

		}
		return productos;

	}

	// insertar proveedor
	public void insertar(Proveedor proveedor) throws KrakedevEception {
		Connection con = null;
		try {
			con = ConexcionBDD.ObtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO proveedores (codigo_prov,tipo_documento,nombre,telefono,correo,direccion) VALUES (?,?,?,?,?,?)");

			ps.setString(1, proveedor.getCodigo_prov());
			ps.setString(2, proveedor.getTipo_documento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al insertar el proveedor Detalle: " + e.getMessage());
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

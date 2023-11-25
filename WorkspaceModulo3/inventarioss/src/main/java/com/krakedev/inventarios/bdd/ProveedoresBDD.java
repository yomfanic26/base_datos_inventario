package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevEception;
import com.krakedev.inventarios.utils.ConexcionBDD;

public class ProveedoresBDD {

	// Recuperar todos

	public ArrayList<Proveedor> buscar(String subcadena) throws KrakedevEception {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;

		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement(
					"select codigo_prov,tipo_documento,td.descripcion,nombre,telefono,correo,direccion "
							+ "	from proveedores prov, tipos_documento td"
							+ "	where prov.tipo_documento = td.codigo and upper(nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo_prov");
				String codigoTipoDocumento = rs.getString("tipo_documento");
				String descriptionTipoDocumento = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				TipoDocumento td = new TipoDocumento(codigoTipoDocumento, descriptionTipoDocumento);

				proveedor = new Proveedor(codigo, td, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);

			}

			;
		} catch (KrakedevEception e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al consultar. Detalle: " + e.getMessage());

		}
		return proveedores;

	}
	
	//insertar proveedor
	public void insertar(Proveedor proveedor) throws KrakedevEception {
		Connection con = null;
		try {
			con = ConexcionBDD.ObtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO proveedores (codigo_prov,tipo_documento,nombre,telefono,correo,direccion) VALUES (?,?,?,?,?,?)");

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

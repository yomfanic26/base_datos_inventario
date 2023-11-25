package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevEception;
import com.krakedev.inventarios.utils.ConexcionBDD;

public class ProveedoresBDD {
	
	//Recuperar todos
	
	public ArrayList<Proveedor> buscar(String subcadena ) throws KrakedevEception {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;

		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("select codigo_prov,tipo_documento,nombre,telefono,correo,direccion "
					+ "from proveedores"
					+ "	where upper(nombre) like ?");
			ps.setString(1, "%"+subcadena.toUpperCase()+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo_prov");
				String tipo_documento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");

				proveedor = new Proveedor(codigo, tipo_documento, nombre,telefono,correo,direccion);
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

}

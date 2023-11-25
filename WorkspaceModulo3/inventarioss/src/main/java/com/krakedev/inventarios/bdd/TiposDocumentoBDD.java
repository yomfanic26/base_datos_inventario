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

public class TiposDocumentoBDD {
	
	//Recuperar todos
	
	public ArrayList<TipoDocumento> buscar( ) throws KrakedevEception {
		ArrayList<TipoDocumento> tiposDocumento = new ArrayList<TipoDocumento>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento tipoDocumento = null;

		try {
			con = ConexcionBDD.ObtenerConexion();
			ps = con.prepareStatement("select codigo,descripcion "
					+ "from tipos_documento");
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				tipoDocumento = new TipoDocumento(codigo, descripcion);
				tiposDocumento.add(tipoDocumento);

			}

			;
		} catch (KrakedevEception e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error al consultar. Detalle: " + e.getMessage());

		}
		return tiposDocumento;

	}

}

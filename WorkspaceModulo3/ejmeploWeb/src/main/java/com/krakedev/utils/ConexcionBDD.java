package com.krakedev.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.krakedev.execepciones.KrakedevEception;

public class ConexcionBDD {

	public static Connection ObtenerConexion() throws KrakedevEception {

		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		try {
			ctx = new InitialContext();
			// JDNI
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/conexcionPg");

			con = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			throw new KrakedevEception("Error new");
		}

		return con;

	}

}

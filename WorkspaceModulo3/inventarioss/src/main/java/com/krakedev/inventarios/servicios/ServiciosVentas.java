package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevEception;

@Path("ventas")
public class ServiciosVentas {

	// insertar venta
	@Path("registrar")
	@POST
	// recibir un venta detalle ente en formato json
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Venta venta) {
		System.out.println("<<<<<<<" + venta);
		VentasBDD vent = new VentasBDD();
		try {
			vent.insertar(venta);
			// devuelve 200
			return Response.ok().build();

		} catch (KrakedevEception e) {
			e.printStackTrace();
			// devuelve error 500
			return Response.serverError().build();
		}
	}

}

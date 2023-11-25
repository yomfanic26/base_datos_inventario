package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevEception;

@Path("proveedores")
public class ServiciosProveedores {

	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response buscar(@PathParam("sub") String subcadena) {

		ProveedoresBDD provBDD = new ProveedoresBDD();
		ArrayList<Proveedor> proveedores = null;
		;
		try {
			proveedores = provBDD.buscar(subcadena);
			return Response.ok(proveedores).build();

		} catch (KrakedevEception e) {
			e.printStackTrace();
			return Response.serverError().build();

		}

	}
	
	//insertar proveedor
	@Path("crear")
	@POST
	// recibir un cliente en formato json
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Proveedor proveedor) {
		System.out.println("<<<<<<<" + proveedor);
		ProveedoresBDD cli = new ProveedoresBDD();
		try {
			cli.insertar(proveedor);
			// devuelve 200
			return Response.ok().build();

		} catch (KrakedevEception e) {
			e.printStackTrace();
			// devuelve error 500
			return Response.serverError().build();
		}
	}


}

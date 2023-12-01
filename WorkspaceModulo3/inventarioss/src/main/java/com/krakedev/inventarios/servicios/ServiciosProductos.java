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

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevEception;

@Path("productos")
public class ServiciosProductos {

	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response buscar(@PathParam("sub") String subcadena) {

		ProductosBDD proBDD = new ProductosBDD();
		ArrayList<Producto> productos = null;
		;
		try {
			productos = proBDD.buscar(subcadena);
			return Response.ok(productos).build();

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
	public Response insertar(Producto producto) {
		System.out.println("<<<<<<<" + producto);
		ProductosBDD pro = new ProductosBDD();
		try {
			pro.insertar(producto);
			// devuelve 200
			return Response.ok().build();

		} catch (KrakedevEception e) {
			e.printStackTrace();
			// devuelve error 500
			return Response.serverError().build();
		}
	}


}

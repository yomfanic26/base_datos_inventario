package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.TiposDocumentoBDD;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevEception;

@Path("tiposdocumento")
public class ServiciosTiposDocumento {

	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response buscar(String subcadena) {

		TiposDocumentoBDD tiposDocBDD = new TiposDocumentoBDD();
		ArrayList<TipoDocumento> tiposDocumento = null;
		;
		try {
			tiposDocumento = tiposDocBDD.buscar();
			return Response.ok(tiposDocumento).build();

		} catch (KrakedevEception e) {
			e.printStackTrace();
			return Response.serverError().build();

		}

	}

}

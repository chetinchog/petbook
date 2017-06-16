package com.mascotas.inicio.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.inicio.InicioService;
import com.mascotas.inicio.dto.EstadoDTO;
import com.mascotas.mascotas.MascotaService;
import com.mascotas.mascotas.dto.MascotaDTO;

/**
 * Servicio REST de Acceso a las Mascotas de un usuario.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/inicio")
public class InicioController {
	@EJB
	InicioService inicioService;

	/**
	 * Busca las mactoas del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEstados(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			return Response.ok().entity(
					inicioService.findEstados()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Graba una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response newEstado(EstadoDTO estadoDTO) throws NamingException {
		try {
			inicioService.newEstado(estadoDTO);;
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}
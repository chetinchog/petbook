package com.mascotas.estadocivil.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.estadocivil.EstadoCivilService;

/**
 * Servicio REST de Acceso a estadocivil.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/estadocivil")
public class EstadoCivilController {
	@EJB
	EstadoCivilService estadoCivilService;

	/**
	 * Busca todos los EstadoCivil.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() throws NamingException {
		try {
			return Response.ok().entity(estadoCivilService.findAll()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Busca un EstadoCivil por su id.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("id") Integer id) throws NamingException {
		try {
			return Response.ok().entity(estadoCivilService.findById(id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}
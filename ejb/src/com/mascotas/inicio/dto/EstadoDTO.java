package com.mascotas.inicio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.application.utils.StringUtils;
import com.mascotas.inicio.entities.Estado;
import com.mascotas.mascotas.entities.Mascota;

/**
 * EstadosDTO
 * 
 * @author CTG
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String usuario;
	private String mascota;
	private String texto;
	private Long fecha;
	private String imagen;
	private String idMascota;
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMascota() {
		return mascota;
	}

	public void setMascota(String mascota) {
		this.mascota = mascota;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public String getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(String idMascota) {
		this.idMascota = idMascota;
	}

	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author CTG
	 *
	 */
	public static class Factory {
		public static EstadoDTO get(Estado estado) {
			EstadoDTO result = new EstadoDTO();
			result.id = estado.getId();
			result.usuario = estado.getUsuario();
			result.mascota = estado.getMascota();
			result.texto = estado.getTexto();
			result.fecha = estado.getFecha();
			result.imagen = estado.getImagen();
			result.id = estado.getId();
			result.idMascota = estado.getIdMascota(); 
			return result;
		}

		public static List<EstadoDTO> get(Collection<Estado> estado) {
			ArrayList<EstadoDTO> result = new ArrayList<EstadoDTO>();
			for (Estado p : estado) {
				result.add(get(p));
			}
			return result;
		}

	}

}

package com.mascotas.estadocivil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.estadocivil.entities.EstadoCivil;

/**
 * Estado Civil
 * 
 * @author nestor
 *
 */
public class EstadoCivilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	
	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static EstadoCivilDTO get(EstadoCivil estadoCivil) {
			EstadoCivilDTO result = new EstadoCivilDTO();
			result.id = estadoCivil.getId();
			result.nombre = estadoCivil.getNombre();
			return result;
		}

		public static List<EstadoCivilDTO> get(Collection<EstadoCivil> estadosCiviles) {
			ArrayList<EstadoCivilDTO> result = new ArrayList<EstadoCivilDTO>();
			for (EstadoCivil p : estadosCiviles) {
				result.add(get(p));
			}
			return result;
		}

	}

}

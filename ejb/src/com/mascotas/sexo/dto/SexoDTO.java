package com.mascotas.sexo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.sexo.entities.Sexo;

/**
 * Sexo
 * 
 * @author nestor
 *
 */
public class SexoDTO implements Serializable {
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
		public static SexoDTO get(Sexo sexo) {
			SexoDTO result = new SexoDTO();
			result.id = sexo.getId();
			result.nombre = sexo.getNombre();
			return result;
		}

		public static List<SexoDTO> get(Collection<Sexo> sexos) {
			ArrayList<SexoDTO> result = new ArrayList<SexoDTO>();
			for (Sexo p : sexos) {
				result.add(get(p));
			}
			return result;
		}

	}

}

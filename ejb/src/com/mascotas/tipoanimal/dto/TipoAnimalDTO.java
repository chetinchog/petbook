package com.mascotas.tipoanimal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.tipoanimal.entities.TipoAnimal;

/**
 * tipoanimal
 * 
 * @author nestor
 *
 */
public class TipoAnimalDTO implements Serializable {
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
		public static TipoAnimalDTO get(TipoAnimal tipoAnimal) {
			TipoAnimalDTO result = new TipoAnimalDTO();
			result.id = tipoAnimal.getId();
			result.nombre = tipoAnimal.getNombre();
			return result;
		}

		public static List<TipoAnimalDTO> get(Collection<TipoAnimal> tiposAnimales) {
			ArrayList<TipoAnimalDTO> result = new ArrayList<TipoAnimalDTO>();
			for (TipoAnimal p : tiposAnimales) {
				result.add(get(p));
			}
			return result;
		}

	}

}

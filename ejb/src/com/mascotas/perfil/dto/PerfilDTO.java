package com.mascotas.perfil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.perfil.entities.Perfil;

/**
 * Perfil de usuario
 * 
 * @author nestor
 *
 */
public class PerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String apellido;
	private String provincia;
	private String email;
	private String direccion;
	private String telefono;
	private Integer estadoCivil;
	private Integer sexo;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getSexo() {
		return sexo;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}
 
	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static PerfilDTO get(Perfil perfil) {
			PerfilDTO result = new PerfilDTO();
			result.id = perfil.getId();
			result.nombre = perfil.getNombre();
			result.telefono = perfil.getTelefono();
			result.email = perfil.getEmail();
			result.provincia = perfil.getProvincia().getNombre();
			result.direccion = perfil.getDireccion();
			return result;
		}

		public static List<PerfilDTO> get(Collection<Perfil> perfiles) {
			ArrayList<PerfilDTO> result = new ArrayList<PerfilDTO>();
			for (Perfil p : perfiles) {
				result.add(get(p));
			}
			return result;
		}

	}

}

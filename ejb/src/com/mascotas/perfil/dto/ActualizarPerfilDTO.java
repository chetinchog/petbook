package com.mascotas.perfil.dto;

import com.mascotas.perfil.entities.Perfil;

public class ActualizarPerfilDTO {
	private Integer id;
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;
	private Integer provincia;
	public String apellido;
	public Integer sexo;
	public Integer estadoCivil;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}



	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static ActualizarPerfilDTO get(Perfil perfil) {
			ActualizarPerfilDTO result = new ActualizarPerfilDTO();
			result.id = perfil.getId();
			result.nombre = perfil.getNombre();
			result.apellido = perfil.getApellido();
			if(perfil.getSexo() != null && perfil.getSexo().getId() > 0) {
				result.sexo = perfil.getSexo().getId();
			}
			if(perfil.getEstadoCivil() != null && perfil.getEstadoCivil().getId() > 0) {
				result.estadoCivil = perfil.getEstadoCivil().getId();
			}
			result.telefono = perfil.getTelefono();
			result.email = perfil.getEmail();
			result.direccion = perfil.getDireccion();
			if (perfil.getProvincia() != null && perfil.getProvincia().getId() > 0) {
				result.provincia = perfil.getProvincia().getId();
			}
			return result;
		}
	}
}


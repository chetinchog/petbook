package com.mascotas.mascotas.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.application.utils.StringUtils;
import com.mascotas.mascotas.entities.Mascota;

/**
 * Mascotas
 * 
 * @author nestor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MascotaNombreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String fechaNacimiento;
	private String descripcion;
	private String textoPerdido;
	private String sexo;
	private String tipoAnimal;

	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public String getTextoPerdido() {
		return textoPerdido;
	}

	public void setTextoPerdido(String textoPerdido) {
		this.textoPerdido = textoPerdido;
	}

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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static MascotaNombreDTO get(Mascota mascota) {
			MascotaNombreDTO result = new MascotaNombreDTO();
			result.id = mascota.getId();
			result.nombre = mascota.getNombre();
			if (mascota.getFechaNacimiento() != null) {
				result.fechaNacimiento = StringUtils.DATE_FORMAT.format(mascota.getFechaNacimiento());
			}
			result.descripcion = mascota.getDescripcion();
			result.textoPerdido = mascota.getTextoPerdido();
			result.sexo = mascota.getSexo().getNombre();
			result.tipoAnimal = mascota.getTipoAnimal().getNombre();
			return result;
		}

		public static List<MascotaNombreDTO> get(Collection<Mascota> mascotas) {
			ArrayList<MascotaNombreDTO> result = new ArrayList<MascotaNombreDTO>();
			for (Mascota p : mascotas) {
				result.add(get(p));
			}
			return result;
		}

	}

}

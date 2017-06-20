package com.mascotas.inicio.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mascotas.seguridad.entities.Usuario;

/**
 * Estados
 * 
 * @author CTG
 *
 */
@Entity(name = "estado")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OrderColumn()
	@Column(nullable = false)
	private String usuario;

	@OrderColumn()
	@Column(nullable = false)
	private String mascota;

	public String getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(String idMascota) {
		this.idMascota = idMascota;
	}

	@OrderColumn()
	@Column(nullable = false)
	private String idMascota;

	@OrderColumn()
	private String texto;

	@OrderColumn()
	@Column(nullable = false)
	private Long fecha;
	
	@OrderColumn()
	private String imagen;

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
}

package com.mascotas.mascotas.entities;

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
import com.mascotas.sexo.entities.Sexo;
import com.mascotas.tipoanimal.entities.TipoAnimal;

/**
 * Mascotas
 * 
 * @author nestor
 *
 */
@Entity(name = "mascotas")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OrderColumn()
	@Column(nullable = false)
	private String nombre;

	@Temporal(TemporalType.DATE)
	private java.util.Date fechaNacimiento;

	private String descripcion;
	private String textoPerdido;

	public String getTextoPerdido() {
		return textoPerdido;
	}

	public void setTextoPerdido(String textoPerdido) {
		this.textoPerdido = textoPerdido;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private TipoAnimal tipoAnimal;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Sexo sexo;

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
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

	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

}

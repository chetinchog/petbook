package com.mascotas.perfil.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mascotas.estadocivil.entities.EstadoCivil;
import com.mascotas.provincias.entites.Provincia;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.sexo.entities.Sexo;

/**
 * Perfil de usuario
 * 
 * @author nestor
 *
 */
@Entity(name = "perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String direccion;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "login", unique = true, nullable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Provincia provincia;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Sexo sexo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private EstadoCivil estadoCivil;

	public Integer getId() { 
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}	

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

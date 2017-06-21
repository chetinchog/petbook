package com.mascotas.estadocivil.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.perfil.entities.Perfil;
import com.mascotas.provincias.entites.Provincia;
import com.mascotas.estadocivil.entities.EstadoCivil;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class EstadoCivilRepository implements Repositorio<String, EstadoCivil> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(EstadoCivil newOne) {
		throw new RuntimeException("No se puede agregar EstadoCivil.");
	}

	@Override
	public void remove(EstadoCivil toDelete) {
		throw new RuntimeException("No se puede eliminar EstadoCivil.");
	}

	@Override
	public EstadoCivil get(String nombre) {
		return entityManager.find(EstadoCivil.class, nombre);
	}

	@Override
	public List<EstadoCivil> getAll() {
		String q = "SELECT p from " + EstadoCivil.class.getName() + " p ";
		TypedQuery<EstadoCivil> query = entityManager.createQuery(q, EstadoCivil.class);

		List<EstadoCivil> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<EstadoCivil>();
		}
		return result;
	}

	public EstadoCivil getEstadoCivil(Integer id) {
		String q = "SELECT p from " + EstadoCivil.class.getName() + " p WHERE p.id = :id";
		TypedQuery<EstadoCivil> query = entityManager.createQuery(q, EstadoCivil.class);
		query.setParameter("id", id);
		List<EstadoCivil> result = query.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public long size() {
		String q = "SELECT count(p) from " + EstadoCivil.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public EstadoCivil get(Integer id) {
		return entityManager.find(EstadoCivil.class, id);
	}

	
}

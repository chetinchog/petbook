package com.mascotas.tipoanimal.repository;

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
import com.mascotas.tipoanimal.entities.TipoAnimal;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class TipoAnimalRepository implements Repositorio<String, TipoAnimal> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(TipoAnimal newOne) {
		throw new RuntimeException("No se puede agregar TipoAnimal.");
	}

	@Override
	public void remove(TipoAnimal toDelete) {
		throw new RuntimeException("No se puede eliminar TipoAnimal.");
	}

	@Override
	public TipoAnimal get(String nombre) {
		return entityManager.find(TipoAnimal.class, nombre);
	}

	@Override
	public List<TipoAnimal> getAll() {
		String q = "SELECT p from " + TipoAnimal.class.getName() + " p ";
		TypedQuery<TipoAnimal> query = entityManager.createQuery(q, TipoAnimal.class);

		List<TipoAnimal> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<TipoAnimal>();
		}
		return result;
	}

	public TipoAnimal getTipoAnimal(Integer id) {
		String q = "SELECT p from " + TipoAnimal.class.getName() + " p WHERE p.id = :id";
		TypedQuery<TipoAnimal> query = entityManager.createQuery(q, TipoAnimal.class);
		query.setParameter("id", id);
		List<TipoAnimal> result = query.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public long size() {
		String q = "SELECT count(p) from " + TipoAnimal.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public TipoAnimal get(Integer id) {
		return entityManager.find(TipoAnimal.class, id);
	}

	
}
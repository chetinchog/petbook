package com.mascotas.sexo.repository;

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
import com.mascotas.sexo.entities.Sexo;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class SexoRepository implements Repositorio<String, Sexo> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Sexo newOne) {
		throw new RuntimeException("No se puede agregar sexos.");
	}

	@Override
	public void remove(Sexo toDelete) {
		throw new RuntimeException("No se puede eliminar sexos.");
	}

	@Override
	public Sexo get(String nombre) {
		return entityManager.find(Sexo.class, nombre);
	}

	@Override
	public List<Sexo> getAll() {
		String q = "SELECT p from " + Sexo.class.getName() + " p ";
		TypedQuery<Sexo> query = entityManager.createQuery(q, Sexo.class);

		List<Sexo> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Sexo>();
		}
		return result;
	}

	public Sexo getSexo(Integer id) {
		String q = "SELECT p from " + Sexo.class.getName() + " p WHERE p.id = :id";
		TypedQuery<Sexo> query = entityManager.createQuery(q, Sexo.class);
		query.setParameter("id", id);
		List<Sexo> result = query.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public long size() {
		String q = "SELECT count(p) from " + Sexo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public Sexo get(Integer id) {
		return entityManager.find(Sexo.class, id);
	}

	
}

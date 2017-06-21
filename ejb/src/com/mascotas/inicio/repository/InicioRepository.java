package com.mascotas.inicio.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.inicio.entities.Estado;
import com.mascotas.mascotas.entities.Mascota;

/**
 * Repositorio de Almacenamiento de Estados.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class InicioRepository implements Repositorio<Integer, Estado> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Estado newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Estado toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Estado get(Integer id) {
		return entityManager.find(Estado.class, id);
	}
	
	public Estado get(Long id) {
		return entityManager.find(Estado.class, id);
	}

	@Override
	public List<Estado> getAll() {
		throw new RuntimeException("No se puede acceder a todos los estados.");
	}

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los estados.");
	}

	public List<Estado> getEstados() {
		String q = "SELECT p from " + Estado.class.getName() +
				" p ORDER BY fecha DESC";
		TypedQuery<Estado> query = entityManager.createQuery(q, Estado.class);
		return query.getResultList();
	}

	public Estado getEstado(Long fecha) {
		String q = "SELECT e from " + Estado.class.getName() +
				" e WHERE  e.fecha = :fecha ";
		TypedQuery<Estado> query = entityManager.createQuery(q, Estado.class);
		query.setParameter("fecha", fecha);
		return query.getSingleResult();
	}

	public List<Estado> getLastEstados(Long fecha) {
		String q = "SELECT e from " + Estado.class.getName() +
				" e WHERE e.fecha > :fecha ORDER BY e.fecha DESC";
		TypedQuery<Estado> query = entityManager.createQuery(q, Estado.class);
		query.setParameter("fecha", fecha);
		return query.getResultList();
	}
}

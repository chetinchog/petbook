package com.mascotas.estadocivil;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.estadocivil.dto.EstadoCivilDTO;
import com.mascotas.estadocivil.repository.EstadoCivilRepository;

/**
 * Servicio del negocio de estadocivil.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EstadoCivilService {

	@EJB
	private EstadoCivilRepository estadoCivilRepository;

	/**
	 * Busca un estadocivil por su id.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public EstadoCivilDTO findById(Integer id) {
		return EstadoCivilDTO.Factory.get(estadoCivilRepository.get(id));
	}

	/**
	 * Retorna todas los EstadoCivil del sistema.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<EstadoCivilDTO> findAll() {
		return EstadoCivilDTO.Factory.get(estadoCivilRepository.getAll());
	}

}
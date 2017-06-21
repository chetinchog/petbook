package com.mascotas.sexo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.sexo.dto.SexoDTO;
import com.mascotas.sexo.repository.SexoRepository;

/**
 * Servicio del negocio de Sexo.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SexoService {

	@EJB
	private SexoRepository sexoRepository;

	/**
	 * Busca un sexo por su id.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public SexoDTO findById(Integer id) {
		return SexoDTO.Factory.get(sexoRepository.get(id));
	}

	/**
	 * Retorna todas los sexos del sistema.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<SexoDTO> findAll() {
		return SexoDTO.Factory.get(sexoRepository.getAll());
	}

}
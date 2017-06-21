package com.mascotas.tipoanimal;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.tipoanimal.dto.TipoAnimalDTO;
import com.mascotas.tipoanimal.repository.TipoAnimalRepository;

/**
 * Servicio del negocio de TipoAnimal.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TipoAnimalService {

	@EJB
	private TipoAnimalRepository tipoAnimalRepository;

	/**
	 * Busca un TipoAnimal por su id.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public TipoAnimalDTO findById(Integer id) {
		return TipoAnimalDTO.Factory.get(tipoAnimalRepository.get(id));
	}

	/**
	 * Retorna todas los TipoAnimal del sistema.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<TipoAnimalDTO> findAll() {
		return TipoAnimalDTO.Factory.get(tipoAnimalRepository.getAll());
	}

}
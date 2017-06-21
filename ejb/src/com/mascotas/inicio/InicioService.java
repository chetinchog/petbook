package com.mascotas.inicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.PathParam;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.utils.StringUtils;
import com.mascotas.inicio.dto.EstadoDTO;
import com.mascotas.inicio.entities.Estado;
import com.mascotas.inicio.repository.InicioRepository;
import com.mascotas.mascotas.dto.MascotaDTO;
import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

/**
 * Servicios de acceso a los Estados
 * 
 * @author CTG
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InicioService {
	@EJB
	private UsuariosRepository usuarioRepository;

	@EJB
	private InicioRepository inicioRepository;

	public List<EstadoDTO> findEstados() throws BusinessException {

		List<Estado> estado = inicioRepository.getEstados();

		return EstadoDTO.Factory.get(estado);
	}
	
	/**
	 * Crea nuevo Estado.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void newEstado(EstadoDTO estado) throws BusinessException {
		Estado newEstado = new Estado();
		newEstado.setFecha(estado.getFecha());
		newEstado.setMascota(estado.getMascota());
		newEstado.setTexto(estado.getTexto());
		newEstado.setUsuario(estado.getUsuario());
		newEstado.setImagen(estado.getImagen());
		newEstado.setIdMascota(estado.getIdMascota());
		inicioRepository.add(newEstado);
	}

	public void eliminarMascota(Long id) {
		inicioRepository.remove(inicioRepository.get(id));
	}

	public List<EstadoDTO> findLastEstados(Long fecha) throws BusinessException {

		List<Estado> estado = inicioRepository.getLastEstados(fecha);

		return EstadoDTO.Factory.get(estado);
	}
}

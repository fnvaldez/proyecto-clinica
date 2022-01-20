package ar.edu.unju.fi.poo.clinica.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.dto.MedicoDTO;
import ar.edu.unju.fi.poo.clinica.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;
import ar.edu.unju.fi.poo.clinica.entity.Medico;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.repository.MedicoRepository;
import ar.edu.unju.fi.poo.clinica.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.clinica.service.MedicoService;
import ar.edu.unju.fi.poo.clinica.util.Mapper;

@Service
public class MedicoServiceImp implements MedicoService {

	final static Logger logger = Logger.getLogger(MedicoServiceImp.class);
	
	private Mapper mapper = new Mapper();
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ObraSocialRepository obraSocialRepository;
	
	
	/**
	 * El método altaMedico realizará el  alta del Medico.
	 */
	@Override
	public void altaMedico(MedicoDTO medicoDTO) throws ModelException {
		
		if(medicoDTO.getObrasSocialesDTO().size() == 0) {
			logger.error("El medico no tiene obras sociales");
			throw new ModelException("El medico no tiene obras sociales");
		}
		if(validarObras(medicoDTO.getObrasSocialesDTO())==false) {
			logger.error("Obra social invalida");
			throw new ModelException("Obra social invalida");
		}
		medicoRepository.save(mapper.map(medicoDTO, Medico.class));
		logger.info("Se registró un médico");	
	}
	
	/**
	 * Este método verifica que las obras sociales del médico sean válidas
	 * @param obrasSociales
	 * @return boolean
	 */
	private boolean validarObras(List<ObraSocialDTO> obrasSociales) {
		for (ObraSocialDTO o : obrasSociales) {
			if(obraSocialRepository.findById(o.getId()).isPresent()==false)
				return false;
		}
		return true;
	}

	/**
	 * El método buscarMedicoPorMatriculaONombre(String,Integer), nos retorna  los medicos buscados 
	 * por  parametros(nombre,matricua).
	 */
	@Override
	public List<MedicoDTO> buscarMedicoPorMatriculaONombre(String nombre, Integer matricula) {
	
		List<MedicoDTO> listMedicoEncontradoDTO = new ArrayList<MedicoDTO>();
		for (Medico  m : medicoRepository.findByNombreOrMatricula(nombre, matricula) ) {
			MedicoDTO medicoDTO = new MedicoDTO();
			medicoDTO = mapper.map(m,MedicoDTO.class);
			
			medicoDTO.setObrasSociales(mapper.mapAll(m.getObrasSociales(), ObraSocialDTO.class));
			medicoDTO.setTurnosDTO(mapper.mapAll(m.getTurnos(), TurnoDTO.class));
			listMedicoEncontradoDTO.add(medicoDTO);
		}
		return listMedicoEncontradoDTO ;
	}

	/**
	 * El método getAllMedicos() ,nos retorna la lista de medico.
	 */
	@Override
	public List<MedicoDTO> getAllMedicos() {
		
		List<MedicoDTO> listMedicoDTO = new ArrayList<MedicoDTO>();
		for (Medico  m : medicoRepository.findAll() ) {
			MedicoDTO medicoDTO = new MedicoDTO();
			medicoDTO = mapper.map(m,MedicoDTO.class);
			
			medicoDTO.setObrasSociales(mapper.mapAll(m.getObrasSociales(), ObraSocialDTO.class));
			medicoDTO.setTurnosDTO(mapper.mapAll(m.getTurnos(), TurnoDTO.class));
			listMedicoDTO.add(medicoDTO);
		}
		return listMedicoDTO ;
	}
	
	/**
	 * El método buscarMedicoPorId(Long) , devuelve el medico buscado por id .
	 */
	@Override
	public MedicoDTO buscarMedicoPorId(Long id) {
		
		MedicoDTO medicoDTO = new MedicoDTO();
		Optional<Medico> medicoEncontrado = medicoRepository.findById(id);
		
		if(medicoEncontrado.isPresent()) {
			medicoDTO = mapper.map(medicoEncontrado.get(),MedicoDTO.class);
			
			medicoDTO.setObrasSociales(mapper.mapAll(medicoEncontrado.get().getObrasSociales(), ObraSocialDTO.class));
			medicoDTO.setTurnosDTO(mapper.mapAll(medicoEncontrado.get().getTurnos(), TurnoDTO.class));
			
		} else {
			logger.error("Medico no encontrado");
			throw new ModelException("Medico no encontrado");
		}
		return medicoDTO;
	}
	
	/**
	 * El método countMedicos(), nos retorna la cantidad de médicos
	 */
	@Override
	public long countMedicos() {
		return medicoRepository.count();
	}
}

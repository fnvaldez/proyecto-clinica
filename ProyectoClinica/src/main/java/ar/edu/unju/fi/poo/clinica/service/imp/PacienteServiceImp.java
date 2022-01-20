package ar.edu.unju.fi.poo.clinica.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.dto.PacienteDTO;
import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;
import ar.edu.unju.fi.poo.clinica.entity.ObraSocial;
import ar.edu.unju.fi.poo.clinica.entity.Paciente;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.manager.PacienteManager;
import ar.edu.unju.fi.poo.clinica.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.clinica.repository.PacienteRepository;
import ar.edu.unju.fi.poo.clinica.service.PacienteService;
import ar.edu.unju.fi.poo.clinica.util.Mapper;

@Service
public class PacienteServiceImp implements PacienteService {

	final static Logger logger = Logger.getLogger(PacienteManager.class);
	
	private Mapper mapper = new Mapper();
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ObraSocialRepository obraSocialRepository;
	
	/**
	 * El método registrarPaciente(PacienteDTO), registra al paciente.
	 */
	@Override
	public void registrarPaciente(PacienteDTO pacienteDTO)throws ModelException {
		
		if(pacienteDTO.getObraSocialId() != null &&  obraSocialRepository.findById(pacienteDTO.getObraSocialId()).isPresent()) {
			
			ObraSocial obraSocialEncontrada = obraSocialRepository.findById(pacienteDTO.getObraSocialId()).get();
			
			Paciente paciente = new Paciente();
			
			paciente = mapper.map(pacienteDTO, Paciente.class);
			paciente.setObraSocial(obraSocialEncontrada);
			pacienteRepository.save(paciente);
			
			logger.info("Se registro un nuevo paciente");
			
		} else {
			logger.error("Obra social invalida");
			throw new ModelException("Obra Social invalida");
		}
		
	}

	/**
	 *El método buscarPacientesPorNombre(String), retorna las lista de paciente 
	 *que son buscado por una cadena.
	 */
	@Override
	public List<PacienteDTO> buscarPacientesPorNombre(String cadena) {
		
		List<PacienteDTO> listPacienteDTO = new ArrayList<PacienteDTO>();
		for (Paciente  p : pacienteRepository.findByNombreContaining(cadena)) {
			
			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO = mapper.map(p,PacienteDTO.class);
			pacienteDTO.setTurnosDTO(mapper.mapAll(p.getTurnos(), TurnoDTO.class));
			
			listPacienteDTO.add(pacienteDTO);
		}
		return listPacienteDTO;
	}
	
	
	/**
	 * El método getAllPacientes(), retorna una lista de todos pacientes.
	 */
	@Override
	public List<PacienteDTO> getAllPacientes() {
		
		List<PacienteDTO> listPacienteDTO = new ArrayList<PacienteDTO>();
		for (Paciente  p : pacienteRepository.findAll()) {
			
			PacienteDTO pacienteDTO = new PacienteDTO();
			pacienteDTO = mapper.map(p,PacienteDTO.class);
			pacienteDTO.setTurnosDTO(mapper.mapAll(p.getTurnos(), TurnoDTO.class));
			
			listPacienteDTO.add(pacienteDTO);
		}
		return listPacienteDTO ;
		
	}

	/**
	 * El método buscarPacientePorId(Long id), retorna un paciente buscado por id.
	 */
	@Override
	public PacienteDTO buscarPacientePorId(Long id) throws ModelException {
		
		PacienteDTO pacienteDTO = new PacienteDTO();
		Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
		
		if(pacienteEncontrado.isPresent()) {
			
			pacienteDTO = mapper.map(pacienteEncontrado.get(), PacienteDTO.class);
			pacienteDTO.setTurnosDTO(mapper.mapAll(pacienteEncontrado.get().getTurnos(), TurnoDTO.class));
			
		} else {
			logger.error("Paciente no encontrado");
			throw new ModelException("Paciente no encontrado");
		}
		return pacienteDTO;
	}
	
	/**
	 * El método buscarPacientePorDNI(), retorna  paciente buscado por dni.
	 */
	@Override
	public PacienteDTO buscarPacientePorDNI(Integer dni) {
		
		PacienteDTO pacienteDTO = new PacienteDTO();
		Paciente pacienteEncontrado = pacienteRepository.findByDni(dni);
		if(pacienteEncontrado != null) {
			
			pacienteDTO = mapper.map(pacienteEncontrado, PacienteDTO.class);
			pacienteDTO.setTurnosDTO(mapper.mapAll(pacienteEncontrado.getTurnos(), TurnoDTO.class));
			
		} else {
			pacienteDTO = null;
		}
		return pacienteDTO;
	}

	/**
	 * El método countPacientes(), retorna la cantidad del paciente.
	 */
	@Override
	public long countPacientes() {
		return pacienteRepository.count();
	}
}

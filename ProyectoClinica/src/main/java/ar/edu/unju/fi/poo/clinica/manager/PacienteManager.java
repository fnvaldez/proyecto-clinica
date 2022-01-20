package ar.edu.unju.fi.poo.clinica.manager;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.entity.Paciente;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.repository.PacienteRepository;

@Service
public class PacienteManager {

	final static Logger logger = Logger.getLogger(PacienteManager.class);
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	public void registrarPaciente(Paciente paciente) throws ModelException{
		
		if(paciente.getObraSocial() == null) {
			logger.error("Obra social invalida");
			throw new ModelException("Obra Social invalida");
		}
		pacienteRepository.save(paciente);
		logger.info("Se registro un nuevo paciente");
		
	}
	
	public List<Paciente> buscarPacientesPorNombre(String cadena) {
		return pacienteRepository.findByNombreContaining(cadena);
	}
	
	public Paciente buscarPacientePorId(Long id){
		Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
		return pacienteEncontrado.isPresent() ? pacienteEncontrado.get() : null;
	}
	
	public List<Paciente> getPacientes(){
		return (List<Paciente>) pacienteRepository.findAll();
	}
	
	public int countPacientes() {
		return (int)pacienteRepository.count();
	}
}

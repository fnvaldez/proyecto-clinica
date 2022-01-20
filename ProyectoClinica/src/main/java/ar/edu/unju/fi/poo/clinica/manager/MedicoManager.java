package ar.edu.unju.fi.poo.clinica.manager;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.entity.Medico;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.repository.MedicoRepository;

@Service
public class MedicoManager {

	final static Logger logger = Logger.getLogger(MedicoManager.class);
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public void altaMedico(Medico medico) throws ModelException {
		
		if(medico.getObrasSociales().size() == 0) {
			logger.error("El medico no tiene obras sociales");
			throw new ModelException("El medico no tiene obras sociales");
		}
		medicoRepository.save(medico);
		logger.info("Se regitró un médico");
		
	}
	
	public List<Medico> buscarMedicoPorMatriculaONombre(String nombre, Integer matricula) {
			
		return medicoRepository.findByNombreOrMatricula(nombre, matricula);
	}
	
	public List<Medico> getMedicos(){
		return (List<Medico>) medicoRepository.findAll();
	}
	
	public int countMedicos() {
		return (int) medicoRepository.count();
	}
	
	public Medico buscarMedicoPorId(Long id){
		Optional<Medico> medicoBuscado = medicoRepository.findById(id);
		return medicoBuscado.isPresent() ? medicoBuscado.get() : null;
	}
}

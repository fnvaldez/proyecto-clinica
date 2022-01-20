package ar.edu.unju.fi.poo.clinica.manager;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.entity.ObraSocial;
import ar.edu.unju.fi.poo.clinica.repository.ObraSocialRepository;

@Service
public class ObraSocialManager {

	@Autowired
	ObraSocialRepository obraSocialRepository;
	
	public ObraSocial buscarObraSocialPorId(Long id){
		Optional<ObraSocial> obraSocial = obraSocialRepository.findById(id);
		return obraSocial.isPresent() ? obraSocial.get() : null;
	}
}

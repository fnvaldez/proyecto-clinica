package ar.edu.unju.fi.poo.clinica.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.clinica.entity.ObraSocial;
import ar.edu.unju.fi.poo.clinica.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.clinica.service.ObraSocialService;
import ar.edu.unju.fi.poo.clinica.util.Mapper;

@Service
public class ObraSocialServiceImp implements ObraSocialService {

	@Autowired
	private ObraSocialRepository obraSocialRepository;
	
	private Mapper mapper = new Mapper();
	
	/**
	 * El metodo buscarObraSocialPorId(Long), nos retorna la obra social buscada por id.
	 */
	@Override
	public ObraSocialDTO buscarObraSocialPorId(Long id) {
		
		ObraSocialDTO obraSocialDTO = new ObraSocialDTO();
		Optional<ObraSocial> obraSocialEncontrada = obraSocialRepository.findById(id);
		if(obraSocialEncontrada.isPresent()) {
			obraSocialDTO = mapper.map(obraSocialEncontrada.get(),ObraSocialDTO.class);
		} else {
			obraSocialDTO = null;
		}
		return obraSocialDTO;
	}
	
	/*
	 * El método getAllObrasSociales(), nos retorna la lista de la obras sociales 
	 */

	@Override
	public List<ObraSocialDTO> getAllObrasSociales() {
		
		List<ObraSocialDTO> listaObraSocialDTO = new ArrayList<ObraSocialDTO>();
		for (ObraSocial  os : obraSocialRepository.findAll() ) {
			
			listaObraSocialDTO.add(mapper.map(os, ObraSocialDTO.class));
		}
		return listaObraSocialDTO;
	}
}

package ar.edu.unju.fi.poo.clinica.service;

import java.util.List;

import ar.edu.unju.fi.poo.clinica.dto.ObraSocialDTO;

public interface ObraSocialService {

	public ObraSocialDTO buscarObraSocialPorId(Long id);
	public List<ObraSocialDTO> getAllObrasSociales();
}

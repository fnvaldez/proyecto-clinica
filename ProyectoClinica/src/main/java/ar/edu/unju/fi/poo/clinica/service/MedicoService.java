package ar.edu.unju.fi.poo.clinica.service;

import java.util.List;

import ar.edu.unju.fi.poo.clinica.dto.MedicoDTO;

public interface MedicoService {

	public void altaMedico(MedicoDTO medicoDTO);
	public List<MedicoDTO> buscarMedicoPorMatriculaONombre(String nombre, Integer matricula);
	public List<MedicoDTO> getAllMedicos();
	public MedicoDTO buscarMedicoPorId(Long id);
	public long countMedicos();
	
}

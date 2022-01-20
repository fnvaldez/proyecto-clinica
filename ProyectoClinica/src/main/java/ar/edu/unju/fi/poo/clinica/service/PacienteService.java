package ar.edu.unju.fi.poo.clinica.service;

import java.util.List;

import ar.edu.unju.fi.poo.clinica.dto.PacienteDTO;

public interface PacienteService {

	public void registrarPaciente(PacienteDTO pacienteDTO);
	public List<PacienteDTO> buscarPacientesPorNombre(String cadena);
	public List<PacienteDTO> getAllPacientes();
	public PacienteDTO buscarPacientePorId(Long id);
	public PacienteDTO buscarPacientePorDNI(Integer dni);
	public long countPacientes();
	
}

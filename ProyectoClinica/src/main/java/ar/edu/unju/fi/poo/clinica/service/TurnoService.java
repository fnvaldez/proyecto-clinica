package ar.edu.unju.fi.poo.clinica.service;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;

public interface TurnoService {

	public void registarTurno(TurnoDTO turnoDTO);
	public List<TurnoDTO> getAllTurnos();
	public TurnoDTO buscarTurnoPorId(Long id);
	public void retrasarTurnos(Integer dni, Long turnoId, int retraso);
	public LocalDateTime consultarHorarioDeAtencionPorDNI(Integer dni, Long turnoId);
	public String calcularTiempoRestanteParaTurno(Integer dni, Long turnoId);
	
}

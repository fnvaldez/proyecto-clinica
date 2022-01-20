package ar.edu.unju.fi.poo.clinica.dto;

import java.time.LocalDateTime;

public class TurnoDTO {

	private Long id;
	
    private Long pacienteId;
	
	private Long medicoId;
	
	private LocalDateTime fechaHoraInicio;
		
	private LocalDateTime fechaHoraFin;

	
	public TurnoDTO() {
		
	}
	
	public TurnoDTO(Long pacienteId, Long medicoId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
		super();
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
}

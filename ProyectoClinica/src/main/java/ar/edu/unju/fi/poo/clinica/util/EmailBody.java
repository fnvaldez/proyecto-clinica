package ar.edu.unju.fi.poo.clinica.util;

import java.time.LocalDateTime;

public class EmailBody {

	private String nombrePaciente;
	private String nombreMedico;
	private String mailPaciente;
	private String mailMedico;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;	
	private String subject;
	
	public EmailBody(String nombrePaciente, String nombreMedico, String mailPaciente, String mailMedico,
			LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String subject) {
		super();
		this.nombrePaciente = nombrePaciente;
		this.nombreMedico = nombreMedico;
		this.mailPaciente = mailPaciente;
		this.mailMedico = mailMedico;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.subject = subject;
	}
	
	public EmailBody(String nombrePaciente, String nombreMedico, String mailPaciente,
			LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String subject) {
		super();
		this.nombrePaciente = nombrePaciente;
		this.nombreMedico = nombreMedico;
		this.mailPaciente = mailPaciente;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.subject = subject;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getMailPaciente() {
		return mailPaciente;
	}

	public void setMailPaciente(String mailPaciente) {
		this.mailPaciente = mailPaciente;
	}

	public String getMailMedico() {
		return mailMedico;
	}

	public void setMailMedico(String mailMedico) {
		this.mailMedico = mailMedico;
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
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}

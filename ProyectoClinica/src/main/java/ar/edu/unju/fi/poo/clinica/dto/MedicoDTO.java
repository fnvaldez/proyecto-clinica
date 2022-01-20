package ar.edu.unju.fi.poo.clinica.dto;

import java.util.List;

public class MedicoDTO {

private Long id;
	
	private String email;
	
	private String nombre;
	
	private Integer matricula;
	
	private String turnoLaboral;
	
	private List<ObraSocialDTO> obrasSocialesDTO;
	
	private List<TurnoDTO> turnosDTO;
	
	public MedicoDTO() {
		
	}
	
	public MedicoDTO(String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocialDTO> obrasSocialesDTO, List<TurnoDTO> turnosDTO) {
		this.email = email;
		this.nombre = nombre;
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.obrasSocialesDTO = obrasSocialesDTO;
		this.turnosDTO = turnosDTO;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(String turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public List<ObraSocialDTO> getObrasSocialesDTO() {
		return obrasSocialesDTO;
	}

	public void setObrasSociales(List<ObraSocialDTO> obrasSocialesDTO) {
		this.obrasSocialesDTO = obrasSocialesDTO;
	}

	public List<TurnoDTO> getTurnos() {
		return turnosDTO;
	}

	public void setTurnosDTO(List<TurnoDTO> turnosDTO) {
		this.turnosDTO = turnosDTO;
	}
	
	
}

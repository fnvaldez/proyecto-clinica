package ar.edu.unju.fi.poo.clinica.dto;

import java.util.List;

public class PacienteDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String email;
	
	private String nombre;
	
	private Integer dni;
	
	private String domicilio;
	
	private String numeroDeCelular;
	
    private Long obraSocialId;
	
	private List<TurnoDTO> turnosDTO;
	
	
	public PacienteDTO() {
		
	}
	
	public PacienteDTO(String nombre, String email, Integer dni, String domicilio, String numeroDeCelular, Long obraSocialId) {
		this.email = email;
		this.nombre = nombre;
		this.dni = dni;
		this.domicilio = domicilio;
		this.numeroDeCelular = numeroDeCelular;
		this.obraSocialId = obraSocialId;
	}
	
	public Integer getDni() {
		return dni;
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

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNumeroDeCelular() {
		return numeroDeCelular;
	}

	public void setNumeroDeCelular(String numeroDeCelular) {
		this.numeroDeCelular = numeroDeCelular;
	}

	public Long getObraSocialId() {
		return obraSocialId;
	}
	
	public void setObraSocialId(Long obraSocialId) {
		this.obraSocialId = obraSocialId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<TurnoDTO> getTurnosDTO() {
		return turnosDTO;
	}
	
	public void setTurnosDTO(List<TurnoDTO> turnosDTO) {
		this.turnosDTO = turnosDTO;
	}
	
}

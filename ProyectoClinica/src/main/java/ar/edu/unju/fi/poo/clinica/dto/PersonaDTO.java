package ar.edu.unju.fi.poo.clinica.dto;

public class PersonaDTO {

private Long id;
	
	private String email;
	
	private String nombre;
	
	public PersonaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PersonaDTO(Long id, String email, String nombre) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
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
}

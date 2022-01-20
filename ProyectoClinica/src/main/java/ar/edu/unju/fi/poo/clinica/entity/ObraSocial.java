package ar.edu.unju.fi.poo.clinica.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OBRAS_SOCIALES")
public class ObraSocial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String telefono;
	
	@Column
	private String direccion;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "obraSocial")
	private List<Paciente> pacientes;
	
	@ManyToMany(mappedBy = "obrasSociales")
	private List<Medico> medicos;
	
	public ObraSocial() {
		// TODO Auto-generated constructor stub
	}

	public ObraSocial(String nombre, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	@Override
	public String toString() {
		return "ObraSocial [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ "]";
	}
	
}

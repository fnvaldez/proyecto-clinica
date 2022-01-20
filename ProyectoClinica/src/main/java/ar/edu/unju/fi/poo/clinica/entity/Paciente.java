package ar.edu.unju.fi.poo.clinica.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "PACIENTES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Paciente extends Persona {

	@Column
	private Long dni;
	
	@Column
	private String domicilio;
	
	@Column
	private String numeroDeCelular;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "obra_social_id", nullable = false)
	private ObraSocial obraSocial;
	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	//private List<Turno> turnos;
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	
	public Paciente(String nombre, String email, Long dni, String domicilio, String numeroDeCelular, ObraSocial obraSocial) {
		super(nombre, email);
		this.dni = dni;
		this.domicilio = domicilio;
		this.numeroDeCelular = numeroDeCelular;
		this.obraSocial = obraSocial;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
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

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
/*
	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
*/
	@Override
	public String toString() {
		return "Paciente [domicilio=" + domicilio + ", numeroDeCelular=" + numeroDeCelular + ", obraSocial="
				+ obraSocial + "]";
	}
	
}

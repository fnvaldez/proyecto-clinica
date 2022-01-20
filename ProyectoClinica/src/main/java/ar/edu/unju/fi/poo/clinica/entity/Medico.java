package ar.edu.unju.fi.poo.clinica.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICOS")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Medico extends Persona {

	@Column
	private Integer matricula;
	
	@Column
	private String turnoLaboral;
	
	@ManyToMany
	@JoinTable(name = "medicos_obrasSociales", 
				joinColumns = @JoinColumn(name = "medico_id"),
				inverseJoinColumns = @JoinColumn(name = "obra_social_id"))
	private List<ObraSocial> obrasSociales;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medico")
	private List<Turno> turnos;
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public Medico(String nombre, String email,Integer matricula, String turnoLaboral, List<ObraSocial> obrasSociales) {
		super(nombre, email);
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.obrasSociales = obrasSociales;
	}

	public Integer getMatricula() {
		return matricula;
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

	public List<ObraSocial> getObrasSociales() {
		return obrasSociales;
	}

	public void setObrasSociales(List<ObraSocial> obrasSociales) {
		this.obrasSociales = obrasSociales;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	@Override
	public String toString() {
		return "Medico [matricula=" + matricula + ", turnoLaboral=" + turnoLaboral + "]";
	}
	
}

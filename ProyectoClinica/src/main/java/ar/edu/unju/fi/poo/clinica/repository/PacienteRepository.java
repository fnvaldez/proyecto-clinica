package ar.edu.unju.fi.poo.clinica.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.clinica.entity.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

	public Paciente findByDni(Integer dni);
	public List<Paciente> findByNombreContaining(String cadena);
}

package ar.edu.unju.fi.poo.clinica.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.clinica.entity.Medico;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Long> {

	public Medico findByMatricula(Integer matricula);
	public List<Medico> findByNombreOrMatricula(String nombre , Integer matricula);
}

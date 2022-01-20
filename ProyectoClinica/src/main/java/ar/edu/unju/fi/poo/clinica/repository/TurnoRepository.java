package ar.edu.unju.fi.poo.clinica.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.clinica.entity.Turno;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Long> {

	public List<Turno> findByMedico_id(Long id);
	public Turno findByPaciente_dniAndId(Integer dni, Long turnoId);
}

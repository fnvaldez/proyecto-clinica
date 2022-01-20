package ar.edu.unju.fi.poo.clinica.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.clinica.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}

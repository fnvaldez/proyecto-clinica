package ar.edu.unju.fi.poo.clinica.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.clinica.dto.PacienteDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.PacienteServiceImp;


@RestController
@RequestMapping("/api/v1")
public class PacienteController {

	@Autowired
	private PacienteServiceImp pacienteService;
	
	@GetMapping("/pacientes")
	public List<PacienteDTO> getAllPacientes(){
		return pacienteService.getAllPacientes();
	}
	
	@GetMapping("/pacientes/{pacienteId}")
	public ResponseEntity<?> getPaciente(@PathVariable Long pacienteId) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			PacienteDTO pacienteEncontrado = pacienteService.buscarPacientePorId(pacienteId);
			response.put("ObjectPaciente", pacienteEncontrado);
			response.put("Mensaje", "Paciente encontrado");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error al buscar paciente");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pacientes")
	public ResponseEntity<?> addPaciente(@RequestBody PacienteDTO pacienteDTO) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			pacienteService.registrarPaciente(pacienteDTO);
			response.put("ObjectPaciente", pacienteDTO);
			response.put("Mensaje", "Paciente registrado");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error en alta de paciente");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pacientes")
	public ResponseEntity<?> updatePaciente(@RequestBody PacienteDTO pacienteDTO) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			PacienteDTO pacienteEncontrado = pacienteService.buscarPacientePorId(pacienteDTO.getId());
			pacienteService.registrarPaciente(pacienteDTO);
			response.put("ObjectPaciente", pacienteDTO);
			response.put("Mensaje", "Paciente actualizado");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error al actualizar paciente");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}

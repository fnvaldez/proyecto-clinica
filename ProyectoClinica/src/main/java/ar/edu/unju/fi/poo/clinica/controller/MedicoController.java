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

import ar.edu.unju.fi.poo.clinica.dto.MedicoDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.MedicoServiceImp;

public class MedicoController {

	@Autowired
	private MedicoServiceImp medicoService;
	
	@GetMapping("/medicos")
	public List<MedicoDTO> getAllMedicos(){
		return medicoService.getAllMedicos();
	}
	
	@GetMapping("/medicos/{medicoId}")
	public ResponseEntity<?> getMedico(@PathVariable Long medicoId){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			MedicoDTO medicoEncontrado = medicoService.buscarMedicoPorId(medicoId);
			response.put("ObjectMedico", medicoEncontrado);
			response.put("Mensaje", "Medico encontrado");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Medico no encontrado");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/medicos")
	public ResponseEntity<?> addMedico(@RequestBody MedicoDTO medicoDTO) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			medicoService.altaMedico(medicoDTO);
			response.put("ObjectMedico", medicoDTO);
			response.put("Mensaje", "Medico registrado");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error en alta de medico");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/medicos")
	public ResponseEntity<?> updateMedico(@RequestBody MedicoDTO medicoDTO) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			MedicoDTO medicoEncontrado = medicoService.buscarMedicoPorId(medicoDTO.getId());
			medicoService.altaMedico(medicoDTO);
			response.put("ObjectMedico", medicoDTO);
			response.put("Mensaje", "Medico actualizado");
			
		} catch (Exception e) {
			response.put("Mensaje", "Error al actualizar medico");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}

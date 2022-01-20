package ar.edu.unju.fi.poo.clinica.controller;

import java.time.LocalDateTime;
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

import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.TurnoServiceImp;

@RestController
@RequestMapping("/api/v1")
public class TurnoController {

	@Autowired
	private TurnoServiceImp turnoService;
	
	@GetMapping("/turnos")
	public List<TurnoDTO> getTurnos(){
		return turnoService.getAllTurnos();
	}
	
	@PostMapping("/turnos")
	public ResponseEntity<?> addTurno(@RequestBody TurnoDTO turno) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			turnoService.registarTurno(turno);
			response.put("Turno", turno);
			response.put("Mensaje", "Turno registrado");
			
		} catch (Exception e) {
			response.put("Mensaje", "Error en alta de turno");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/turnos/{dni}/{turnoId}/retrasar-{retraso}")
	public ResponseEntity<?> delayTurno(@PathVariable Integer dni, @PathVariable Long turnoId, @PathVariable int retraso){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			turnoService.retrasarTurnos(dni, turnoId, retraso);
			response.put("Mensaje", "Turno retrasado con exito");
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error al atrasar el turno");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/turnos/{dni}/{turnoId}/consultar")
	public ResponseEntity<?> getHorario(@PathVariable Integer dni, @PathVariable Long turnoId){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			LocalDateTime horarioConsultado = turnoService.consultarHorarioDeAtencionPorDNI(dni, turnoId);
			response.put("Horario consultado", horarioConsultado);
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error al consultar el turno");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/turnos/{dni}/{turnoId}/calcular")
	public ResponseEntity<?> getTiempoRestante(@PathVariable Integer dni, @PathVariable Long turnoId){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			String tiempoRestante = turnoService.calcularTiempoRestanteParaTurno(dni, turnoId);
			response.put("Tiempo restante", tiempoRestante );
			
		} catch (Exception e) {
			
			response.put("Mensaje", "Error al calcular tiempo restante");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}

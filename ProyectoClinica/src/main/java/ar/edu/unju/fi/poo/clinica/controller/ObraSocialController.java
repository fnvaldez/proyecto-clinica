package ar.edu.unju.fi.poo.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.clinica.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.ObraSocialServiceImp;

@RestController
@RequestMapping("/api/v1")
public class ObraSocialController {

	@Autowired
	private ObraSocialServiceImp obraSocialService;
	
	@GetMapping("/obras")
	public List<ObraSocialDTO> getAllObrasSociales(){
		List<ObraSocialDTO> listaObraSocial= obraSocialService.getAllObrasSociales();
		return listaObraSocial;
	}
}

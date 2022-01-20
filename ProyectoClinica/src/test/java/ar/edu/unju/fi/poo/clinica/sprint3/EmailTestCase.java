package ar.edu.unju.fi.poo.clinica.sprint3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.poo.clinica.dto.MedicoDTO;
import ar.edu.unju.fi.poo.clinica.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.clinica.dto.PacienteDTO;
import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.MedicoServiceImp;
import ar.edu.unju.fi.poo.clinica.service.imp.ObraSocialServiceImp;
import ar.edu.unju.fi.poo.clinica.service.imp.PacienteServiceImp;
import ar.edu.unju.fi.poo.clinica.service.imp.TurnoServiceImp;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class EmailTestCase {

	final static Logger logger = Logger.getLogger(EmailTestCase.class);
	
	@Autowired
	private PacienteServiceImp pacienteService;
	
	@Autowired
	private MedicoServiceImp medicoService;
	
	@Autowired
	private ObraSocialServiceImp obraSocialService;
	
	@Autowired
	private TurnoServiceImp turnoService;
	
	
	PacienteDTO paciente1;
	PacienteDTO paciente2;
	MedicoDTO medico1;
	TurnoDTO turno1;
	TurnoDTO turno2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		logger.debug("Configurando en SetUp...");
		
		paciente1 = new PacienteDTO("Juan Alvarez", "valdezfn15@gmail.com", 1000000, "424 Bosco Garden Suite 691", "1976235", 1L);
		paciente2 = new PacienteDTO("Jose Baena", "jbaena@gmail.com", 2000000, "27769 Thompson Rapid", "3841840", 2L);
		
		List<ObraSocialDTO> obras = obraSocialService.getAllObrasSociales();
		
		List<TurnoDTO> turnos = new ArrayList<>();
		
		medico1 = new MedicoDTO("Juan Velez", "florencianoelivaldez@gmail.com", 1230123, "MAÑANA", obras, turnos);
		
		turno1 = new TurnoDTO(null, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
		turno2 = new TurnoDTO(null, null,LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)),LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.debug("Limpiando variables");
		paciente1 = null;
		paciente2 = null;
		medico1 = null;
		turno1 = null;
		turno2 = null;
	}

	@Test
	@DisplayName("Registrar Turno")
	void altaTurnoTest() {
		
		pacienteService.registrarPaciente(paciente1);
		pacienteService.registrarPaciente(paciente2);
		
		medicoService.altaMedico(medico1);
		
		
		
		logger.info("Agregando turnos");
		
		turno1.setMedicoId(3L);
		turno1.setPacienteId(1L);
		turnoService.registarTurno(turno1);
		
		turno2.setMedicoId(3L);
		turno2.setPacienteId(2L);
		turnoService.registarTurno(turno2);
		
		assertTrue(turnoService.getAllTurnos().size()>0);
		
				
	}

}

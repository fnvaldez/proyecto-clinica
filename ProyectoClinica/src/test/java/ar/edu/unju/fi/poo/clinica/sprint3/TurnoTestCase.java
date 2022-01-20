package ar.edu.unju.fi.poo.clinica.sprint3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class TurnoTestCase {

	final static Logger logger = Logger.getLogger(TurnoTestCase.class);
	
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
	PacienteDTO paciente3;
	MedicoDTO medico1;
	MedicoDTO medico2;
	MedicoDTO medico3;
	TurnoDTO turno1;
	TurnoDTO turno2;
	TurnoDTO turno3;
	LocalDate fechaPosterior = LocalDate.now().plusDays(1);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		logger.debug("Configurando en SetUp...");
		
		paciente1 = new PacienteDTO("Juan Alvarez", "valdezfn15m@gmail.com", 1000000, "424 Bosco Garden Suite 691", "1976235", 1L);
		paciente2 = new PacienteDTO("Jose Baena", "jbaena@gmail.com", 200, "27769 Thompson Rapid", "3841840", 2L);
		paciente3 = new PacienteDTO("David Quitero", "dquintero@gmail.com", 300, "2694 Conner Toute Suite 789", "6225750", 3L);
		
		List<ObraSocialDTO> obras = obraSocialService.getAllObrasSociales();
		
		List<TurnoDTO> turnos = new ArrayList<>();
		
		medico1 = new MedicoDTO("Juan Velez", "jvelez@gmail.com", 1230123, "MAÑANA", obras, turnos);
		medico2 = new MedicoDTO("Marcos Tevez", "florencianoelivaldez@gmail.com", 3210321, "MAÑANA", obras, turnos);
		medico3 = new MedicoDTO("Silvia Ceruti", "sceruti@gmail.com", 1411141, "MAÑANA", obras, turnos);
		
		turno1 = new TurnoDTO(null, null, LocalDateTime.of(fechaPosterior, LocalTime.of(9, 00)), LocalDateTime.of(fechaPosterior, LocalTime.of(10, 0)));
		turno2 = new TurnoDTO(null, null,LocalDateTime.of(fechaPosterior, LocalTime.of(10, 00)),LocalDateTime.of(fechaPosterior, LocalTime.of(11, 00)));
		turno3 = new TurnoDTO(null, null, LocalDateTime.of(fechaPosterior, LocalTime.of(12, 00)),LocalDateTime.of(fechaPosterior, LocalTime.of(13, 00)));
		//turno3 = new TurnoDTO(null, null, LocalDateTime.of(fechaPosterior, LocalTime.of(10, 30)),LocalDateTime.of(fechaPosterior, LocalTime.of(11, 30)));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.debug("Limpiando variables");
		
		paciente1 = null;
		paciente2 = null;
		paciente3 = null;
		medico1 = null;
		medico2 = null;
		medico3 = null;
		turno1 = null;
		turno3 = null;
		turno2 = null;
	}

	@Test
	@DisplayName("Registrar Turno")
	void altaTurnoTest() {
		
		pacienteService.registrarPaciente(paciente1);
		pacienteService.registrarPaciente(paciente2);
		pacienteService.registrarPaciente(paciente3);
		
		
		medicoService.altaMedico(medico1);
		medicoService.altaMedico(medico2);
		medicoService.altaMedico(medico3);

		
		logger.info("Agregando turnos");
		
		turno1.setMedicoId(5L);
		turno1.setPacienteId(1L);
		turnoService.registarTurno(turno1);
		
		assertTrue(turnoService.buscarTurnoPorId(1L) != null );
		
		turno2.setMedicoId(5L);
		turno2.setPacienteId(2L);
		turnoService.registarTurno(turno2);
		
		turno3.setMedicoId(5L);
		turno3.setPacienteId(3L);
		turnoService.registarTurno(turno3);
		
		assertTrue(turnoService.getAllTurnos().size()>1);
		
	}
	
	@Test
	@DisplayName("Retrasar turnos")
	void retrasarTurnosTest() {
		try {
			turnoService.retrasarTurnos(200, 2L, 30);
			TurnoDTO turnoEncontrado = turnoService.buscarTurnoPorId(2L);
			assertEquals(turnoEncontrado.getFechaHoraFin(),LocalDateTime.of(fechaPosterior, LocalTime.of(11, 30)));
		} catch (Exception e) {
			logger.error("TEST: altaTurnoTest --> Error: " + e);
		}
		
	}
	
	@Test
	@DisplayName("Consultar Turno")
	void consultarHoraTurnoTest() {
		
		try {
			
			LocalDateTime horarioConsultado = turnoService.consultarHorarioDeAtencionPorDNI(1000000, 1L);
			assertEquals(LocalDateTime.of(fechaPosterior, LocalTime.of(9, 0)),horarioConsultado);
			
		} catch (Exception e) {
			logger.error("TEST: consultarHoraTurnoTest --> Error: " + e);
		}
		
	}
	
	@Test
	@DisplayName("Calcular tiempo restante")
	void consultarTiempoRestanteTest() {
		
		try {
			
			String tiempoRestante = turnoService.calcularTiempoRestanteParaTurno(300, 3L);
			assertTrue(Character.getNumericValue(tiempoRestante.charAt(0))>0);
			
		} catch (Exception e) {
			logger.error("TEST: consultarTiempoRestanteTest --> Error: " + e);
		}
		
	}
	

}

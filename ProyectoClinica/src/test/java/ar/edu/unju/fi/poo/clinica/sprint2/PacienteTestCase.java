package ar.edu.unju.fi.poo.clinica.sprint2;

import static org.junit.jupiter.api.Assertions.*;

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

import ar.edu.unju.fi.poo.clinica.dto.PacienteDTO;
import ar.edu.unju.fi.poo.clinica.service.imp.PacienteServiceImp;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PacienteTestCase {

	final static Logger logger = Logger.getLogger(PacienteTestCase.class);
	
	@Autowired
	private PacienteServiceImp pacienteService;
	
	
	PacienteDTO paciente1;
	PacienteDTO paciente2;
	PacienteDTO paciente3;
	PacienteDTO paciente4;
	PacienteDTO paciente5;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		logger.debug("Configurando en SetUp...");
		
		paciente1 = new PacienteDTO("Juan Alvarez", "jalvarez@gmail.com", 1000000, "424 Bosco Garden Suite 691", "1976235", 1L);
		paciente2 = new PacienteDTO("Jose Baena", "jbaena@gmail.com", 2000000, "27769 Thompson Rapid", "3841840", 2L);
		paciente3 = new PacienteDTO("David Quitero", "dquintero@gmail.com", 3000000, "2694 Conner Toute Suite 789", "6225750", 3L);
		paciente4 = new PacienteDTO("Juliana Velez", "jvelez@gmail.com", 4000000, "79384 Hagenes Ville Apt. 133", "5880985", 1L);
		paciente5 = new PacienteDTO("Carlos Marin", "cmarin@gmail.com", 5000000, "158 Williamson Place Apt. 751", "154616549", 4L);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.debug("Limpiando variables");
		
		paciente1 = null;
		paciente2 = null;
		paciente3 = null;
		paciente4 = null;
		paciente5 = null;
		
	}
	
	@Test
	@DisplayName("Registrar Paciente")
	void agregarPacienteTest() {
		
		logger.info("Iniciando registro de pacientes");
		
		long cantidadInicial = pacienteService.countPacientes();
		
		try {
			
			pacienteService.registrarPaciente(paciente1);
			pacienteService.registrarPaciente(paciente2);
			pacienteService.registrarPaciente(paciente3);
			pacienteService.registrarPaciente(paciente4);
			pacienteService.registrarPaciente(paciente5);
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		long cantidadFinal = pacienteService.countPacientes();
		assertTrue(cantidadInicial != cantidadFinal);
	}
	

	@Test
	@DisplayName("Modificar Pacientes")
	void modificarPacienteTest() {
		
		PacienteDTO pacienteEncontrado = pacienteService.buscarPacientePorId(2L);
		
		String domicilioAnterior = pacienteEncontrado.getDomicilio();
		String domicilioNuevo = "239 Tromp Row Apt.236";
		
		logger.info("Actualizando datos");
		
		pacienteEncontrado.setDomicilio(domicilioNuevo);
		pacienteService.registrarPaciente(pacienteEncontrado);
		PacienteDTO pacienteActualizado = pacienteService.buscarPacientePorId(2L);
		assertNotEquals(domicilioAnterior, pacienteActualizado.getDomicilio());
		
	}
	
	@Test
	@DisplayName("Buscar pacientes por nombre")
	void buscarPacientesTest() {
		
		logger.info("Buscando paciente");
		
		List<PacienteDTO> pacientesEncontrados = pacienteService.buscarPacientesPorNombre("Ju");
		assertTrue(pacientesEncontrados.size()>0);
	}

}

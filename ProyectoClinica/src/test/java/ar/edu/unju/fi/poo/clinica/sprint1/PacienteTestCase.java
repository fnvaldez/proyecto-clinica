package ar.edu.unju.fi.poo.clinica.sprint1;

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

import ar.edu.unju.fi.poo.clinica.entity.ObraSocial;
import ar.edu.unju.fi.poo.clinica.entity.Paciente;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.manager.ObraSocialManager;
import ar.edu.unju.fi.poo.clinica.manager.PacienteManager;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PacienteTestCase {

	final static Logger logger = Logger.getLogger(PacienteTestCase.class);
	
	@Autowired
	private PacienteManager pacienteManager;
	
	@Autowired
	private ObraSocialManager obraSocialManager;
	
	Paciente paciente1;
	Paciente paciente2;
	Paciente paciente3;
	Paciente paciente4;
	Paciente paciente5;
	ObraSocial obra1;
	ObraSocial obra2;
	ObraSocial obra3;
	ObraSocial obra4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		logger.debug("Configurando en SetUp...");
		
		obra1 = obraSocialManager.buscarObraSocialPorId(1L);
		obra2 = obraSocialManager.buscarObraSocialPorId(2L);
		obra3 = obraSocialManager.buscarObraSocialPorId(3L);
		obra4 = obraSocialManager.buscarObraSocialPorId(4L);
		
		paciente1 = new Paciente("Juan Alvarez", "jalvarez@gmail.com", 1000000L, "424 Bosco Garden Suite 691", "155224755", obra1);
		paciente2 = new Paciente("Jose Baena", "jbaena@gmail.com", 2000000L, "27769 Thompson Rapid", "155068393", obra2);
		paciente3 = new Paciente("David Quitero", "dquintero@gmail.com", 3000000L, "2694 Conner Toute Suite 789", "154944216", obra3);
		paciente4 = new Paciente("Juliana Velez", "jvelez@gmail.com", 4000000L, "79384 Hagenes Ville Apt. 133", "154444815", obra1);
		paciente5 = new Paciente("Carlos Marin", "cmarin@gmail.com", 5000000L, "158 Williamson Place Apt. 751", "154616549", obra4);
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
		logger.debug("Limpiando variables");
		
		obra1 = null;
		obra2 = null;
		obra3 = null;
		obra4 = null;
		paciente1 = null;
		paciente2 = null;
		paciente3 = null;
		paciente4 = null;
		paciente5 = null;
		
	}
	
	@Test
	@DisplayName("Agregar paciente")
	void agregarPacienteTest() {
		
		logger.info("Iniciando registro de pacientes");
		
		int cantidadInicial = pacienteManager.countPacientes();
		//Controlar que se siga ejecutando
		try {
			
			pacienteManager.registrarPaciente(paciente1);
			pacienteManager.registrarPaciente(paciente2);
			pacienteManager.registrarPaciente(paciente3);
			pacienteManager.registrarPaciente(paciente4);
			pacienteManager.registrarPaciente(paciente5);
			
		} catch (ModelException e) {
			logger.error(e);
		}
		
		int cantidadFinal = pacienteManager.countPacientes();
		assertTrue(cantidadInicial != cantidadFinal);
		
		Paciente pacienteEncotrado = pacienteManager.buscarPacientePorId(2L);
		assertTrue(pacienteEncotrado != null);
		assertEquals(pacienteEncotrado.getDni(),2000000);
		
		assertTrue(pacienteManager.buscarPacientePorId(5L) == null);
		
	}
	
	@Test
	@DisplayName("Buscar Pacientes por nombre")
	void buscarPacientesTest() {
		
		logger.info("Buscando paciente");
		
		List<Paciente> pacientesEncontrados = pacienteManager.buscarPacientesPorNombre("Ju");
		assertTrue(pacientesEncontrados.size() > 0);
	}
	
	@Test
	@DisplayName("Modificar paciente")
	void modificarPacienteTest() {
		
 	   	Paciente pacienteEncontrado = pacienteManager.buscarPacientePorId(2L);
 	   	
		String domicilioAnterior = pacienteEncontrado.getDomicilio();
		String domicilioNuevo = "239 Tromp Row Apt.236";
		
		logger.info("Actualizando datos de paciente");
		
		pacienteEncontrado.setDomicilio(domicilioNuevo);
		pacienteManager.registrarPaciente(pacienteEncontrado);
		Paciente pacienteActualizado = pacienteManager.buscarPacientePorId(2L);
		
		assertNotEquals(domicilioAnterior, pacienteActualizado.getDomicilio());
		
	}

}

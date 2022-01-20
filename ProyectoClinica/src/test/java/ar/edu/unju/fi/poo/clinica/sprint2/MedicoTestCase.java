package ar.edu.unju.fi.poo.clinica.sprint2;

import static org.junit.jupiter.api.Assertions.*;

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
import ar.edu.unju.fi.poo.clinica.service.imp.MedicoServiceImp;
import ar.edu.unju.fi.poo.clinica.service.imp.ObraSocialServiceImp;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class MedicoTestCase {

	final static Logger logger = Logger.getLogger(MedicoTestCase.class);
	
	@Autowired
	private MedicoServiceImp medicoService;
	
	@Autowired
	private ObraSocialServiceImp obraSocialService;
	
	MedicoDTO medico1;
	MedicoDTO medico2;
	MedicoDTO medico3;
	MedicoDTO medico4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		logger.debug("Configurando en SetUp...");
		
		List<ObraSocialDTO> obras1 = obraSocialService.getAllObrasSociales();
		List<ObraSocialDTO> obras2 = new ArrayList<>();
		
		medico1 = new MedicoDTO("Juan", "juan@gmail.com", 1230123, "MAÑANA", obras1, null);
		medico2 = new MedicoDTO("Marcos", "marcos@gmail.com", 3210321, "MAÑANA", obras1, null);
		medico3 = new MedicoDTO("Juan", "juan123@gmail.com", 1411141, "MAÑANA", obras1, null);
		medico4 = new MedicoDTO("Lina", "lina@gmail.com", 1518121, "MAÑANA", obras2,null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.debug("Limpiando variables");
		
		medico1 = null;
		medico2 = null;
		medico3 = null;
		medico4 = null;
	}
	
	@Test
	@DisplayName("Alta Medico")
	void altaMedicoTest() {
		
		logger.info("Registrando medico");
		
		long cantidadInicial = medicoService.countMedicos();
		
		try {
			
			medicoService.altaMedico(medico1);
			medicoService.altaMedico(medico2);
			medicoService.altaMedico(medico3);
			medicoService.altaMedico(medico4);
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		long cantidadFinal = medicoService.countMedicos();
		assertTrue(cantidadInicial != cantidadFinal);
		
	}

	@Test
	@DisplayName("Buscar Medico")
	void buscarMedicoTest() {
		
		logger.info("Buscando medico");
		
		assertTrue(medicoService.buscarMedicoPorMatriculaONombre("Juan", null).size()>0);
		assertTrue(medicoService.buscarMedicoPorMatriculaONombre(null, 1411141).size() == 1);
	
	}

}

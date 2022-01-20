package ar.edu.unju.fi.poo.clinica.sprint1;

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

import ar.edu.unju.fi.poo.clinica.entity.Medico;
import ar.edu.unju.fi.poo.clinica.entity.ObraSocial;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.manager.MedicoManager;
import ar.edu.unju.fi.poo.clinica.manager.ObraSocialManager;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class MedicoTestCase {

final static Logger logger = Logger.getLogger(MedicoTestCase.class);
	
	@Autowired
	private MedicoManager medicoManager;
	
	@Autowired
	private ObraSocialManager obraSocialManager;
	
	Medico medico1;
	Medico medico2;
	Medico medico3;
	Medico medico4;
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
		
		List<ObraSocial> obrasList1 = new ArrayList<>();
		List<ObraSocial> obrasList2 = new ArrayList<>();
		obrasList1.add(obra1);
		obrasList1.add(obra2);
		
		medico1 = new Medico("Juan", "juan@gmail.com", 1230123, "MAÑANA", obrasList1);
		medico2 = new Medico("Marcos", "marcos@gmail.com", 3210321, "MAÑANA", obrasList1);
		medico3 = new Medico("Juan", "juan123@gmail.com", 1411141, "TARDE", obrasList1);
		medico4 = new Medico("Lina", "lina@gmail.com", 1518121, "MAÑANA", obrasList2);
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
		logger.debug("Limpiando variables");
		
		obra1 = null;
		obra2 = null;
		obra3 = null;
		obra4 = null;
		medico1 = null;
		medico2 = null;
		medico3 = null;
		medico4 = null;
		
	}
	
	@Test
	@DisplayName("Alta Medico")
	void altaMedicoTest() {
		
		logger.info("Registrando medico");
		
		int cantidadInicial = medicoManager.countMedicos();
		//Controlar que se siga ejecutando
		try {
			
			medicoManager.altaMedico(medico1);
			medicoManager.altaMedico(medico2);
			medicoManager.altaMedico(medico3);
			medicoManager.altaMedico(medico4);
			
		} catch (ModelException e) {
			logger.error(e);
		}
		
		int cantidadFinal = medicoManager.countMedicos();
		assertTrue(cantidadInicial != cantidadFinal);
		
	}
	
	@Test
	@DisplayName("Buscar Medico")
	void buscarMedicoTest() {
		
		logger.info("Buscando medico");
		
		assertTrue(medicoManager.buscarMedicoPorMatriculaONombre("Juan", null).size() > 0);
		
		assertTrue(medicoManager.buscarMedicoPorMatriculaONombre(null, 1411141).size() == 1);
	
	}

}

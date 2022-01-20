package ar.edu.unju.fi.poo.clinica.service.imp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.clinica.dto.TurnoDTO;
import ar.edu.unju.fi.poo.clinica.entity.Medico;
import ar.edu.unju.fi.poo.clinica.entity.Paciente;
import ar.edu.unju.fi.poo.clinica.entity.Turno;
import ar.edu.unju.fi.poo.clinica.exceptions.ModelException;
import ar.edu.unju.fi.poo.clinica.repository.MedicoRepository;
import ar.edu.unju.fi.poo.clinica.repository.PacienteRepository;
import ar.edu.unju.fi.poo.clinica.repository.TurnoRepository;
import ar.edu.unju.fi.poo.clinica.service.TurnoService;
import ar.edu.unju.fi.poo.clinica.util.Constante;
import ar.edu.unju.fi.poo.clinica.util.Mapper;

@Service
public class TurnoServiceImp implements TurnoService {

	final static Logger logger = Logger.getLogger(TurnoServiceImp.class);
	
	@Autowired
	private TurnoRepository turnoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	private Mapper mapper = new Mapper();
	
	final static LocalTime horaInicio = LocalTime.of(Constante.HORA_INICIO, 0);
	final static LocalTime horaFin = LocalTime.of(Constante.HORA_FINAL, 0);
	
	/**
	* Método que permite dar de alta los turnos, con su correspondiente validacion 
	*/
	@Override
	public void registarTurno(TurnoDTO turnoDTO) throws ModelException{
		
		List<Turno> turnosMedico = turnoRepository.findByMedico_id(turnoDTO.getMedicoId());
		
		if(validarHorarioAtencion(turnoDTO)==false) {
			if( turnosMedico.size()!=0 ) {
				for(Turno t : turnosMedico) {
					if(validarTurno(turnoDTO, t) == true) {
						logger.error("Los turnos se superponen");
						throw new ModelException("Turnos superpuestos");
					}
				}
			}
			
			Turno turno = validarId(turnoDTO);
			turnoRepository.save(turno);
			
			
			logger.info("Se ha registrado un turno");
		}else {
			logger.error("Turno fuera de horario de atencion");
			throw new ModelException("Turno fuera del horario de atencion");
		}

	}
	
	/**
	 * Metodo auxiliar que valida que los turnos no se superpongan
	 * 
	 * @param turnoDTO
	 * @param turno
	 * @return boolean
	 */
	private Boolean validarTurno(TurnoDTO turnoDTO, Turno turno) {
		
		boolean caso1 = turnoDTO.getFechaHoraInicio().isEqual(turno.getFechaHoraInicio()) || turnoDTO.getFechaHoraFin().isEqual(turno.getFechaHoraFin());
				
		boolean caso2 = turnoDTO.getFechaHoraFin().isBefore(turno.getFechaHoraFin());
		
		boolean caso3 = turnoDTO.getFechaHoraInicio().isBefore(turno.getFechaHoraFin());
		
		return caso1 || caso2 || caso3;
	}
	

	/**
	 * Método que valida que un turno no se agregue fuera del horario de atencion
	 * @param turnoDTO
	 * @return boolean
	*/
	private Boolean validarHorarioAtencion(TurnoDTO turnoDTO) {
		
		LocalTime horaInicioTurno = turnoDTO.getFechaHoraInicio().toLocalTime();
		return horaInicioTurno.isBefore(horaInicio) || horaInicioTurno.isAfter(horaFin) || horaInicioTurno.equals(horaFin);
	}
	
	/**
	 * Metodo auxiliar que busca y valida que que tanto Medico como Paciente existan ,para generar el turno
	 * @param turnoDTO
	 * @return
	 * @throws ModelException
	 */
	private Turno validarId(TurnoDTO turnoDTO) throws ModelException {
		
		Optional<Medico> medicoEncontrado = medicoRepository.findById(turnoDTO.getMedicoId());
		Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(turnoDTO.getPacienteId());
		
		if(!(medicoEncontrado.isPresent() && pacienteEncontrado.isPresent())) {
			logger.error("El id no existe");
			throw new ModelException("El id no existe");
		}
		Turno turno = new Turno();
		turno = mapper.map(turnoDTO, Turno.class);
		turno.setMedico(medicoEncontrado.get());
		turno.setPaciente(pacienteEncontrado.get());
		return turno;
	}
	
	/**
	 * Metodo que permite retrasar un turno, y los turnos que le siguen
	 * 
	 */
	@Override
	public void retrasarTurnos(Integer dni, Long turnoId, int retraso) throws ModelException {
		
		Turno turnoEncontrado = turnoRepository.findByPaciente_dniAndId(dni, turnoId);
		
		if(turnoEncontrado == null) {
			logger.error("No se encontro el turno");
			throw new ModelException("No se encontro ningun turno con el DNI ingresado");
		}
		
		List<Turno> turnosMedico = getTurnosPosteriores(turnoEncontrado);
		
		turnoEncontrado.setFechaHoraFin(turnoEncontrado.getFechaHoraFin().plusMinutes(retraso));
		turnoRepository.save(turnoEncontrado);
		
		if(turnosMedico.size()>0) {
			for (Turno t : turnosMedico) {
				t.setFechaHoraInicio(t.getFechaHoraInicio().plusMinutes(retraso));
				t.setFechaHoraFin(t.getFechaHoraFin().plusMinutes(retraso));
				turnoRepository.save(t);
			}
		}
	}
	/**
	 * Metodo auxiliar que permite recibir la lista de turnos de un  medico especifico, desde un determinado horario
	 * @param turno
	 * @return List<Turno>
	 */
	private List<Turno> getTurnosPosteriores(Turno turno){
		
		List<Turno> turnosMedico = turnoRepository.findByMedico_id(turno.getMedico().getId());
		LocalDateTime encontrado = turno.getFechaHoraInicio();
		List<Turno> turnosPosteriores = new ArrayList<>();
		
		for(Turno t : turnosMedico) {
			if(t.getFechaHoraInicio().isAfter(encontrado))
				turnosPosteriores.add(t);
		}
		return turnosPosteriores;
	}
	
	/**
	 * Método que permite consultar un determinado turno , de acuerdo a un numero de DNI
	 * return LocalDateTime
	 */
	@Override
	public LocalDateTime consultarHorarioDeAtencionPorDNI(Integer dni, Long turnoId) throws ModelException {
		
		Turno turnoEncontrado = turnoRepository.findByPaciente_dniAndId(dni, turnoId);
		
		if(turnoEncontrado == null) {
			logger.error("No se encontro el turno");
			throw new ModelException("No se encontro ningun turno con el DNI ingresado");
		}
		
		return turnoEncontrado.getFechaHoraInicio();
	}
	
	/**
	 * Metodo que permite calcular el tiempo que resta para que toque un turno
	 * return String
	 */
	@Override
	public String calcularTiempoRestanteParaTurno(Integer dni, Long turnoId) throws ModelException {
		
		Turno turnoEncontrado = turnoRepository.findByPaciente_dniAndId(dni, turnoId);
		
		if(turnoEncontrado == null) {
			logger.error("No se encontro el turno");
			throw new ModelException("No se encontro ningun turno con el DNI ingresado");
		}

		LocalDateTime fechaHoraTurno = turnoEncontrado.getFechaHoraInicio();
		
		if(fechaHoraTurno.isBefore(LocalDateTime.now())) {
			logger.error("Turno anterior a la fecha actual");
			throw new ModelException("El turno encontrado es anterior a la fecha actual");
		}

		Duration duracion = Duration.between(LocalDateTime.now(), fechaHoraTurno);
		
		String tiempoRestante = duracion.toHoursPart() + " horas - " + duracion.toMinutesPart() + " minutos";

		return tiempoRestante;
		
	}
	/**
	 * Devuelve todos los turnos
	 * return: List<TurnoDTO>
	 */
	@Override
	public List<TurnoDTO> getAllTurnos() {
		
		List<TurnoDTO> listTurnoDTO = new ArrayList<TurnoDTO>();
		for (Turno t : turnoRepository.findAll() ) {
						
			listTurnoDTO.add(mapper.map(t, TurnoDTO.class));
		}
		return listTurnoDTO;
	}

	/**
	 * Busca turno por ID
	 * return TurnoDTO
	 */
	@Override
	public TurnoDTO buscarTurnoPorId(Long id) {
		
		TurnoDTO turnoDTO = new TurnoDTO();
		Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
		if(turnoEncontrado.isPresent()) {
			
			turnoDTO = mapper.map(turnoEncontrado.get(),TurnoDTO.class);
			
		} else {
			turnoDTO = null;
		}
		
		return turnoDTO;
	}
}

package ar.edu.unju.fi.poo.clinica.exceptions;

public class ModelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelException (String mensajeError) {
		super(mensajeError);
	}
}

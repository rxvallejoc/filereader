/**
 * 
 */
package com.obiectumclaro.file.reader.exception;

/**
 * @author fausto
 *
 */
public class ObjectValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectValidationException(String message) {
		super(message);
	}

	public ObjectValidationException(Throwable cause) {
		super(cause);
	}
}

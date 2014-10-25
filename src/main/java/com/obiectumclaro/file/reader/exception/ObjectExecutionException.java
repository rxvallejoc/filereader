/**
 * 
 */
package com.obiectumclaro.file.reader.exception;

/**
 * @author fausto
 *
 */
public class ObjectExecutionException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectExecutionException(String message) {
		super(message);
	}

	public ObjectExecutionException(Throwable cause) {
		super(cause);
	}
}

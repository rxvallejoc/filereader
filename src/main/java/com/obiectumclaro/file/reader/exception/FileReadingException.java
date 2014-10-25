/**
 * 
 */
package com.obiectumclaro.file.reader.exception;

/**
 * @author fausto
 *
 */
public class FileReadingException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileReadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileReadingException(String message) {
		super(message);
	}

	public FileReadingException(Throwable cause) {
		super(cause);
	}
}

/**
 * 
 */
package com.obiectumclaro.file.reader.exception;

/**
 * @author fausto
 *
 */
public class FileConvertionException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileConvertionException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileConvertionException(String message) {
		super(message);
	}

	public FileConvertionException(Throwable cause) {
		super(cause);
	}
}

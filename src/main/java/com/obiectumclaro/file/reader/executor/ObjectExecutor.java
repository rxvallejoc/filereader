/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.executor;

import java.util.Map;

import com.obiectumclaro.file.reader.exception.ObjectExecutionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public interface ObjectExecutor<T> {

	/**
	 * @param o
	 * @throws ObjectExecutionException la excepcion sera capturada pero el proceso de lectura continuara
	 */
	public void execute(T o) throws ObjectExecutionException;

	/**
	 * @param t
	 * @param aditionalInfo
	 * @throws ObjectExecutionException si se quiere detener el proceso de lectura del archivo, el proceso finaliza con una excepcion FileReadingException 
	 * 
	 */
	public void onConversionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException;

	/**
	 * @param t
	 * @param aditionalInfo
	 * @throws ObjectExecutionException si se quiere detener el proceso de lectura del archivo, el proceso finaliza con una excepcion FileReadingException 
	 * 
	 */
	public void onValidationError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException;

	/**
	 * @param t
	 * @param aditionalInfo
	 * @throws ObjectExecutionException si se quiere detener el proceso de lectura del archivo, el proceso finaliza con una excepcion FileReadingException
	 */
	public void onExecutionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException;

}

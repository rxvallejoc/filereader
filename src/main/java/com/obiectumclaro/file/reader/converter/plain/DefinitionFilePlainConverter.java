/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain;

import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public abstract class DefinitionFilePlainConverter<T> extends FilePlainConverter<T> {

	public T convert(Object o, T objectToConvert) throws FileConvertionException {
		try {
			String[] row = (String[]) o;
			return convert(row, objectToConvert);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException(String.format("El parametro para la conversion %s no es de tipo String[]", o.getClass()));
		}
	}

	protected abstract T convert(String[] row, T o) throws FileConvertionException;

}

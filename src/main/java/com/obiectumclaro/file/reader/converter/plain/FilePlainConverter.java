/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain;

import com.obiectumclaro.file.reader.converter.FileConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public abstract class FilePlainConverter<T> implements FileConverter<T> {

	@Override
	public T convert(Object o) throws FileConvertionException {
		try {
			String[] row = (String[]) o;
			return convert(row);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException(String.format("El parametro para la conversion %s no es de tipo String[]", o.getClass()));
		}
	}

	protected abstract T convert(String[] row) throws FileConvertionException;

}

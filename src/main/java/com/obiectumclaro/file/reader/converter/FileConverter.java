/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter;

import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public interface FileConverter<T> {

	public T convert(Object o) throws FileConvertionException;
}

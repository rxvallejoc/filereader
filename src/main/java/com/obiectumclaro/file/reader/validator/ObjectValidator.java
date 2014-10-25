/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.validator;

import com.obiectumclaro.file.reader.exception.ObjectValidationException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public interface ObjectValidator<T> {

	public void validate(T o) throws ObjectValidationException;
}

/**
 * 
 */
package com.obiectumclaro.file.reader.descriptor.interfaces;

import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author fausto
 * @param <T>
 *
 */

public interface FileDefinition<T> {

	String getColumnSeparator();

	String getName();

	Class<? extends ObjectValidator<T>> getValidatorClass();
}

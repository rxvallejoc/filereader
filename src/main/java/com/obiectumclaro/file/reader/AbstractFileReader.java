/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader;

import com.obiectumclaro.file.reader.converter.FileConverter;
import com.obiectumclaro.file.reader.executor.ObjectExecutor;
import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public abstract class AbstractFileReader<T> implements FileReader {

	protected FileConverter<T> converter;
	protected ObjectExecutor<T> executor;
	protected ObjectValidator<T> validator;

	public AbstractFileReader(ObjectExecutor<T> executor, FileConverter<T> converter) {
		this.executor = executor;
		this.converter = converter;
	}

}

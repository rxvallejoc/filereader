/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.impl;

import com.obiectumclaro.file.reader.descriptor.interfaces.FileDefinition;
import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileDefinitionBasic<T> implements FileDefinition<T> {

	private String columnSeparator;
	private String name;
	private Class<? extends ObjectValidator<T>> validatorClass;

	@SuppressWarnings("unchecked")
	public FileDefinitionBasic(String columnSeparator, String name, Class<? extends ObjectValidator<?>> validatorClass) {
		super();
		this.columnSeparator = columnSeparator;
		this.name = name;
		this.validatorClass = (Class<? extends ObjectValidator<T>>) validatorClass;
	}

	public String getColumnSeparator() {
		return columnSeparator;
	}

	public String getName() {
		return name;
	}

	public Class<? extends ObjectValidator<T>> getValidatorClass() {
		return validatorClass;
	}

}

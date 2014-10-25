/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.impl;

import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinition;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileColumnDefinitionBasic implements FileColumnDefinition {

	private int position;
	private String name;
	private String datePattern;
	private boolean optional;
	private String defaultValue;

	public FileColumnDefinitionBasic(int position, String name, String datePattern, boolean optional, String defaultValue) {
		super();
		this.position = position;
		this.name = name;
		this.datePattern = datePattern;
		this.optional = optional;
		this.defaultValue = defaultValue;
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public boolean isOptional() {
		return optional;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}

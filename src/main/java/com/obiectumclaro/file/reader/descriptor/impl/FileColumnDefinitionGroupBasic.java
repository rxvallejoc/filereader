/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.impl;

import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinitionGroup;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileColumnDefinitionGroupBasic implements FileColumnDefinitionGroup {

	private int[] positions;
	private String name;
	private String datePattern;
	private boolean optional;
	private String[] defaultValues;

	public FileColumnDefinitionGroupBasic(int[] positions, String name, String datePattern, boolean optional, String[] defaultValues) {
		super();
		this.positions = positions;
		this.name = name;
		this.datePattern = datePattern;
		this.optional = optional;
		this.defaultValues = defaultValues;
	}

	public int[] getPositions() {
		return positions;
	}

	public void setPositions(int[] positions) {
		this.positions = positions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public String[] getDefaultValues() {
		return defaultValues;
	}
}

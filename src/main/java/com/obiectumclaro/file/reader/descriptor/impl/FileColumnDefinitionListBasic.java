/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.impl;

import java.util.List;

import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinitionGroup;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinitionList;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileColumnDefinitionListBasic implements FileColumnDefinitionList {

	private List<FileColumnDefinitionGroup> groups;

	private Class<?> elementsClass;

	public FileColumnDefinitionListBasic(List<FileColumnDefinitionGroup> groups, Class<?> elementsClass) {
		super();
		this.groups = groups;
		this.elementsClass = elementsClass;
	}

	public List<FileColumnDefinitionGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<FileColumnDefinitionGroup> groups) {
		this.groups = groups;
	}

	public Class<?> getElementsClass() {
		return elementsClass;
	}

	public void setElementsClass(Class<?> elementsClass) {
		this.elementsClass = elementsClass;
	}
}

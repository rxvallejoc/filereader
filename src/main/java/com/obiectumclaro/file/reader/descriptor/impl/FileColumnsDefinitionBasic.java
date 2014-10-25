/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.impl;

import java.util.List;

import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinition;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnsDefinition;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileColumnsDefinitionBasic implements FileColumnsDefinition {

	private List<FileColumnDefinition> columnDefinitionList;

	public FileColumnsDefinitionBasic(List<FileColumnDefinition> columnDefinitionList) {
		super();
		this.columnDefinitionList = columnDefinitionList;
	}

	@Override
	public List<FileColumnDefinition> getColumnDefinitionList() {
		return columnDefinitionList;
	}

}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.obiectumclaro.util.reflect.FieldInfo;
import com.obiectumclaro.file.reader.descriptor.annotations.FCD;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDs;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnDefinitionBasic;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnsDefinitionBasic;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinition;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnsDefinition;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class ColumnsDefinitionField extends FieldInfo {

	private FileColumnsDefinition definition;
	private List<ColumnDefinitionField> columnDefinitionFieldList;

	public ColumnsDefinitionField(Field field, final FCDs fcds) {
		super(field);
		List<FileColumnDefinition> columnDefinitionList = new ArrayList<>();
		for (FCD fcd : fcds.value()) {
			columnDefinitionList
					.add(new FileColumnDefinitionBasic(fcd.position(), fcd.name(), fcd.datePattern(), fcd.optional(), fcd.defaultValue()));
		}
		setDefinition(new FileColumnsDefinitionBasic(columnDefinitionList));
	}

	public ColumnsDefinitionField(Field field, FileColumnsDefinition definition) {
		super(field);
		setDefinition(definition);
	}

	private void setDefinition(FileColumnsDefinition definition) {
		this.definition = definition;
		setColumnDefinitionFieldList();
	}

	private void setColumnDefinitionFieldList() {
		columnDefinitionFieldList = new ArrayList<>();
		for (FileColumnDefinition fcd : definition.getColumnDefinitionList()) {
			columnDefinitionFieldList.add(new ColumnDefinitionField(getChildField(fcd.getName()), fcd));
		}
	}

	public List<ColumnDefinitionField> getColumnDefinitionFieldList() {
		return columnDefinitionFieldList;
	}

	public FileColumnsDefinition getDefinition() {
		return definition;
	}

	public void addField(FileColumnDefinition fcd) {
		definition.getColumnDefinitionList().add(fcd);
		columnDefinitionFieldList.add(new ColumnDefinitionField(getChildField(fcd.getName()), fcd));
	}
}

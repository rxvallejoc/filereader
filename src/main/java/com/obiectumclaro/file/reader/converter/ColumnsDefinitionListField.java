/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.obiectumclaro.util.reflect.FieldInfo;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDGroup;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDList;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnDefinitionBasic;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnDefinitionGroupBasic;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnDefinitionListBasic;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinition;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinitionGroup;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinitionList;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class ColumnsDefinitionListField extends FieldInfo {

	private FileColumnDefinitionList definition;
	private Map<Integer, List<ColumnDefinitionField>> columnDefinitionMap;

	public ColumnsDefinitionListField(Field field, FCDList fcd) {
		super(field);
		List<FileColumnDefinitionGroup> groups = new ArrayList<>();
		for (FCDGroup g : fcd.group()) {
			groups.add(new FileColumnDefinitionGroupBasic(g.positions(), g.name(), g.datePattern(), g.optional(), g.defaultValues()));
		}
		setDefinition(new FileColumnDefinitionListBasic(groups, fcd.elementsClass()));
	}

	public ColumnsDefinitionListField(Field field, FileColumnDefinitionList definition) {
		super(field);
		setDefinition(definition);
	}

	private void setDefinition(FileColumnDefinitionList definition) {
		this.definition = definition;
		setColumnsDefinitionField();
	}

	private void setColumnsDefinitionField() {
		columnDefinitionMap = new HashMap<>();

		for (FileColumnDefinitionGroup g : definition.getGroups()) {
			for (int i = 0; i < g.getPositions().length; i++) {
				addField(g, i);
			}
		}
	}

	public void addField(FileColumnDefinitionGroup g, int i) {
		try {
			FileColumnDefinition cd = new FileColumnDefinitionBasic(g.getPositions()[i], g.getName(), g.getDatePattern(), g.isOptional(),
					getDefaultValue(g.getDefaultValues(), i));
			if (columnDefinitionMap.get(i) == null) {
				columnDefinitionMap.put(i, new ArrayList<ColumnDefinitionField>());
			}
			columnDefinitionMap.get(i).add(new ColumnDefinitionField(definition.getElementsClass().getDeclaredField(g.getName()), cd));
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(
					String.format("No existe la propiedad %s en la clase %s", g.getName(), definition.getElementsClass().getName()));
		}

	}

	private String getDefaultValue(String[] defaultValues, int i) {
		int j = i;
		if (defaultValues == null || defaultValues.length == 0) {
			return null;
		} else if (i >= defaultValues.length) {
			j = (i % defaultValues.length);
		}
		return defaultValues[j];

	}

	public Map<Integer, List<ColumnDefinitionField>> getColumnDefinitionMap() {
		return columnDefinitionMap;
	}

	public FileColumnDefinitionList getDefinition() {
		return definition;
	}

}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter;

import java.lang.reflect.Field;

import com.obiectumclaro.util.reflect.FieldInfo;
import com.obiectumclaro.file.reader.descriptor.annotations.FCD;
import com.obiectumclaro.file.reader.descriptor.impl.FileColumnDefinitionBasic;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileColumnDefinition;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class ColumnDefinitionField extends FieldInfo {

	private FileColumnDefinition definition;

	public ColumnDefinitionField(Field field, final FCD fcd) {
		this(field, new FileColumnDefinitionBasic(fcd.position(), fcd.name().length() == 0 ? field.getName() : fcd.name(), fcd.datePattern(),
				fcd.optional(), fcd.defaultValue()));
	}

	public ColumnDefinitionField(Field field, FileColumnDefinition definition) {
		super(field);
		this.definition = definition;
	}

	public FileColumnDefinition getDefinition() {
		return definition;
	}

}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.obiectumclaro.file.reader.converter.ColumnsDefinitionListField;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.converter.plain.simple.CDFListFileConverter;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDList;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FCDListAnnotatedFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private CDFListFileConverter<T> delegate;

	public FCDListAnnotatedFileConverter(Class<T> type) {
		List<ColumnsDefinitionListField> fields = new ArrayList<>();
		for (Field field : type.getDeclaredFields()) {
			FCDList fcd = field.getAnnotation(FCDList.class);
			if (fcd != null) {
				fields.add(new ColumnsDefinitionListField(field, fcd));
			}
		}
		delegate = new CDFListFileConverter<>(type, fields);
	}

	@Override
	protected T convert(String[] row) throws FileConvertionException {
		return delegate.convert(row);
	}

	@Override
	protected T convert(String[] row, T o) throws FileConvertionException {
		return delegate.convert(row, o);
	}

}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.obiectumclaro.file.reader.converter.ColumnDefinitionField;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.converter.plain.simple.CDFFileConverter;
import com.obiectumclaro.file.reader.descriptor.annotations.FCD;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FCDAnnotatedFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private CDFFileConverter<T> delegate;

	public FCDAnnotatedFileConverter(Class<T> type) {
		List<ColumnDefinitionField> fields = new ArrayList<>();
		for (Field field : type.getDeclaredFields()) {
			FCD fcd = field.getAnnotation(FCD.class);
			if (fcd != null) {
				fields.add(new ColumnDefinitionField(field, fcd));
			}
		}
		delegate = new CDFFileConverter<>(type, fields);
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

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.obiectumclaro.file.reader.converter.ColumnsDefinitionField;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.converter.plain.simple.CDFsFileConverter;
import com.obiectumclaro.file.reader.descriptor.annotations.FCDs;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FCDsAnnotatedFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private CDFsFileConverter<T> delegate;

	public FCDsAnnotatedFileConverter(Class<T> type) {
		List<ColumnsDefinitionField> fields = new ArrayList<>();
		for (Field field : type.getDeclaredFields()) {
			FCDs fcd = field.getAnnotation(FCDs.class);
			if (fcd != null) {
				fields.add(new ColumnsDefinitionField(field, fcd));
			}
		}
		delegate = new CDFsFileConverter<>(type, fields);
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

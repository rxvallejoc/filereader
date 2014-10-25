/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.simple;

import java.util.ArrayList;
import java.util.List;

import com.obiectumclaro.file.reader.converter.ColumnDefinitionField;
import com.obiectumclaro.file.reader.converter.ColumnsDefinitionField;
import com.obiectumclaro.file.reader.converter.ColumnsDefinitionListField;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CompositeFieldConverter<T> extends DefinitionFilePlainConverter<T> {

	private Class<T> type;
	private List<DefinitionFilePlainConverter<T>> converters;

	public CompositeFieldConverter(Class<T> type, List<ColumnDefinitionField> CDFields, List<ColumnsDefinitionField> CDsFields,
			List<ColumnsDefinitionListField> CDListFields) {
		this.type = type;
		converters = new ArrayList<>();
		converters.add(new CDFFileConverter<>(type, CDFields));
		converters.add(new CDFsFileConverter<>(type, CDsFields));
		converters.add(new CDFListFileConverter<>(type, CDListFields));
	}

	@Override
	protected T convert(String[] row) throws FileConvertionException {
		T o;
		try {
			o = type.newInstance();
			return convert(row, o);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(String.format("La clase %s debe tener un constructor publico sin argumentos", type.getName()));
		}
	}

	@Override
	protected T convert(String[] row, T o) throws FileConvertionException {
		for (DefinitionFilePlainConverter<T> converter : converters) {
			o = converter.convert(row, o);
		}
		return o;
	}
}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.simple;

import java.util.List;

import com.obiectumclaro.file.reader.converter.ColumnsDefinitionField;
import com.obiectumclaro.file.reader.converter.FileConverter;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CDFsFileConverter<T> extends DefinitionFilePlainConverter<T> {
	private Class<T> type;
	private List<ColumnsDefinitionField> fields;

	public CDFsFileConverter(Class<T> type, List<ColumnsDefinitionField> fields) {
		this.type = type;
		this.fields = fields;
	}

	@Override
	protected T convert(String[] row) throws FileConvertionException {
		try {
			T o = type.newInstance();
			return convert(row, o);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(String.format("La clase %s debe tener un constructor publico sin argumentos", type.getName()));
		}
	}

	@Override
	protected T convert(String[] row, T o) throws FileConvertionException {
		for (ColumnsDefinitionField f : fields) {
			FileConverter<?> converter = new CDFFileConverter<>(f.getType(), f.getColumnDefinitionFieldList());
			Object so = converter.convert(row);
			f.invokeSetter(o, so);
		}
		return o;
	}

}

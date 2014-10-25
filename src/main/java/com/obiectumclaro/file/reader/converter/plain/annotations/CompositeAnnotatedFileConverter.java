/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.annotations;

import java.util.Arrays;
import java.util.List;

import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CompositeAnnotatedFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private Class<T> type;
	private List<DefinitionFilePlainConverter<T>> converters;

	public CompositeAnnotatedFileConverter(Class<T> type) {
		this(type, new FCDAnnotatedFileConverter<>(type), new FCDsAnnotatedFileConverter<>(type), new FCDListAnnotatedFileConverter<>(type));
	}

	@SafeVarargs
	public CompositeAnnotatedFileConverter(Class<T> type, DefinitionFilePlainConverter<T>... converters) {
		this.type = type;
		if (converters != null) {
			this.converters = Arrays.asList(converters);
		}
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

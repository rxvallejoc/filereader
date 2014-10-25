/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.simple;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import com.obiectumclaro.util.conversion.CreationCollections;
import com.obiectumclaro.util.exception.ParsingException;
import com.obiectumclaro.file.reader.converter.ColumnDefinitionField;
import com.obiectumclaro.file.reader.converter.ColumnsDefinitionListField;
import com.obiectumclaro.file.reader.converter.FileConverter;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CDFListFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private Class<T> type;
	private List<ColumnsDefinitionListField> fields;

	public CDFListFileConverter(Class<T> type, List<ColumnsDefinitionListField> fields) {
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
		for (ColumnsDefinitionListField f : fields) {
			Collection<Object> collection = createCollection(f);
			for (Entry<Integer, List<ColumnDefinitionField>> e : f.getColumnDefinitionMap().entrySet()) {
				FileConverter<?> converter = new CDFFileConverter<>(f.getDefinition().getElementsClass(), e.getValue());
				Object objectCollection = converter.convert(row);
				collection.add(objectCollection);
			}
			f.invokeSetter(o, collection);
		}
		return o;
	}

	private Collection<Object> createCollection(ColumnsDefinitionListField f) throws FileConvertionException {
		try {
			return CreationCollections.createCollection(f.getType(), Object.class);
		} catch (ParsingException e) {
			throw new FileConvertionException(String.format("No se puede crear una Coleccion de objetos para la propiedad %s de la clase %s",
					f.getName(), f.getClassType()));
		}
	}

}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.converter.plain.simple;

import java.util.List;

import com.obiectumclaro.util.conversion.ConversionBasicDataType;
import com.obiectumclaro.util.exception.ParsingException;
import com.obiectumclaro.file.reader.converter.ColumnDefinitionField;
import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 * Column Definition Field Converter 
 * Convierte un arreglo de String en objeto de clase T utilizando la definicion de los campos de la clase T
 */
public class CDFFileConverter<T> extends DefinitionFilePlainConverter<T> {

	private Class<T> type;
	private List<ColumnDefinitionField> fields;

	public CDFFileConverter(Class<T> type, List<ColumnDefinitionField> fields) {
		super();
		this.fields = fields;
		this.type = type;
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
		for (ColumnDefinitionField f : fields) {
		    	String strValue ="";
		    	if(f.getDefinition().getPosition() < row.length){
			  strValue = f.getDefinition().getPosition() >= 0 ? row[f.getDefinition().getPosition()] : "";
		    	}
			
			strValue = strValue.length() == 0 ? f.getDefinition().getDefaultValue() : strValue;
			setValue(o, f, strValue);
		}
		return o;
	}

	private void setValue(T o, ColumnDefinitionField f, String strValue) throws FileConvertionException {
		try {
			if (strValue != null && strValue.length() > 0) {
				Object value = ConversionBasicDataType.convertFromString(f.getType(), strValue, f.getDefinition().getDatePattern());
				f.invokeSetter(o, value);
			} else if (!f.getDefinition().isOptional()) {
				throw new FileConvertionException(String.format("El campo %s es obligatorio para crear %s", f.getDefinition().getName(),
						type.getName()));
			}
		} catch (ParsingException e) {
			throw new FileConvertionException(String.format("Error en la conversion de la propiedad %s para el valor %s en la clase %s", f
					.getDefinition().getName(), strValue, type.getName()));
		}
	}

}

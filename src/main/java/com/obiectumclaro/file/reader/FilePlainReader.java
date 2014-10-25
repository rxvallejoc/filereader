/**
 * 
 */
package com.obiectumclaro.file.reader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.obiectumclaro.file.reader.converter.plain.FilePlainConverter;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileDefinition;
import com.obiectumclaro.file.reader.exception.FileConvertionException;
import com.obiectumclaro.file.reader.exception.FileReadingException;
import com.obiectumclaro.file.reader.exception.ObjectExecutionException;
import com.obiectumclaro.file.reader.exception.ObjectValidationException;
import com.obiectumclaro.file.reader.executor.ObjectExecutor;
import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author fausto
 * @param <T>
 *
 */
public abstract class FilePlainReader<T> extends AbstractFileReader<T> {

	private byte[] file;
	private FileDefinition<T> definition;

	public FilePlainReader(byte[] file, ObjectExecutor<T> executor, FilePlainConverter<T> converter) {
		super(executor, converter);
		this.file = file;
		if (this.file == null) {
			throw new IllegalArgumentException("El archivo debe ser diferente de null");
		}
	}

	private void setProperties() {
		if (definition == null) {
			definition = getDefinition();
		}
		if (validator == null) {
			validator = getValidator();
		}
	}

	@Override
	public void read() throws FileReadingException {
		setProperties();

		String line;
		String[] row;

		int rowNumber = -1;

		try (InputStream is = new ByteArrayInputStream(file);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				rowNumber++;
				row = line.split(definition.getColumnSeparator());
				try {
					T o = converter.convert(row);
					validator.validate(o);
					executor.execute(o);
				} catch (FileConvertionException e) {
					onConversionError(e, rowNumber);
				} catch (ObjectValidationException e) {
					onValidationError(e, rowNumber);
				} catch (ObjectExecutionException e) {
					onExecutionError(e, rowNumber);
				}
			}
		} catch (IOException e) {
			throw new FileReadingException(String.format("Error al leer el archivo %s, Error: %s", definition.getName(), e.getMessage()));
		}
	}

	private void onConversionError(FileConvertionException e, int rowNumber) throws FileReadingException {
		Map<String, Object> info = new HashMap<>();
		info.put("rowNumber", rowNumber);
		try {
			executor.onConversionError(e, info);
		} catch (ObjectExecutionException oee) {
			throw new FileReadingException(String.format("Error en onConversionError, Error: %s", oee.getMessage()));
		}
	}

	private void onValidationError(ObjectValidationException e, int rowNumber) throws FileReadingException {
		Map<String, Object> info = new HashMap<>();
		info.put("rowNumber", rowNumber);
		try {
			executor.onValidationError(e, info);
		} catch (ObjectExecutionException oee) {
			throw new FileReadingException(String.format("Error en onValidationError, Error: %s", oee.getMessage()));
		}
	}

	private void onExecutionError(ObjectExecutionException e, int rowNumber) throws FileReadingException {
		Map<String, Object> info = new HashMap<>();
		info.put("rowNumber", rowNumber);
		try {
			executor.onExecutionError(e, info);
		} catch (ObjectExecutionException oee) {
			throw new FileReadingException(String.format("Error en onExecutionError, Error: %s", oee.getMessage()));
		}
	}

	protected ObjectValidator<T> getValidator() {
		try {
			return definition.getValidatorClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(String.format("No se pudo crear una instancia del validador para % ", definition.getName()));
		}
	}

	protected abstract FileDefinition<T> getDefinition();
}

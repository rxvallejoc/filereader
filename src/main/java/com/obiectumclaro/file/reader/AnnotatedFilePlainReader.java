/**
 * 
 */
package com.obiectumclaro.file.reader;

import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.converter.plain.annotations.CompositeAnnotatedFileConverter;
import com.obiectumclaro.file.reader.descriptor.annotations.FD;
import com.obiectumclaro.file.reader.descriptor.impl.FileDefinitionBasic;
import com.obiectumclaro.file.reader.descriptor.interfaces.FileDefinition;
import com.obiectumclaro.file.reader.executor.ObjectExecutor;

/**
 * @author Fausto De La Torre
 *
 */
public class AnnotatedFilePlainReader<T> extends FilePlainReader<T> {

	private FD definition;

	public AnnotatedFilePlainReader(byte[] file, ObjectExecutor<T> executor, Class<T> type) {
		this(file, executor, type, new CompositeAnnotatedFileConverter<T>(type));
	}

	public AnnotatedFilePlainReader(byte[] file, ObjectExecutor<T> executor, Class<T> type, DefinitionFilePlainConverter<T> converter) {
		super(file, executor, converter);
		definition = type.getAnnotation(FD.class);
		if (definition == null) {
			throw new IllegalArgumentException(String.format("La clase %s debe ser anotada como @FileDefinition", type.getName()));
		}
	}

	@Override
	protected FileDefinition<T> getDefinition() {
		return new FileDefinitionBasic<>(definition.columnSeparator(), definition.name(), definition.validator());
	}
}

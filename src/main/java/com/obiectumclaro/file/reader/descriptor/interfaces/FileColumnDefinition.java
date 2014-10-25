/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.interfaces;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public interface FileColumnDefinition {

	int getPosition();

	String getName();

	String getDatePattern();

	boolean isOptional();

	String getDefaultValue();
}

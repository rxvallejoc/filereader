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
public interface FileColumnDefinitionGroup {

	boolean isOptional();

	int[] getPositions();

	String getName();

	String getDatePattern();

	String[] getDefaultValues();
}

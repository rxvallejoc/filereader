/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.interfaces;

import java.util.List;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public interface FileColumnDefinitionList {

	List<FileColumnDefinitionGroup> getGroups();

	Class<?> getElementsClass();
}

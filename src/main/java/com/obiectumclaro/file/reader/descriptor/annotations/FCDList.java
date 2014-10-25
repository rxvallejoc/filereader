/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.descriptor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 * 
 * File Columns Definition List
 * 
 * Sirve para anotar una propiedad que defina una lista de objetos de clases complejas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FCDList {

	FCDGroup[] group();

	Class<?> elementsClass();
}

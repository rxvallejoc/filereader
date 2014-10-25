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
 * File Columns Definition
 * 
 * Sirve para anotar una propiedad de clase compleja que no sea una lista
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FCDs {

	FCD[] value();
}

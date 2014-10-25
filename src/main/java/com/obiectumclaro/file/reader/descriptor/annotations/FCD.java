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
 * File Column Definition
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FCD {

	int position();

	String name() default "";

	String datePattern() default "dd-MM-yyyy";

	boolean optional() default false;

	String defaultValue() default "";
}

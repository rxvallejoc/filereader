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
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FCDGroup {

	int[] positions();

	String name();

	String datePattern() default "dd/MM/yyyy";

	boolean optional() default false;

	String[] defaultValues() default {};
}

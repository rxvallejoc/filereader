/**
 * 
 */
package com.obiectumclaro.file.reader.descriptor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author fausto
 * 
 * File Definition
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FD {

	String columnSeparator();

	String name();

	Class<? extends ObjectValidator<?>> validator();
}

/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test.classes;

import com.obiectumclaro.file.reader.exception.ObjectValidationException;
import com.obiectumclaro.file.reader.validator.ObjectValidator;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CustomerValidator implements ObjectValidator<Customer> {

	@Override
	public void validate(Customer o) throws ObjectValidationException {
		if (o.getId() != null && o.getId().compareTo("validErrorSimple") == 0) {
			throw new ObjectValidationException("validErrorSimple");
		}
		if (o.getId() != null && o.getId().compareTo("validErrorFatal") == 0) {
			throw new ObjectValidationException("validErrorFatal");
		}

	}

}

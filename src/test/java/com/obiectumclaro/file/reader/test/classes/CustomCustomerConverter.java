/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test.classes;

import com.obiectumclaro.file.reader.converter.plain.DefinitionFilePlainConverter;
import com.obiectumclaro.file.reader.exception.FileConvertionException;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CustomCustomerConverter extends DefinitionFilePlainConverter<Customer> {

	@Override
	protected Customer convert(String[] row, Customer o) throws FileConvertionException {
		o.setPk(1L);
		o.setName("Custom Name");

		return o;
	}

	@Override
	protected Customer convert(String[] row) throws FileConvertionException {
		Customer c = new Customer();
		return convert(row, c);
	}

}

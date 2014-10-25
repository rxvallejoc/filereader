/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.obiectumclaro.file.reader.AnnotatedFilePlainReader;
import com.obiectumclaro.file.reader.FileReader;
import com.obiectumclaro.file.reader.converter.plain.annotations.CompositeAnnotatedFileConverter;
import com.obiectumclaro.file.reader.converter.plain.annotations.FCDAnnotatedFileConverter;
import com.obiectumclaro.file.reader.converter.plain.annotations.FCDListAnnotatedFileConverter;
import com.obiectumclaro.file.reader.converter.plain.annotations.FCDsAnnotatedFileConverter;
import com.obiectumclaro.file.reader.exception.FileReadingException;
import com.obiectumclaro.file.reader.test.classes.CustomCustomerConverter;
import com.obiectumclaro.file.reader.test.classes.Customer;
import com.obiectumclaro.file.reader.test.classes.CustomerExecutor;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 * 
 * Aqui se ve reflejado el patron bridge, se puede utilizar cualquier implementación del converter que se tenga en el reader
 * De esta forma se puede tener implementaciones propias que no se encuentren dentro del artefacto fileReader y pasarlas
 * al reader 
 *
 */
public class FileReaderConverterTest {

	private StringBuilder textFile;
	private byte[] file;

	private String templateRow = "%s,Cliente%s,Telefono%s,%s,9%s,15/%s/1995,calle prinicpal %s,la que cruza %s,%s,TEL,t%s%s%s,CEL,c%s%s%s,EMAIL,e%s%s%s,valor";

	@Before
	public void setUp() throws Exception {
		textFile = new StringBuilder();
		for (int i = 0; i < 1; i++) {
			textFile.append("\n");
			textFile.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		}
		textFile.replace(0, 1, "");
		this.file = textFile.toString().getBytes();
	}

	/**
	 * Se procesa solo las anotaciones FCD
	 */
	@Test
	public void shouldExecuteOnlyFCD() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class, new FCDAnnotatedFileConverter<>(Customer.class));
		try {
			fr.read();
			assertNull(executor.lastCustomerExecuted.getAddress());
			assertNull(executor.lastCustomerExecuted.getContacts());
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se procesa solo las anotaciones FCDs
	 */
	@Test
	public void shouldExecuteOnlyFCDs() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class, new FCDsAnnotatedFileConverter<>(Customer.class));
		try {
			fr.read();
			assertNotNull(executor.lastCustomerExecuted.getAddress());
			assertNull(executor.lastCustomerExecuted.getContacts());
			assertNull(executor.lastCustomerExecuted.getAge());
			assertNull(executor.lastCustomerExecuted.getAmount());
			assertNull(executor.lastCustomerExecuted.getBirthDate());
			assertNull(executor.lastCustomerExecuted.getId());
			assertNull(executor.lastCustomerExecuted.getName());
			assertNull(executor.lastCustomerExecuted.getTelephone());

		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se procesa solo las anotaciones FCDList
	 */
	@Test
	public void shouldExecuteOnlyFCDList() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class, new FCDListAnnotatedFileConverter<>(Customer.class));
		try {
			fr.read();
			assertNotNull(executor.lastCustomerExecuted.getContacts());
			assertNull(executor.lastCustomerExecuted.getAddress());
			assertNull(executor.lastCustomerExecuted.getAge());
			assertNull(executor.lastCustomerExecuted.getAmount());
			assertNull(executor.lastCustomerExecuted.getBirthDate());
			assertNull(executor.lastCustomerExecuted.getId());
			assertNull(executor.lastCustomerExecuted.getName());
			assertNull(executor.lastCustomerExecuted.getTelephone());

		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se procesa solo las anotaciones FCD y FCDs
	 */
	@Test
	public void shouldExecuteOnlyFCDAndFCDs() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class, new CompositeAnnotatedFileConverter<>(Customer.class,
				new FCDAnnotatedFileConverter<>(Customer.class), new FCDsAnnotatedFileConverter<>(Customer.class)));
		try {
			fr.read();
			assertNull(executor.lastCustomerExecuted.getContacts());
			assertNotNull(executor.lastCustomerExecuted.getAddress());
			assertNotNull(executor.lastCustomerExecuted.getAge());
			assertNotNull(executor.lastCustomerExecuted.getAmount());
			assertNotNull(executor.lastCustomerExecuted.getBirthDate());
			assertNotNull(executor.lastCustomerExecuted.getId());
			assertNotNull(executor.lastCustomerExecuted.getName());
			assertNotNull(executor.lastCustomerExecuted.getTelephone());

		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se procesa solo las anotaciones FCD y FCDs
	 */
	@Test
	public void shouldExecuteAllAnnotations() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class);
		try {
			fr.read();
			assertNotNull(executor.lastCustomerExecuted.getContacts());
			assertNotNull(executor.lastCustomerExecuted.getAddress());
			assertNotNull(executor.lastCustomerExecuted.getAge());
			assertNotNull(executor.lastCustomerExecuted.getAmount());
			assertNotNull(executor.lastCustomerExecuted.getBirthDate());
			assertNotNull(executor.lastCustomerExecuted.getId());
			assertNotNull(executor.lastCustomerExecuted.getName());
			assertNotNull(executor.lastCustomerExecuted.getTelephone());

		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se procesa con el convertidor personalizado
	 */
	@Test
	public void shouldExecuteCustomConverter() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class, new CustomCustomerConverter());
		try {
			fr.read();
			assertEquals(executor.lastCustomerExecuted.getName(), "Custom Name");
			assertEquals(executor.lastCustomerExecuted.getPk(), new Long(1));
			assertNull(executor.lastCustomerExecuted.getContacts());
			assertNull(executor.lastCustomerExecuted.getAddress());
			assertNull(executor.lastCustomerExecuted.getAge());
			assertNull(executor.lastCustomerExecuted.getAmount());
			assertNull(executor.lastCustomerExecuted.getBirthDate());
			assertNull(executor.lastCustomerExecuted.getId());
			assertNull(executor.lastCustomerExecuted.getTelephone());

		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Se verifica el funcionamiento de la anotacion FCDList con valores por defecto
	 */
	@Test
	public void shouldExecuteDefaultValues() {
		int i = 1;
		String templateRow = "%s,Cliente%s,Telefono%s,%s,9%s,15/%s/1995,calle prinicpal %s,la que cruza %s,%s,TEL,t%s%s%s,CEL,c%s%s%s,EMAIL,e%s%s%s,valor%s";
		textFile.append("\n");
		textFile.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i, i));
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(textFile.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			assertEquals(executor.lastCustomerExecuted.getContacts2().size(), 4);
			assertEquals(executor.lastCustomerExecuted.getContacts2().get(0).getContactType(), "AAA");
			assertEquals(executor.lastCustomerExecuted.getContacts2().get(1).getContactType(), "CEL");
			assertEquals(executor.lastCustomerExecuted.getContacts2().get(2).getContactType(), "CCC");
			assertEquals(executor.lastCustomerExecuted.getContacts2().get(3).getContactType(), "OTRO");
			assertEquals(executor.lastCustomerExecuted.getContacts2().get(3).getContactValue(), "valor" + i);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}
}

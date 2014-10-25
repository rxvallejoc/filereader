/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.obiectumclaro.file.reader.AnnotatedFilePlainReader;
import com.obiectumclaro.file.reader.FileReader;
import com.obiectumclaro.file.reader.exception.FileReadingException;
import com.obiectumclaro.file.reader.test.classes.Customer;
import com.obiectumclaro.file.reader.test.classes.CustomerExecutor;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class FileReaderTest {

	private StringBuilder textFile;
	private byte[] file;

	private String templateRow = "%s,Cliente%s,Telefono%s,%s,9%s,15/%s/1995,calle prinicpal %s,la que cruza %s,%s,TEL,t%s%s%s,CEL,c%s%s%s,EMAIL,e%s%s%s,valor";

	@Before
	public void setUp() throws Exception {
		textFile = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			textFile.append("\n");
			textFile.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		}
		textFile.replace(0, 1, "");
		this.file = textFile.toString().getBytes();
	}

	/**
	 * Se procesa todo segun lo esperado 
	 */
	@Test
	public void shouldExecuteAllOK() {
		System.out.println(textFile);
		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(file, executor, Customer.class);
		try {
			fr.read();
			assertFalse(executor.errorExecution);
			assertFalse(executor.errorValidation);
			assertFalse(executor.errorConvertion);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Al agregar una linea con el campo id en blanco cuando debe ser obligatorio ((position = 0, optional = false)) 
	 * debe dar un error de validacion en la linea 10 (contando desde 0)
	 * pero no se debe detener la ejecucion de lectura del archivo
	 */
	@Test
	public void shouldNotStopReadingConvertionErrorObligationValue() {
		StringBuilder t = new StringBuilder(textFile);
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, "", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			assertTrue(executor.errorConvertion);
			assertTrue(executor.rowConvertionError == 10);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Al agregar una linea con el campo id en blanco cuando debe ser obligatorio ((position = 0, optional = false)) 
	 * debe dar un error de validacion en las lineas 10 a 19 (contando desde 0)
	 * se debe detener la ejecucion en la linea 15 (Esa es la condicion para detener la ejecucion en el metodo onConvertionError)
	 */
	@Test
	public void shouldStopReadingConvertionErrorObligationValueRow15() {
		StringBuilder t = new StringBuilder(textFile);
		int i = 10;
		for (i = 10; i < 20; i++) {
			t.append("\n");
			t.append(String.format(templateRow, "", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		}
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			fail("La lectura debio detenerse en la fila 15");
		} catch (FileReadingException e) {
			assertTrue(executor.rowConvertionError == 15);
		}
	}

	/**
	 * Al agregar una linea con el texto validErrorSimple, el validador arroja un error
	 * que es leido en el metodo onValidationError que no detiene la lectura del archivo
	 */
	@Test
	public void shouldNotStopReadingValidationErrorSimple() {
		StringBuilder t = new StringBuilder(textFile);
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			assertTrue(executor.errorValidation);
			assertTrue(executor.rowValidationError == 10);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Al agregar una linea con el texto validErrorSimple, el validador arroja un error
	 * que es leido en el metodo onValidationError que no detiene
	 * la lectura del archivo, sin embargo en la linea 12 al agregar una linea con el texto validErrorFatal
	 * es leido en el metodo onValidationError se detiene la lectura del archivo
	 */
	@Test
	public void shouldStopReadingValidationErrorFatal() {
		StringBuilder t = new StringBuilder(textFile);
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 11;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 12;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorFatal", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 13;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			fail("La lectura debio detenerse en la fila 12");
		} catch (FileReadingException e) {
			assertTrue(executor.errorValidation);
			assertTrue(executor.rowValidationError == 12);
		}
	}

	/**
	 * Al agregar una linea con el texto execErrorSimple, el ejecutor arroja un error
	 * que es leido en el metodo onExecutionError que no detiene la lectura del archivo
	 */
	@Test
	public void shouldNotStopReadingExecutionErrorSimple() {
		StringBuilder t = new StringBuilder(textFile);

		// linea valida que deberia ejecutarse
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		// linea con error que no deberia detener la lectura
		i = 11;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));

		// linea valida que deberia ejecutarse
		i = 12;
		t.append("\n");
		t.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			assertTrue(executor.errorExecution);
			assertTrue(executor.rowExecutionError == 11);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}

		assertTrue(executor.validExecutions == 12);
	}

	/**
	 * Al agregar una linea con el texto execErrorSimple, el validador arroja un error
	 * que es leido en el metodo onValidationError que no detiene
	 * la lectura del archivo, sin embargo en la linea 12 al agregar una linea con el texto execErrorFatal
	 * es leido en el metodo onValidationError se detiene la lectura del archivo
	 */
	@Test
	public void shouldStopReadingExecutionErrorFatal() {
		StringBuilder t = new StringBuilder(textFile);

		// linea con error que no deberia detener la lectura
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));

		// linea con error que no deberia detener la lectura
		i = 11;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));

		// linea con error donde deberia detener la lectura
		i = 12;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorFatal", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));

		// linea con error que ya no deberia leer
		i = 13;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));

		// linea valida que ya no se deberia leer
		i = 14;
		t.append("\n");
		t.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			fail("La lectura debio detenerse en la fila 12");
		} catch (FileReadingException e) {
			assertTrue(executor.errorExecution);
			assertTrue(executor.rowExecutionError == 12);
		}

		assertTrue(executor.validExecutions == 10);
	}

	/**
	 * Agregar multiples lineas con errores que no detienen la lectura del archivo
	 */
	@Test
	public void shouldNotStopReadingMultipleErrorSimple() {
		StringBuilder t = new StringBuilder(textFile);
		int i = 10;
		t.append("\n");
		t.append(String.format(templateRow, "", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 11;
		t.append("\n");
		t.append(String.format(templateRow, "validErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 12;
		t.append("\n");
		t.append(String.format(templateRow, "execErrorSimple", i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		i = 13;
		t.append("\n");
		t.append(String.format(templateRow, i, i, i, (i + 20), (i * 103.25), (i + 1), i, i, (i + 60), i, i, i, i, i, i, i, i, i));
		System.out.println(t);

		CustomerExecutor executor = new CustomerExecutor();
		FileReader fr = new AnnotatedFilePlainReader<Customer>(t.toString().getBytes(), executor, Customer.class);
		try {
			fr.read();
			assertTrue(executor.errorConvertion);
			assertTrue(executor.rowConvertionError == 10);
			assertTrue(executor.errorValidation);
			assertTrue(executor.rowValidationError == 11);
			assertTrue(executor.errorExecution);
			assertTrue(executor.rowExecutionError == 12);
		} catch (FileReadingException e) {
			fail(e.getMessage());
		}

		assertTrue(executor.validExecutions == 11);
	}

}

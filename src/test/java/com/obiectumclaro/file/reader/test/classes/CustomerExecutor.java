/**
 * obiectumclaro
 * 2013
 */
package com.obiectumclaro.file.reader.test.classes;

import java.util.Map;

import com.obiectumclaro.file.reader.exception.ObjectExecutionException;
import com.obiectumclaro.file.reader.executor.ObjectExecutor;

/**
 * @author Fausto De La Torre
 * 		   obiectumclaro
 *
 */
public class CustomerExecutor implements ObjectExecutor<Customer> {

	// Estas propiedades se aumentan solo para las pruebas
	public int validExecutions = 0;

	public boolean errorConvertion = false;
	public int rowConvertionError = -1;

	public boolean errorValidation = false;
	public int rowValidationError = -1;

	public boolean errorExecution = false;
	public int rowExecutionError = -1;

	public Customer lastCustomerExecuted;

	@Override
	public void execute(Customer o) throws ObjectExecutionException {
		System.out.println("ejecutando... Customer " + o);
		lastCustomerExecuted = o;
		if (o.getContacts() != null) {
			for (Contact c : o.getContacts()) {
				System.out.println("\tc: " + c);
			}
		}
		if (o.getContacts2() != null) {
			for (Contact c : o.getContacts2()) {
				System.out.println("\tc2: " + c);
			}
		}
		if (o.getId() != null && o.getId().compareTo("execErrorSimple") == 0) {
			throw new ObjectExecutionException("execErrorSimple");
		}
		if (o.getId() != null && o.getId().compareTo("execErrorFatal") == 0) {
			throw new ObjectExecutionException("execErrorFatal");
		}
		validExecutions++;
	}

	@Override
	public void onConversionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		errorConvertion = true;
		rowConvertionError = (Integer) aditionalInfo.get("rowNumber");
		if (rowConvertionError == 15) {
			System.out.println(String.format("Error de conversion la fila es %s SI se detiene la lectura del archivo, %s", rowConvertionError,
					t.getMessage()));
			throw new ObjectExecutionException(String.format("Error de conversion la fila es %s se detiene la lectura del archivo",
					rowConvertionError));
		}
		System.out.println(String.format("Error de conversion la fila es %s NO se detiene la lectura del archivo, %s", rowConvertionError,
				t.getMessage()));

	}

	@Override
	public void onValidationError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		errorValidation = true;
		rowValidationError = (Integer) aditionalInfo.get("rowNumber");
		System.out.println("onValidationError linea " + rowValidationError + " " + t.getMessage());
		if (t.getMessage().compareTo("validErrorFatal") == 0) {
			throw new ObjectExecutionException(String.format("Se detiene la lectura del archivo en la linea %s por error: %s", rowValidationError,
					t.getMessage()));
		}
	}

	@Override
	public void onExecutionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		errorExecution = true;
		rowExecutionError = (Integer) aditionalInfo.get("rowNumber");
		System.out.println("onExecutionError " + t.getMessage());
		if (t.getMessage().compareTo("execErrorFatal") == 0) {
			throw new ObjectExecutionException("Se detiene la lectura del archivo por: " + t.getMessage());
		}
	}

}

Para utilizar el lector de archivos fileReader se debe realizar lo siguiente:

Suponemos que el problema es leer un archivo plano, donde cada fila representa un objeto de determinada clase que tenemos en nuestro sistema.
vamos a suponer que esa clase es un cliente.

Suponemos que el archivo plano es separado por comas (,) y que además tiene la siguiente estructura
ruc(Texto),nombre(Texto),edad(Numero entero),fecha de nacimiento(Fecha),valor(NUmero decimal).
ej:
1569452125,Cliente A,20,15/01/1990,2336.55
1869361126,Cliente B,20,16/12/1990,45.55
0563245823,Cliente C,20,13/08/1990,123123.55
0169215446,Cliente D,20,14/06/1990,789.55
1769452127,Cliente E,20,19/05/1990,5675.55.

Lo primero que hay que hacer es crear una clase Cliente con los campos necesarios (con getters y setters)
ej:
class Cliente {
	private String ruc;
	private String nombre;
	private Integer edad;
	private Date fechaNac;
	private Double valor;
}

luego se debe crear una clase que implemente la interfaz ObjectValidator<T>
se debe implementar el metodo validate(T o) throws ObjectValidationException 
ej:
class CustomerValidator implements ObjectValidator<Cliente>{
	public void validate(Customer o) throws ObjectValidationException {
		// definir logica de validacion
		// si no cumple arrojar la excepcion
	}
}

después se debe crear una clase que implemente la interfaz ObjectExecutor<T>
se debe implmentar los siguientes metodos
public class CustomerExecutor implements ObjectExecutor<Cliente> {
	public void execute(Customer o) throws ObjectExecutionException {
		// Este es el metodo que se ejecutara luego de:
		//  1: leer una fila del archivo plano
		//	2: convertir en objeto de tipo Cliente
		//	3: pasar la validacion exitosamente
	}
	
	public void onConversionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		// Este metodo se ejecura si se produce cualquier error en la conversion de una fila del archivo
		// Si se arroja la excepcion ObjectExecutionException entonces se detendra la lectura de las filas restantes del archivo
		// aquí se puede ejecutar cualquier accion que se desee (log de errores, guardar error en la base, etc), 
		// si no se arroja ObjectExecutionException, la lectura de las siguientes lineas del archivo continua normalmente
	}
	
	public void onValidationError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		// Este metodo se ejecura si el validador arroja la excepcion ObjectValidationException
		// Si se arroja la excepcion ObjectExecutionException entonces se detendra la lectura de las filas restantes del archivo
		// aquí se puede ejecutar cualquier accion que se desee (log de errores, guardar error en la base, etc), 
		// si no se arroja ObjectExecutionException, la lectura de las siguientes lineas del archivo continua normalmente
	}
	
	public void onExecutionError(Throwable t, Map<String, Object> aditionalInfo) throws ObjectExecutionException {
		// Este metodo se ejecutara si en el metodo execute de esta clase se arroja la excepcion declarada.
		// Si se vuelve a arrojar la excepcion en este método se detendra la lectura de las filas restantes del archivo 
		// aquí se puede ejecutar cualquier accion que se desee (log de errores, guardar error en la base, etc), 
		// si no se arroja la excepcion, la lectura de las siguientes lineas del archivo continua normalmente
	}
}

El siguiente paso es anotar a la clase Cliente y a sus propiedades
ej:
@FD(columnSeparator = ",", name = "Cliente", validator = CustomerValidator.class)
class Cliente {
	@FCD(position = 0)
	private String ruc;
	@FCD(position = 1)
	private String nombre;
	@FCD(position = 2)
	private Integer edad;
	@FCD(position = 3, datePattern="dd/MM/yyyy")
	private Date fechaNac;
	@FCD(position = 4, optional=true)
	private Double valor;
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

la anotacion @FD (FileDefinition) define a cada fila del archivo (el separador y la clase que realizara la validacion), el nombre solo es descriptivo
la anotacion @FCD (FileColumnDefinition) define la metadata de cala columna de las filas del archivo

Las tres clases que fueron descritas se deben crear en cualquier aplicacion o libreria o lo que les de la gana.

El paso final es proceder a leer el archivo de la siguiente forma
ej:
{
 	...
 	byte[] archivo; // bytes del archivo plano
 	...
 	CustomerExecutor executor = new CustomerExecutor();
 	FileReader reader = new AnnotatedFilePlainReader<Cliente>(archivo, executor, Customer.class);
 	
 	try {
			fr.read();
			// todo OK
		} catch (FileReadingException e) {
			// si se detiene la lectura del archivo
		}
 }
 
 En el metodo execute del CustomerExecutor tenderemos un objeto de tipo cliente con valor en las propiedades anotadas
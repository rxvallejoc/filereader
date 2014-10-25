Suponemos que el archivo es más complejo aún, ahora a parte de todos los campos anteriores.
se tiene tambien columnas que representan los medios de contacto, pero son varios y queremos mapearlos en una lista
dentro de la clase cliente

El archivo tiene ahora aparte de todos los campos anteriores los siguientes campos
....., tipo medio contacto, valor, tipo medio contacto, valor, tipo medio contacto, valor, tipo medio contacto, valor.
ej:
1569452125,Cliente A,20,15/01/1990,2336.55,10 de agosto,1552,la que cruza,TEL,224545,CEL,548484847,EMAIL,asdasdasd,TEL2,324234234
1869361126,Cliente B,20,16/12/1990,45.55,guayaquil,1552,la que cruza,TEL,224545,CEL,548484847,EMAIL,asdasdasd,TEL2,324234234
0563245823,Cliente C,20,13/08/1990,123123.55,av colon,1552,la que cruza,TEL,224545,CEL,548484847,CEL2,12343423,EMAIL,sdfwerftert

para mapear esto debemos crear una clase llamada MedioContacto (a como quieran llamarle) con las propiedades necesarias.
A la clase MedioContacto no se le anota.
ej:
class MedioContacto{
	private String tipo;
	private String valor;
	private Integer numero;
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

Luego en la clase Cliente se aumenta una propiedad de tipo Collection, List o Set de medios de contacto(con getter y setter) y se anota con @FCDList.
La clase cliente quedaría de la siguiente manera

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
	@FCDs({ @FCD(name = "callePrincipal", position = 5), @FCD(name = "numero", position = 6), @FCD(name = "calleSecundaria", position = 7) })
	private Direccion direccion;
	
	@FCDList(elementsClass = MedioContacto.class, group = { @FCDGroup(name = "tipo", positions = { 8, 10, 12, 14 }), @FCDGroup(name = "contactValue", positions = { 9, 11, 13, 15 }) })
	private List<MedioContacto> contactos;
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

La anotación @@FCDList define el tipo de los elementos de la lista y un arreglo de @FCDGroup
la anotación @FCDGroup define la propiedad (de MedioContacto) y en las posiciones que se encuentran.

Para leer el archivo es la misma tontera que en el anterior ejemplo.

En el metodo execute del CustomerExecutor tenderemos un objeto de tipo cliente con una lista de Medios de Contacto de tamaño 4
El tamaño de la lista será definido de acuerdo a las posiciones que le mandemos a la anotación 
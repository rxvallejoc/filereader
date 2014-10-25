Suponemos que el archivo es más complejo, ahora a parte de todos los campos anteriores.
se tiene tambien columnas que representan la direccion del cliente, pero no se desea tener 
dentro de la misma clase cliente todas las propiedades sueltas, sino agruparlas en una clase llamada Direccion.

El archivo tiene ahora aparte de todos los campos anteriores los siguientes campos
....., calle principal(Texto),numero(Numero entero),calle Secundaria(Texto).
ej:
1569452125,Cliente A,20,15/01/1990,2336.55,10 de agosto,1552,la que cruza
1869361126,Cliente B,20,16/12/1990,45.55,guayaquil,1552,la que cruza
0563245823,Cliente C,20,13/08/1990,123123.55,av colon,1552,la que cruza

para mapear esto debemos crear una clase llamada Direccion (a como quieran llamarle) con las propiedades necesarias.
A la clase Direccion no se le anota.
ej:
class Direccion{
	private String callePrincipal;
	private String calleSecundaria;
	private Integer numero;
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

Luego en la clase Cliente se aumenta una propiedad direccion(con getter y setter) y se anota con @FCDs.
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
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

La anotación @FCDs define un arreglo de @FCD

Para leer el archivo es la misma tontera que en el anterior ejemplo.

En el metodo execute del CustomerExecutor tenderemos un objeto de tipo cliente con la propiedad direccion llena
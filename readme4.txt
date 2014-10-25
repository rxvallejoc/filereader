El mismo ejemplo anterior, pero ahora se define que las columnas 16, 17, 18 y 19 son tambien medios de contacto, 
y que los tipos de no estan definidos en el archivo sino que se dice que el 16 y 17 son FACEBOOK y el 18 y 19 TWITTER, y que además
si el campo de la columna 14 está vacío entonces ponga 'OTRO'

El archivo tiene ahora aparte de todos los campos anteriores los siguientes campos
....., facebook 1, facebook 2, twitter 1, twitter 2.
ej:
1569452125,Cliente A,20,15/01/1990,2336.55,10 de agosto,1552,la que cruza,TEL,224545,CEL,548484847,EMAIL,asdasdasd,TEL2,324234234,carelibro1 cliente1,carelibro2 c1, twt1 c1, twt2 c1
1869361126,Cliente B,20,16/12/1990,45.55,guayaquil,1552,la que cruza,TEL,224545,CEL,548484847,,asdasdasd,TEL2,324234234,carelibro1 cliente2,carelibro2 c2, twt1 c2, twt2 c2

En la clase Cliente en la anotacion @FCDList se aumenta defaultValues de la siguiente manera.
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
	
	@FCDList(elementsClass = MedioContacto.class, group = { @FCDGroup(name = "tipo", positions = { 8, 10, 12, 14, -1, -1, -1, -1 }, defaultValues={"", "", "", "OTRO", "FACEBOOK", "FACEBOOK", "TWITTER", "TWITTER"}), @FCDGroup(name = "contactValue", positions = { 9, 11, 13, 15, 16, 17, 18, 19 }) })
	private List<MedioContacto> contactos;
	
	// no importa si se tienen mas propiedades
	private String a, b, c;
}

El numero de defaultValues debe ser igual al numero de posiciones, es por eso que se aumenta -1 (menos 1) en las posiciones

En el metodo execute del CustomerExecutor tenderemos un objeto de tipo cliente con una lista de Medios de Contacto de tamaño 8
El tamaño de la lista será definido de acuerdo a las posiciones que le mandemos a la anotación 
# appFirmas

<p>Este proyecto re sealizó con la finalidad de implementar el proceso de firma y verificación de firma de archivos con java.</p>
<p>Para lograrlo se utilizó la API criptografica de java la cual nos permitió acceder a diferentes clases, interfaces y metodos para implementar a los requerimientos del programa</p>
<p>El programa genera un par de llaves publicas y privadas con las cuales se le asigna una firma digital a un archivo, para mas adelante utilizar dichas lllaves para verificar que el archivo recibido coincide con el archivo originalmente firmado</p>


### Estructura

<p>El programa cuenta con 3 clases: Main, Keys y Signer</p>

<p>La clase Main se encarga de iniciar la ejecución del programa llamando a los respectivos metodos de generacion de claves privadas y publicas</p>

<p>La clase Keys contiene los metodos para generar, exportar e importar las llaves publica y privada a 2 archivos de texto diferentes</p>

##### Ejemplo de generador de par de llaves

			>KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

<p>La clase Signer se encarga de asignar la la firma digital generada a un archivo especificado. Tambien permite emplear un metodo para verificar si el archivo previamente firmado corresponde a otro enviado por parametro</p>


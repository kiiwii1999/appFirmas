package main;


import java.util.Scanner;

import model.Keys;
import model.Signer;

public class Main {
	
	public static final String FILE_PATH = "./prueba.txt";

	public static void main(String[] args) {
		
		
		Keys keys = new Keys();
		
		
		Signer signer = new Signer();
		
		
		Scanner lector = new Scanner(System.in);
		
		
		System.out.println("Hola, bienvenido al firmador y verificador de firmas.\r\n"
							+ "Elija alguna de las siguientes opciones:   \r\n"
							+ "");
		System.out.println("1) Generar par de claves asimetricas. \r\n"
						  +"2) Firmar archivo. \r\n"
				          +"3) Verificar firma \r\n ");
		
		int option = lector.nextInt();
		
		if(option == 1) {
			keys.keyPairGenerator();
			keys.exportPrivatekey();
			keys.exportPublickey();
			System.out.println("Las claves han sido creadas con exito. Podras verlas en las siguientes rutas: \r\n"
							  +"Clave privada: " + Keys.PRIVATE_KEY_PATH +"\r\n"
							  +"CLave publica: " + Keys.PUBLIC_KEY_PATH +"\r\n");
			
			System.out.println("Ahora, si desea firmar un documento presione 1 de lo contrario presione 2 y saldra de este menu");
			int option2 = lector.nextInt();
			
			if(option2 == 1) {	
				
				System.out.println("Ingrese la ruta del archivo que contiene la clave privada.  \r\n"
					     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
				
				String filePath1 = lector.next();

				if(filePath1 != null || filePath1 != "") {					
					signer.sifnFile(keys.importPrivateKey(keys.PRIVATE_KEY_PATH), filePath1 , keys.getSecureRandom());
					System.out.println("La firma se a creado exitosamente \r\n"
							+"y se encuentra en la siguiente ruta: " + Signer.SIGNATURE_PATH);
				}
				
				System.out.println("Ahora, si desea verificar la firma presione 1 de lo contrario presione 2 y saldra de este menu");
				int option3 = lector.nextInt();
				if(option3 == 1) {
					
					System.out.println("Ingrese la ruta del archivo que contiene la clave publica.  \r\n"
						     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
					String publicKeyPath = lector.next();
					System.out.println("Ingrese la ruta del archivo que contiene la firma que desea evaluar.  \r\n"
						     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
					String signature = lector.next();
					System.out.println("Ingrese la ruta del  al que le desea verificar su firma.  \r\n"
						     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
					String filePath = lector.next();
					
					boolean verified = signer.verifySignature(keys.importPublicKey(publicKeyPath), signature, filePath);
					
					if(verified == true) {
						System.out.println("La firma coincide con el archivo");
					}else {
						System.out.println("La firma NO coincide con el archivo");
					}
					
				}
			}
			
		}else if(option == 2) {
			
			System.out.println("Ingrese la ruta del archivo que contiene la clave privada.  \r\n"
						     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
			String privateKeyPath = lector.nextLine();
			System.out.println("Ingrese la ruta del archivo que desea firmar.  \r\n"
				     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
			String filePath = lector.next();
			signer.sifnFile(keys.importPrivateKey(privateKeyPath), filePath, null);
			System.out.println("La firma se a creado exitosamente \r\n"
					          +"y se encuentra en la siguiente ruta: " + Signer.SIGNATURE_PATH);
			
		}else if(option == 3) {
			System.out.println("Ingrese la ruta del archivo que contiene la clave publica.  \r\n"
				     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
			String publicKeyPath = lector.next();
			System.out.println("Ingrese la ruta del archivo que contiene la firma que desea evaluar.  \r\n"
				     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
			String signature = lector.next();
			System.out.println("Ingrese la ruta del  al que le desea verificar su firma.  \r\n"
				     + "(prucure tener solo slash y NO back slash en la ruta) \r\n");
			String filePath = lector.next();
			
			boolean verified = signer.verifySignature(keys.importPublicKey(publicKeyPath), signature, filePath);
			
			if(verified == true) {
				System.out.println("La firma coincide con el archivo");
			}else {
				System.out.println("La firma NO coincide con el archivo");
			}
			
			
			
		}
		
		
		
		

	}

}

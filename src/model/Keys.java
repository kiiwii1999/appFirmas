package model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Keys {
	/**
	 * Constantes con las rutas hacia los archivos de las llaves privada y publica
	 */
	public static final String PRIVATE_KEY_PATH = "./keys/privateKey.txt";
	public static final String PUBLIC_KEY_PATH = "./keys/publicKey.txt";
	
	/**
	 * Atributos que representa la llave privada
	 */
	private PrivateKey privateKey;
	/**
	 * Atributos que representa la llave publica
	 */
	private PublicKey publicKey;
	/**
	 * Objeto que provee un numero aleatorio criptograficamente fuerte
	 */
	private SecureRandom secureRandom;
	
	public Keys() {
	}
	
	/**
	 * Metodo que retorna la llave privada
	 * @return privateKey - lave privada
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * Metodo que asigna la llave privada con base en un parametro 
	 * @param privateKey - la llave privada recibida que se debe asignar
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Metodo que retorna la llave publica
	 * @return publicKey - la llave publica
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * Metodo que asigna la llave publica con base en un parametro recibido
	 * @param publicKey - la llave publica que se debe asignar
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Metodo que retorna el numero aleatorio
	 * @return secureRandom - EL numero aleatorio
	 */
	public SecureRandom getSecureRandom() {
		return secureRandom;
	}
	
	/**
	 * Metodo que asigna el numero aleatorio con base en un parametro recibido
	 * @param secureRandom - el numero aleatorio recibido
	 */

	public void setSecureRandom(SecureRandom secureRandom) {
		this.secureRandom = secureRandom;
	}

	/**
	 * Metodo que se encarga de generar el par de llaves publica y privada
	 */
	public void keyPairGenerator() {
		try {
			
			secureRandom = new SecureRandom();
			
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que exporta la llave privada a un archivo de texto
	 */
	public void exportPrivatekey() {
		
		try {
			File privateFile = new File(PRIVATE_KEY_PATH);
			FileOutputStream fos = new FileOutputStream(privateFile);
			fos.write(privateKey.getEncoded());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	/**
	 * metodo que exporta la llave publica a un archivo de texto
	 */
	public void exportPublickey() {
		
		try {
			File publicFile = new File(PUBLIC_KEY_PATH);
			FileOutputStream fos = new FileOutputStream(publicFile);
			fos.write(publicKey.getEncoded());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Metodo que importa la llave privada desde un archivo de texto
	 * @param path - La ruta al archivo con la llave privada
	 * @return 
	 */
	
	public PrivateKey importPrivateKey(String path) {
		try {
			File privateFile = new File(path);
			FileInputStream fis = new FileInputStream(privateFile);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) privateFile.length()];
			dis.readFully(keyBytes);
			dis.close();
			fis.close();
			
			KeyFactory fac = KeyFactory.getInstance("DSA");
			
			return fac.generatePrivate( new PKCS8EncodedKeySpec(keyBytes) );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Metodo que se encarga de importar la llave publica desde un archivo de texto
	 * @param path - la ruta al archivo con llave publica
	 * @return
	 */
	
	public PublicKey importPublicKey(String path) {
		try {
			File publicFile = new File(path);
			FileInputStream fis = new FileInputStream(publicFile);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) publicFile.length()];
			dis.readFully(keyBytes);
			dis.close();
			fis.close();
			KeyFactory fac = KeyFactory.getInstance("DSA");
			
			return fac.generatePublic(new X509EncodedKeySpec(keyBytes));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	

}

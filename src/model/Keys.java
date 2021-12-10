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
	
	public static final String PRIVATE_KEY_PATH = "./keys/privateKey.txt";
	public static final String PUBLIC_KEY_PATH = "./keys/publicKey.txt";
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private SecureRandom secureRandom;
	
	public Keys() {
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public SecureRandom getSecureRandom() {
		return secureRandom;
	}

	public void setSecureRandom(SecureRandom secureRandom) {
		this.secureRandom = secureRandom;
	}

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

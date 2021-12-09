package model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class Signer {
	
	public static final String SIGNATURE_PATH = "./keys/signature.txt";
	
	private byte[] digitalSignature;
	
	
	public Signer() {
	}
	
	
	/***
	 * 
	 * @param privateKey
	 * @param filePath
	 * @param secureRandom
	 */
	public void sifnFile (PrivateKey privateKey, String filePath, SecureRandom secureRandom) {
		
		try {
			
			//creacion de la instancia de la firma
			Signature signature = Signature.getInstance("SHA256WithDSA");
			signature.initSign(privateKey, secureRandom);
			
			
			//se elabora la firma basada en los datos del archivo original
			signature.update(getBytesFromFile(filePath));
			digitalSignature = signature.sign();
			
			//se guarda la firma en un archivo tipo .txt
			File signFile = new File(SIGNATURE_PATH);
			FileOutputStream fos = new FileOutputStream(signFile);
			fos.write(digitalSignature);
			fos.close();
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/***
	 * 
	 * @param publicKey
	 * @param signaturePath
	 * @param filePath
	 * @return
	 */
	public boolean verifySignature(PublicKey publicKey, String signaturePath, String filePath) {
	
		try {
			//toma el archivo que contiene la forma y lee sus datos para guardarlos en la variable  signature
			byte[] signature = getBytesFromFile(signaturePath); 
			
			Signature signature2 = Signature.getInstance("SHA256WithDSA");
			signature2.initVerify(publicKey);
			signature2.update(getBytesFromFile(filePath));
			
			return signature2.verify(signature);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public byte[] getBytesFromFile(String path) {
		try {
			File dataFile = new File(path);
			FileInputStream fis;
			fis = new FileInputStream(dataFile);
			DataInputStream dis = new DataInputStream(fis);
			byte[] dataBytes = new byte[(int) dataFile.length()];
			dis.readFully(dataBytes);
			dis.close();
			fis.close();	
			return dataBytes;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}

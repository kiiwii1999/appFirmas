package main;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;


import model.Keys;

public class Main {
	
	
	
	
	public static void keygenerator() { 
	
		try {
//			SecureRandom securerRandom = new SecureRandom();
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
//			KeyPair keyPair = keyPairGenerator.generateKeyPair();
	
			Keys keys = new Keys();
			keys.keyPairGenerator();
			keys.exportPrivatekey();
			keys.exportPublickey();
			System.out.println("--------------- LLAVE FORMATO--------------------------");
			System.out.println(keys.getPrivateKey().getEncoded());
			System.out.println("--------------- LLAVE PRIVADA--------------------------");
			System.out.println(new String(keys.getPrivateKey().getEncoded()));
			System.out.println("--------------- LLAVE PUBLICA--------------------------");
			System.out.println(new String(keys.getPublicKey().getEncoded()));
	
			
//			File privatekey = new File("./keys/privateKey.txt");
			
			
//			FileOutputStream outF = new FileOutputStream(privatekey);
//			outF.write(keyPair.getPrivate().getEncoded());
			
//			File f = new File("./keys/privateKey.txt");
//	        FileInputStream fis = new FileInputStream(f);
//	        DataInputStream dis = new DataInputStream(fis);
//	        byte[] keyBytes = new byte[(int) f.length()];
//	        dis.readFully(keyBytes);
//	        dis.close();
	        
	        
//	        KeyFactory fact2 = KeyFactory.getInstance("DSA");
//	        PrivateKey pKey = fact2.generatePrivate( new PKCS8EncodedKeySpec(keyBytes) );
			
			Signature signature = Signature.getInstance("SHA256WithDSA");

			signature.initSign(keys.importPrivateKey(Keys.PRIVATE_KEY_PATH), keys.getSecureRandom());
			
			byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
			signature.update(data);
			
			byte[] digitalSignature = signature.sign();


			Signature signature2 = Signature.getInstance("SHA256WithDSA");
			signature2.initVerify(keys.importPublicKey(Keys.PUBLIC_KEY_PATH));// aqui se agrega el archivo (proceso) de la llave publica para la opcion 3

			signature2.update(data);// aqui se agrega el archivo original en la opcion 3

			boolean verified = signature2.verify(digitalSignature);// aqui se agrega el archivo firmado para ser verificado en la opcion 3

			System.out.println("verified = " + verified);

			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		keygenerator();

	}

}

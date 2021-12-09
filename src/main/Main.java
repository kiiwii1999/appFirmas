package main;


import model.Keys;
import model.Signer;

public class Main {
	
	public static final String FILE_PATH = "./prueba.txt";

	public static void main(String[] args) {
		Keys keys = new Keys();
		keys.keyPairGenerator();
		keys.exportPrivatekey();
		keys.exportPublickey();
		
		Signer signer = new Signer();
//		signer.sifnFile(keys.importPrivateKey(keys.PRIVATE_KEY_PATH), FILE_PATH , keys.getSecureRandom());
		boolean verified = signer.verifySignature(keys.importPublicKey(Keys.PUBLIC_KEY_PATH), Signer.SIGNATURE_PATH, FILE_PATH);
		System.out.println("verified = " + verified);

	}

}

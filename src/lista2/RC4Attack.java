package lista2;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RC4Attack {
	
	private final static byte[] candidates = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
	
	public static void main(String[] args) throws Exception {
		String[] ciphertext;
		try (BufferedReader br = new BufferedReader(new FileReader("zad2.txt"))) {
		       ciphertext = br.readLine().split(" ");
		}
		
		byte[] ciphertextBytes = new byte[ciphertext.length];
		for(int i = 0; i < ciphertextBytes.length; i++)
			ciphertextBytes[i] = (byte) Integer.parseInt(ciphertext[i], 2);
		
		int s, e, c, u, r, i, t, y;
		for(s = 0; s < 16; s++)
			for(e = 8; e < 16; e++)
				for(c = 9; c < 16; c++)
					for(u = 9; u < 16; u++)
						for(r = 9; r < 16; r++)
							for(i = 12; i < 16; i++)
								for(t = 4; t < 16; t++)
									for(y = 3; y < 16; y++) {
										byte[] key = {
												candidates[s], candidates[e], candidates[c], candidates[u],
												candidates[r], candidates[i], candidates[t], candidates[y],
												48, 53, 101, 101, 49, 99, 99, 50}; //05ee1cc2
										
										SecretKeySpec spec = new SecretKeySpec(key, "RC4");
										Cipher cipher = Cipher.getInstance("RC4");
										cipher.init(Cipher.DECRYPT_MODE, spec);
										String message = new String(cipher.update(ciphertextBytes), "ASCII");

										if (message.matches("[a-zA-Z0-9 ,.?!()\"'-]+")) {;
											System.out.println(new String(key, "ASCII")); //08999c4305ee1cc2
											System.out.println(message); //China Allegedly Arrested Hackers To Comply With The U.S. Government's Demands
											return;
										}
									}

	}
	
}

package lista2;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CiphertextOnlyAttack {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[][] ciphertexts = CiphertextReader.read("zad1.txt");
		String[] key = DecryptUtils.findSpaces(ciphertexts, ciphertexts.length, ciphertexts[0].length);
		
		key[0] = DecryptUtils.xor(DecryptUtils.xor(key[0], "01010001", 8), "01001110", 8);
		key[12] = "11101100";
		key[12] = DecryptUtils.xor(DecryptUtils.xor(key[12], "01000001", 8),"01101110", 8);
		key[15] = DecryptUtils.xor(DecryptUtils.xor(key[15], "01111000", 8), "01111010", 8);
		key[19] = DecryptUtils.xor(DecryptUtils.xor(key[19], "01100010", 8), "01101110", 8);

		StringBuilder message = new StringBuilder();

		for(int i = 0; i < ciphertexts.length; i++) {
			for(int j = 0; j < ciphertexts[0].length; j++) {
				message.append(DecryptUtils.bitsToText(DecryptUtils.xor(key[j], ciphertexts[i][j], key[j].length())));
			}
			message.append("\n");
		}
		
		System.out.println(message.toString());
	}
}
package lista2;

import java.util.HashMap;
import java.util.Map;

public class DecryptUtils {
	
	public static String[] findSpaces(String[][] ciphertexts, int rows, int columns) {
		String[] key = new String[columns];
		for(int i = 0; i < columns; i++) {
			char letter = determineLetterBit(ciphertexts, i, rows);		
			Map<String, Integer> incidence = new HashMap<String, Integer>();
			for(int j = 0; j < rows; j++) {
				if(ciphertexts[j][i].charAt(1) == letter)
					continue;
				if(incidence.containsKey(ciphertexts[j][i])) {
					incidence.put(ciphertexts[j][i], incidence.get(ciphertexts[j][i]) + 1);
				} else {
					incidence.put(ciphertexts[j][i], 1);
				}
			}
			
			Map.Entry<String, Integer> space = null;
			for (Map.Entry<String, Integer> entry : incidence.entrySet()) {
			    if (space == null || entry.getValue().compareTo(space.getValue()) > 0) {
			        space = entry;
			    }
			}

			if(space != null)
				key[i] = xor("00100000", space.getKey(), space.getKey().length());
			else
				key[i] = "00000000";
		}
		
		return key;
	}
	
	public static char determineLetterBit(String[][] ciphertexts, int column, int rows) {
		int zeroes = 0;
		int ones = 0;
		
		for(int i = 0; i < rows; i++) {
			if(ciphertexts[i][column].charAt(1) == '0')
				zeroes++;
			else
				ones++;
		}

		return ones > zeroes? '1': '0';
	}
	
	public static String xor(String a, String b, int length) {
		StringBuilder score = new StringBuilder();
		for(int i = 0; i < length; i++)
			score.append(a.charAt(i) == b.charAt(i)? '0' : '1');
			
		return score.toString();
	}
	
	public static String bitsToText(String bits) {
		int charValue = Integer.parseInt(bits, 2);
		return String.valueOf((char) (charValue));
	}
	
/*	public static String charToBits(char c) {
		return Integer.toString((int) c, 2);
	}*/
}

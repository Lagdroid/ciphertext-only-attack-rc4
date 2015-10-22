package lista2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CiphertextReader {
	
	public static String[][] read(String file) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    StringBuilder builder = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        builder.append(line);
		        builder.append("\n");
		        line = br.readLine();
		    }
		    String data = builder.toString();
			
		    String[] rows = data.split("\n");
		    int i = rows.length;
		    String[][] ciphertexts = new String[i][];
		    i = 0;
		    for(String row : rows)
		    	ciphertexts[i++] = row.split(" ");

			return ciphertexts;
		}
	}
}

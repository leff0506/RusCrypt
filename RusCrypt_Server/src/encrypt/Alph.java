package encrypt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Alph {
	public static String getAlph() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data_base/alphabet.txt")));
			return reader.readLine();
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

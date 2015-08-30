package project.swagger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SwaggerJson {
	
	public static void main(String [] args){
		//Create destination folder if not exists
		File folder = new File(args[1]);
		if (!folder.exists()) folder.mkdir();
		
		try {
			//Open connection to get swagger json
			URL url = new URL(args[0]);
			URLConnection conn = url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
		
			//Save to this file name
			File file = new File(args[0]+"/"+args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
		
			//Use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
		
			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
			}
		
			bw.close();
			br.close();
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Cannot create swagger json file");
		}
	}
}

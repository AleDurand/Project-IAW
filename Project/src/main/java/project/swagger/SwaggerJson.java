package project.swagger;

import java.io.File;

public class SwaggerJson {
	
	public static void main(String [] args){
		File file = new File(args[0]);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println(args[0]);
				System.out.println("Failed to create directory!");
			}
		}
	}
}

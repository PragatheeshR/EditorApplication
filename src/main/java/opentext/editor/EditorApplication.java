package opentext.editor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import opentext.editor.reader.FileReader;

public class EditorApplication {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String fileName = "dummy.txt";
		
		File file = new File(fileName);
		
		while(!file.exists() || !file.isFile() || !fileName.endsWith(".txt")) {
			if(!fileName.equals("dummy.txt") && (!file.exists() || !file.isFile())) {
				System.err.println("Please recheck and enter a valid File Path");
			}
			else if(!fileName.endsWith(".txt")) {
				System.err.println("Currently supports only .txt");
				System.err.println("Please enter a valid File Path");
			}
			else
			System.out.println("Enter the Valid Absolute file path");			
			 fileName = scanner.nextLine();
			file = new File(fileName);
		}
		List<String> list = new ArrayList<>();
		FileReader fileReader = new FileReader();
		Engine engine = new Engine();

		// reading the file
		list = fileReader.readFile(fileName);
		int lineNum = 1;

		// shows the data present in the file with line numbers
		engine.displayFileContents(list, lineNum);
		engine.processData(list, fileName);
		
		scanner.close();
		

	}

	

}

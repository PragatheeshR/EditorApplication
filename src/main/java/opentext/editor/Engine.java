package opentext.editor;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import opentext.editor.reader.FileWriterIDE;

public class Engine {
	
	Consumer<String> infoLogger = (data) -> System.out.println(data);
	Consumer<String> errorLogger = (data) -> System.err.println(data);

	public void processData(List<String> list, String fileName) {

		
		String command = "";
		FileWriterIDE fileWriterIDE = new FileWriterIDE();
		try(Scanner scanner = new Scanner(System.in);) {
			while (true) {

				infoLogger.accept("Commands to execute: \n del n - Deleting the line \n ins n - Inserting the line \n save \n show \n quit");
				lineSeperator();
				String commandLine = scanner.nextLine();
				String inputArray[] = commandLine.split(" ");
				int lineToUpdate = -1;
				command = inputArray[0];
				
				if(command.equalsIgnoreCase("del") || command.equalsIgnoreCase("ins")) {
					boolean isValid = validate(list, command, inputArray);
					if(isValid) {
						lineToUpdate = Integer.parseInt(inputArray[1]);
					}
					else {
						continue;
					}
				}
				
				

				switch (command) {
				case "ins":
					
					infoLogger.accept("Enter the text to be added");
					String text = scanner.nextLine();

					if (lineToUpdate - 1 >= list.size()) {
						for(int i = list.size(); i < lineToUpdate - 1; i++) {
							list.add(" ");
						}
						list.add(text);
					} else
						list.add(lineToUpdate - 1, text);
					
					infoLogger.accept("Line Added successfully");
					lineSeperator();
					break;

				case "del":
					if (lineToUpdate - 1 >= list.size()) {
						errorLogger.accept("Enter correct line number");
						lineSeperator();
						break;
					}
					list.remove(lineToUpdate - 1);
					
					infoLogger.accept("Line Deleted successfully");
					lineSeperator();
					break;

				case "save":
					fileWriterIDE.write(fileName, list);
					infoLogger.accept("Saved successfully");
					lineSeperator();
					break;
				case "show":
					displayFileContents(list, 1);
					lineSeperator();
					break;
				case "quit":
					infoLogger.accept("Exiting the Application.................");
					System.exit(0);
					break;
				default:
					infoLogger.accept("Enter proper Command");
					lineSeperator();
					break;
				}

			}
		} catch (NumberFormatException e) {
			if (!command.equalsIgnoreCase("save") && !command.equalsIgnoreCase("quit")) {
				System.err.println("Enter Valid Line Number");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Enter valid Arguments");
		} catch (Exception e) {
			System.out.println("Unexpected Error " + e);
			System.exit(1);
		}
	}

	private boolean validate(List<String> list, String command, String[] inputArray) {
		int lineToUpdate = -1;
		boolean valid = true;
			if(inputArray.length < 2) {
				errorLogger.accept("Please provide Line Number to run the command - del/ins");
				valid = false;
			}
			try {
			 lineToUpdate = Integer.parseInt(inputArray[1]);
			}
			catch(Exception e) {
				valid =  false;
			}
			if(!command.equalsIgnoreCase("ins") && (lineToUpdate - 1 >= list.size() || lineToUpdate - 1 <  0)) {
				errorLogger.accept("Enter Valid line number");
				valid = false;
			}
		
		return valid;
	}

	private void lineSeperator() {
		System.out.println("****************************** \n");
	}
	
	public void displayFileContents(List<String> list, int lineNum) {
		infoLogger.accept("/**********Start of the file***********/");
		for (String currLine : list) {
			infoLogger.accept(lineNum + ": " + currLine);
			lineNum++;
		}
		infoLogger.accept("/**********End of the file***********/ \n");
		//infoLogger.accept(" ");
	}

}

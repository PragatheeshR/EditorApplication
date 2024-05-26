package opentext.editor.reader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileWriterIDE {
	
	public void write(String fileName, List<String> data) {
		
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
			for(String line : data) {
				writer.write(line);
	            writer.newLine();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

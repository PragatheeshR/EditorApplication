package opentext.editor.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileReader {
	
	//private int lineNumbers = 1;
	
	
	public List<String> readFile(String fileName) {
		
		//Map<Integer, String> map = new TreeMap<>();
		List<String> list = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
               // map.put(lineNumbers, line);
                list.add(line);
               // lineNumbers++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return list;
		
	}

}

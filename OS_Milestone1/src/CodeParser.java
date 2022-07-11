import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

import java.io.*;
public class CodeParser {
	public CodeParser() {

	}
	public static ArrayList<String> parser(String path)throws Exception{

		String line;
		ArrayList<String> x = new ArrayList<String>();
		try {
			File myFile = new File(path); 
			BufferedReader reader = new BufferedReader(new FileReader(myFile));
			//BufferedReader bufferreader = new BufferedReader(new FileReader("C:\\Users\\Marwan\\Downloads\\OS_Milestone1\\OS_Milestone1\\Program_3.txt"));



			while ((line = reader.readLine()) != null) {
				x.add(line);
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return x;
	}
	
//	public static void main(String[] args) throws Exception {
//		Process p=new Process("test.txt");
//		execute(p.instructions.get(0),p);
//		
//		execute(p.instructions.get(1),p);
//		execute(p.instructions.get(2),p);
//
//	}




}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;



public class Program {
	ArrayList<String> instructions;
	static scheduler sched=new scheduler();
	static CodeParser parse=new CodeParser();
	int arriveTime;
	public Program(String path,int arrive) throws Exception {
		instructions=new ArrayList<String>();
		instructions=CodeParser.parser(path);
		this.sched=sched;
		arriveTime=arrive;
		this.sched.New.add(this);


	}

}

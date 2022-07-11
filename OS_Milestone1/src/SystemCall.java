import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class SystemCall {
	int pid;
	static Memory mem;

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Memory getMem() {
		return this.mem;
	}

	public void setMem(Memory mem) {
		this.mem = mem;
	}

	public SystemCall(int pid) {
		this.pid = pid;
	}

	public void assign(String s, Object o) {
		Variable temp;
		if (this.mem.readFrommem(0) !=null && (int)Integer.parseInt(""+mem.readFrommem(0))==this.pid) {
			for(int i=5;i<=7;i++) {
				if(this.mem.readFrommem(i) !=null  && !this.mem.readFrommem(i).equals("null")) {
					
					temp=((Variable)this.mem.readFrommem(i));
					if(temp.getVariable().equals(s)) {
						temp.setData(o);
						break;
					}
				}
				else {
					this.mem.writeTomem(new Variable(s,o),i);
		
					break;
				}
				
			}

		}
		else {
			
			if(this.mem.readFrommem(20) !=null && (int)Integer.parseInt(""+mem.readFrommem(20))==this.pid) {
				for(int i=25;i<=27;i++) {
					if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
					
						temp=(Variable)this.mem.readFrommem(i);
						if(temp.getVariable().equals(s)) {
							temp.setData(o);
						}
					}
					else {
						this.mem.writeTomem(new Variable(s,o),i);
						break;
					}
					
				}
			}
			
			
		}
		
		
		
		


	}

	public void printFromTo(Object f, Object l) {
		int x=0;
		int y=0;
		Variable temp;
		if (this.mem.readFrommem(0) !=null && (int)Integer.parseInt(""+mem.readFrommem(0))==this.pid) {
			for(int i=5;i<=7;i++) {
				if(this.mem.readFrommem(i) !=null) {
					temp=(Variable)this.mem.readFrommem(i);
					if(temp.getVariable().equals((String)f)) {
						x=(int)Integer.parseInt(temp.getData()+"");
					}
					if(temp.getVariable().equals((String)l)) {
						y=(int)Integer.parseInt(temp.getData()+"");
					}
				}
				
			}

		}
		else {
			
			if(this.mem.readFrommem(20) !=null && (int)Integer.parseInt(""+mem.readFrommem(20))==this.pid) {
				for(int i=25;i<=27;i++) {
					if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
						temp=(Variable)this.mem.readFrommem(i);
						if(temp.getVariable().equals((String)f)) {
							x=(int)Integer.parseInt(temp.getData()+"");
						}
						if(temp.getVariable().equals((String)l)) {
							y=(int)Integer.parseInt(temp.getData()+"");
						}
					}
					
				}
			}
			
			
		}
		for(int i=x;i<=y;i++) {
			System.out.println(i);
		}
		

	}

	public String readFile(Object o) {
        String path = "";
        Variable temp;
		
        if (this.mem.readFrommem(0) !=null && (int)Integer.parseInt(""+mem.readFrommem(0))==this.pid) {
		
            for(int i=5;i<=7;i++) {
                if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {

                    temp=(Variable)this.mem.readFrommem(i);
                    if(temp.getVariable().equals((String)o)) {
				
                        path=(String)temp.getData()+".txt";
                        break;
                    }
                }

            }

        }

        else if (this.mem.readFrommem(20) !=null && (int)Integer.parseInt(""+mem.readFrommem(20))==this.pid) {

		

            for(int i=25;i<=27;i++) {
                if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
				

                    temp=(Variable)this.mem.readFrommem(i);
                    if(temp.getVariable().equals((String)o)) {
                        path=(String)temp.getData()+".txt";
                        break;
                    }
                }

            }

        }
        if(((String)o).equals("Disk.txt")) {
            path=(String)o;
        }

        String line;

        String res = "";
        try {

            // BufferedReader bufferreader = new BufferedReader(new FileReader(path));
            File myFile = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            while ((line = reader.readLine()) != null) {
                res += line + "\n";

            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // System.out.println(res);

        return res;

    }
	


	public void writeFile(String path, String data) {
		
		Variable temp;
		if (this.mem.readFrommem(0) !=null && (int)Integer.parseInt(""+mem.readFrommem(0))==this.pid) {
		
			for(int i=5;i<=7;i++) {
				if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
					temp=(Variable)this.mem.readFrommem(i);
					if(temp.getVariable().equals(path)) {
						path=(String)temp.getData()+".txt";
					}
					if(temp.getVariable().equals(data)) {
						data=(String)temp.getData();
					}
					
				}
				
			}
		
		}
		
		else if (this.mem.readFrommem(20) !=null && (int)Integer.parseInt(""+mem.readFrommem(20))==this.pid) {

		
			for(int i=25;i<=27;i++) {
				if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
					
					temp=(Variable)this.mem.readFrommem(i);
					if(temp.getVariable().equals(path)) {
						path=(String)temp.getData()+".txt";
					}
					if(temp.getVariable().equals(data)) {
	
						data=(String)temp.getData();
					}
				}
				
			}
		
		}
		
		try {
			// File myObj = new File(path);
			FileWriter myWriter = new FileWriter(path);
			myWriter.write(data);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
	

	public String input() {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		System.out.print("Please enter a value  ");
		System.out.println();
		String res = sc.nextLine();

		return res;
	}

	public void print(Object o) {

		String print = "";
		Variable temp;
		if (this.mem.readFrommem(0) !=null && (int)Integer.parseInt(""+mem.readFrommem(0))==this.pid) {
			for(int i=5;i<=7;i++) {
				if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
					temp=(Variable)this.mem.readFrommem(i);
					if(temp.getVariable().equals((String )o)) {
						print=(String)temp.getData();
						break;
					}
				}
				
			}

		}
		else {
			
			if(this.mem.readFrommem(20) !=null && (int)Integer.parseInt(""+mem.readFrommem(20))==this.pid) {
				for(int i=25;i<=27;i++) {
					if(this.mem.readFrommem(i) !=null && !this.mem.readFrommem(i).equals("null")) {
						temp=(Variable)this.mem.readFrommem(i);
						if(temp.getVariable().equals((String )o)) {
							print=(String)temp.getData();
							break;
						}
					}
					
				}
			}
			
			
		}

		System.out.println(print);

	}
}
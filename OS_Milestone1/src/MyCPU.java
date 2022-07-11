public class MyCPU {
	public static void main(String[] args) throws Exception {
		Program p1=new Program("Program_1.txt",0);
		Program p2=new Program("Program_2.txt",1);
		Program p3=new Program("Program_3.txt",4);
		Mutex m1=new Mutex("userInput");
		Mutex m2=new Mutex("userOutput");
		Mutex m3=new Mutex("file");
		p1.sched.Mutex.add(m1);
		p1.sched.Mutex.add(m2);
		p1.sched.Mutex.add(m3);
		scheduler s=p1.sched;
		s.start();
	}
}

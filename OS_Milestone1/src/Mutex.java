import java.util.LinkedList;
import java.util.Queue;

public class Mutex {
private int value=1; 
Queue<Integer> Blocked=new LinkedList<Integer>();
int Ownerid;
String resource;
public Mutex(String resource) {
	this.resource=resource;
}
public int getOwnerid() {
	return Ownerid;
}
public void setOwnerid(int ownerid) {
	Ownerid = ownerid;
}
public Queue<Integer> getBlocked() {
	return Blocked;
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}




public void semWait(int pid,scheduler sched) {
	if (this.getValue() == 1) {
		this.setValue(0);
		this.setOwnerid(pid);
	}else {
		this.getBlocked().add(pid);
		
		if(pid==(Integer)sched.getMem().readFrommem(0))
			sched.getMem().writeTomem(state.BLOCKED+"", 1);
		else
		sched.getMem().writeTomem(state.BLOCKED+"", 21);
	
		sched.GeneralBlocked.add(pid);
		System.out.println("Ready Queue: "+sched.Ready);
		System.out.println("General Blocked Queue:"+sched.GeneralBlocked);
		sched.currentlyRunning=0;
		

	}

}
public void semSignal(int pid,scheduler sched) {
	if (pid==this.getOwnerid()) {
		if(this.getBlocked().isEmpty()) {
			this.setValue(1);
		}else {
			int p2=this.getBlocked().remove();
			if(p2==(Integer)sched.getMem().getMemory()[0])
			sched.getMem().writeTomem(state.READY+"", 1);
		else
		if(p2==(Integer)sched.getMem().getMemory()[20])
		sched.getMem().writeTomem(state.READY+"", 21);
		else	
		sched.setDequed(true);
			
			sched.GeneralBlocked.remove(p2);
			sched.getReady().add(p2);
			this.setOwnerid(p2);
		}
	}

}

}
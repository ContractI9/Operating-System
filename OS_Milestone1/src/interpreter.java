import java.util.ArrayList;
import java.util.Stack;

public class interpreter {

    public interpreter(scheduler sched) {
        this.sched = sched;
    }
    private scheduler sched;

    public scheduler getSched() {
        return this.sched;
    }

    public void setSched(scheduler sched) {
        this.sched = sched;
    }
    

public  void execute(int pid,Memory mem) {//pid
    //String[] content = processs.instructions.get(0).split(" ");
    int pcounter=0;
    if(pid==(int)Integer.parseInt(""+mem.getMemory()[0]))
    pcounter=(int)Integer.parseInt(""+mem.getMemory()[2]);
    else
    pcounter=(int)Integer.parseInt(""+mem.getMemory()[22]);
    System.out.println("PC: "+pcounter);
    String[] content= ((String)mem.getMemory()[pcounter]).split(" ");

    ArrayList<String> variables = new ArrayList<String>();
    boolean unfinishedInstruction=false;
    SystemCall call=new SystemCall(pid);
    call.setMem(mem);
    Stack order=new Stack();
    for(String s:content) {
        order.push(s);
    }
    while(!order.isEmpty()) {
        String var=(String)order.pop();
        switch(var) {
        case"print":call.print(variables.get(0));
        variables.remove(variables.get(0));
        break;
        case"assign":
        if (variables.size()>2){
            String entireInput="";
        while(variables.size()!=1){
            entireInput=variables.remove(0)+" "+entireInput;
        }
        variables.add(0, entireInput.trim());
        }
        call.assign(variables.get(1),variables.get(0).replace("$", " "));
        variables.remove(variables.get(0));
        variables.remove(variables.get(0));
        
        break;
                    case"writeFile":call.writeFile(variables.get(1),variables.get(0));
                    variables.remove(variables.get(0));
                    variables.remove(variables.get(0));
                        break;
        case"readFile"://order.push(call.readFile(variables.get(0)));
        order.push(call.readFile(variables.get(0)).replace(" ", "$"));
        if(!order.isEmpty())
        {
        unfinishedInstruction=true;
        }

        variables.remove(variables.get(0));
        break;
        case"printFromTo":call.printFromTo(variables.get(1),variables.get(0));
        variables.remove(variables.get(0));
        variables.remove(variables.get(0));
        break;
        
        case"input":
            String inputString=call.input();
            order.push(inputString);
            unfinishedInstruction=true;
            
            break;
                    
            case"semWait":
                        Mutex m = null;
                        for(Mutex x: this.sched.Mutex) {
                            if(x.resource.equals(variables.get(0))) {
                                m=x;
                            
                                break;
                            }
                        }
                        
                        
                        m.semWait(pid, sched);
                        
                    variables.remove(variables.get(0));	
                    break;
                    case"semSignal":
                        Mutex r = null;
                        for(Mutex x:this.sched.Mutex) {
                            if(x.resource.equals(variables.get(0))) {
                                r=x;
                                break;
                            }
                        }
                        
                        
                        
                        r.semSignal(pid, sched);
                    variables.remove(variables.get(0));	
                    break;
        default:variables.add(var);
        break;
        }
        if(	unfinishedInstruction)
        break;
    }
    String instr="";
    if(unfinishedInstruction){
        while(!order.isEmpty()){
            String a=(String) order.pop();
            if(!order.isEmpty())
            instr=" "+a+instr;
            else
                instr=a+instr;
        }
        mem.writeTomem(instr, pcounter);
    }else{
        pcounter++;
        if(pcounter<20){
        mem.writeTomem(pcounter+"", 2);
        }else{
            mem.writeTomem(pcounter+"", 22);
        }
    }
    


      if((mem.readFrommem(pcounter).equals(""))||(mem.readFrommem(pcounter)==null)||(mem.readFrommem(pcounter).equals("null"))){
        if(pid==(int)Integer.parseInt(""+mem.getMemory()[0]))
        mem.writeTomem(state.FINISHED+"", 1);
        else
        mem.writeTomem(state.FINISHED+"", 21);
      }
}}
public class Memory {

  
    Object [] memory;
   
    public Memory() {
        this.memory=new Object[40];
    }
   
    public void writeTomem(Object object, int index){
        this.getMemory()[index]=object;
    }
    public Object readFrommem(int index){
        return this.getMemory()[index];
    }
   
    public Object[] getMemory() {
        return this.memory;
    }

    public void setMemory(Object[] memory) {
        this.memory = memory;
    }
    public String toString(){
        String s="[";
        for(int i=0;i<40;i++) {
        	if(i==9||i==19||i==29)
        		s+="\n";
            if((this.memory[i]==null||this.memory[i].equals("null")||this.memory[i].equals("")) && i!=39) {
                s+="null ,";
            }
            else    if((this.memory[i]==null||this.memory[i].equals("null")||this.memory[i].equals("")) && i==39)
                s+="null]";
            else
            if(i==0||i==20) {
                s+="ProccessID: "+this.memory[i]+" ,"; 
            }else if(i==1||i==21) {
                s+="State: "+this.memory[i]+" ,";
            }else if(i==2||i==22) {
                s+="PC: "+this.memory[i]+" ,";
            
            }else if(i==3||i==23) {
                s+="Minboundry: "+this.memory[i]+" ,";
            }else if(i==4||i==24) {
                s+="Maxboundry: "+this.memory[i]+" ,";
            }else if(i==5||i==6||i==7||i==25||i==26||i==27) {
                s+=((Variable)this.memory[i]).getVariable()+" : "+((Variable)this.memory[i]).getData()+" ,";;           
            }else  if(i==39){
                s+="]";
            }
            else {
                s+="Instruction: "+this.memory[i]+" ,";
            }

    }
        return s;
    }
}
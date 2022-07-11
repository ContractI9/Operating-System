public class Variable {

    private String variable;
    private Object data;

    public Variable(String variable, Object data) {
        this.variable = variable;
        this.data = data;
    }

    public String getVariable() {
        return this.variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public String toString(){
        return this.getVariable()+" : " +this.getData();
    }
}

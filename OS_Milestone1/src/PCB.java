public class PCB {
    private int pid;
    private state state;
    private int pc;
    private int minBoundry;
    private int maxBoundry;



    public PCB(int pid, state state, int pc, int minBoundry, int maxBoundry) {
        this.pid = pid;
        this.state = state;
        this.pc = pc;
        this.minBoundry = minBoundry;
        this.maxBoundry = maxBoundry;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public state getState() {
        return this.state;
    }

    public void setState(state state) {
        this.state = state;
    }

    public int getPc() {
        return this.pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getMinBoundry() {
        return this.minBoundry;
    }

    public void setMinBoundry(int minBoundry) {
        this.minBoundry = minBoundry;
    }

    public int getMaxBoundry() {
        return this.maxBoundry;
    }

    public void setMaxBoundry(int maxBoundry) {
        this.maxBoundry = maxBoundry;
    }
}

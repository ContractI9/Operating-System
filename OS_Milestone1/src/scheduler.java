import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class scheduler {
	static ArrayList<Program> New = new ArrayList<Program>();
	static Queue<Integer> Ready = new LinkedList<Integer>();
	static Queue<Integer> GeneralBlocked = new LinkedList<Integer>();
	private boolean dequed;
	static int timeslice = 2;
	static int counter = 0;
	static int time = 0;
	private static int proccessId = 1;
	private int firstproccess;
	static int currentlyRunning;
	interpreter inter = new interpreter(this);
	public ArrayList<Mutex> Mutex = new ArrayList<Mutex>();
	private static Memory mem = new Memory();

	public int getFirstproccess() {
		return this.firstproccess;
	}

	public void setFirstproccess(int firstproccess) {
		this.firstproccess = firstproccess;
	}

	public boolean isDequed() {
		return this.dequed;
	}

	public boolean getDequed() {
		return this.dequed;
	}

	public void setDequed(boolean dequed) {
		this.dequed = dequed;
	}

	public ArrayList<Mutex> getMutex() {
		return this.Mutex;
	}

	public void setMutex(ArrayList<Mutex> Mutex) {
		this.Mutex = Mutex;
	}

	public Memory getMem() {
		return this.mem;
	}

	public void setMem(Memory mem) {
		this.mem = mem;
	}

	public scheduler() {

	}

	public static ArrayList<Program> getNew() {
		return New;
	}

	public static Queue<Integer> getGeneralBlocked() {
		return GeneralBlocked;
	}

	public static int getCurrentlyRunning() {
		return currentlyRunning;
	}

	public static void setNew(ArrayList<Program> new1) {
		New = new1;
	}

	public static void setReady(Queue<Integer> ready) {
		Ready = ready;
	}

	public static void setGeneralBlocked(Queue<Integer> generalBlocked) {
		GeneralBlocked = generalBlocked;
	}

	public static void setTimeslice(int timeslice) {
		scheduler.timeslice = timeslice;
	}

	public static void setCurrentlyRunning(int currentlyRunning) {
		scheduler.currentlyRunning = currentlyRunning;
	}

	public static void setTimeslice() {

	}

	public static Queue<Integer> getReady() {
		return Ready;
	}

	public void start() {

		Program p;
		while (true) {
			System.out.println("Time  " + time);
			int numz = 0;
			if (!(this.getMem().getMemory()[0] == null || this.getMem().getMemory()[20] == null))
				numz = exists(currentlyRunning);
			if (numz != 0 && this.New.isEmpty() && this.Ready.isEmpty() && this.GeneralBlocked.isEmpty()
					&& this.getMem().readFrommem(numz + 1).equals("FINISHED")) {
				System.out.println("All Processes Finished Execution!");
				return;
			}

			for (int i = 0; i < this.New.size(); i++) {
				p = this.New.get(i);

				SystemCall call = new SystemCall(proccessId);
				if (p.arriveTime == time) {
					System.out.println("Process " + proccessId + " has arrived at time " + time);
					New.remove(p);
					if (mem.getMemory()[0] == null) {
						int num = p.instructions.size();
						firstproccess = proccessId;
						PCB p1 = new PCB(proccessId++, state.READY, 8, 0, 19);
						Ready.add(p1.getPid());
						mem.writeTomem(p1.getPid(), 0);
						mem.writeTomem(p1.getState() + "", 1);
						mem.writeTomem(p1.getPc(), 2);
						mem.writeTomem(p1.getMinBoundry(), 3);
						mem.writeTomem(p1.getMaxBoundry(), 4);
						mem.writeTomem("null", 5);
						mem.writeTomem("null", 6);
						mem.writeTomem("null", 7);
						for (int z = 0; z < num; z++) {
							mem.writeTomem(p.instructions.get(z), z + 8);
						}
						int s = num + 8;
						for (int j = s; s < 20; s++) {
							mem.writeTomem("", j);
						}
					} else {
						if (mem.getMemory()[20] == null) {
							int num = p.instructions.size();
							PCB p1 = new PCB(proccessId++, state.READY, 28, 20, 39);
							Ready.add(p1.getPid());
							mem.writeTomem(p1.getPid(), 20);
							mem.writeTomem(p1.getState() + "", 21);
							mem.writeTomem(p1.getPc(), 22);
							mem.writeTomem(p1.getMinBoundry(), 23);
							mem.writeTomem(p1.getMaxBoundry(), 24);
							mem.writeTomem("null", 25);
							mem.writeTomem("null", 26);
							mem.writeTomem("null", 27);
							for (int z = 0; z < num; z++) {

								mem.writeTomem(p.instructions.get(z), z + 28);
							}
							int s = num + 28;
							for (int j = s; s < 40; s++) {
								mem.writeTomem("null", j);
							}
						} else {
			
							int idx = (Integer) mem.getMemory()[0];
							System.out.println("Swaping here");
							System.out.println("------------------------------");
							System.out.println("Process "+idx+" sent to disk" );
							System.out.println("------------------------------");
							int num = p.instructions.size();
							if (firstproccess == idx) {
								firstproccess = (Integer) mem.getMemory()[20];
								String pfile = "";
								for (int l = 0; l < 20; l++) {
									if (!(l > 8 & mem.getMemory()[l] == null))
										pfile += mem.getMemory()[l] + "\n";

								}
								call.writeFile("Disk.txt", pfile);

								for (int l = 0; l < 20; l++)
									mem.writeTomem("null", l);

								PCB p1 = new PCB(proccessId++, state.READY, 8, 0, 19);
								Ready.add(p1.getPid());
								mem.writeTomem(p1.getPid(), 0);
								mem.writeTomem(p1.getState() + "", 1);
								mem.writeTomem(p1.getPc(), 2);
								mem.writeTomem(p1.getMinBoundry(), 3);
								mem.writeTomem(p1.getMaxBoundry(), 4);
								mem.writeTomem("null", 5);
								mem.writeTomem("null", 6);
								mem.writeTomem("null", 7);
								for (int z = 0; z < num; z++) {
									mem.writeTomem(p.instructions.get(z), z + 8);
								}
								int s = num + 8;
								for (int j = s; s < 20; s++) {
									mem.writeTomem("null", j);
								}
							}
						}
					}
				}
			}

			if (currentlyRunning == 0) {
				Object trial = this.Ready.poll();
				if (trial != null) {
					currentlyRunning = (Integer) trial;
					int ex = swapIfne(currentlyRunning);
					this.mem.writeTomem(state.RUNNING + "", ex + 1);
					counter = 0;
					System.out.println("Ready Queue: " + this.Ready);
					System.out.println("General Blocked Queue:" + this.GeneralBlocked);
				} else {
					System.out.println("All Processes Finished Execution!");
					return;

				}

			}
			int ex = swapIfne(currentlyRunning);

			if (this.counter == this.timeslice | ((String) this.getMem().readFrommem(ex + 1)).equals("FINISHED")) {
				if (!((String) this.getMem().readFrommem(ex + 1)).equals("FINISHED")) {
					this.Ready.add(currentlyRunning);
					this.mem.writeTomem(state.READY + "", ex + 1);
				}
				Object trial = this.Ready.poll();
				if (trial != null) {
					currentlyRunning = (Integer) trial;
					ex = swapIfne(currentlyRunning);
					this.mem.writeTomem(state.RUNNING + "", ex + 1);
					counter = 0;
					System.out.println("Ready Queue: " + this.Ready);
					System.out.println("General Blocked Queue:" + this.GeneralBlocked);
				} else {
					System.out.println("All Processes Finished Execution!");
					return;
				}

			}



			System.out.println("Process Currently Running: " + this.currentlyRunning);
			int pc1 = (int) Integer.parseInt(this.getMem().readFrommem(ex + 2) + "");
			System.out.println("Instruction Currently Running: " + this.getMem().readFrommem(pc1) + " for process "+ currentlyRunning);
			inter.execute(this.currentlyRunning, this.mem);
			time++;
			counter++;

			if (isDequed()) {
				this.setDequed(false);

				if (ex == 20) {
					swap((Integer) this.getMem().readFrommem(0));
					this.mem.writeTomem(state.READY + "", 1);

				} else {
					swap((Integer) this.getMem().readFrommem(20));
					this.mem.writeTomem(state.READY + "", 21);

				}
				firstproccess = (Integer) this.getMem().readFrommem(ex);
			}


			if (currentlyRunning != 0 && ((String) this.getMem().readFrommem(ex + 1)).equals("FINISHED")) {
				System.out.println("Process " + currentlyRunning + " Finished Execution");
				System.out.println("Ready Queue: " + this.Ready);
				System.out.println("General Blocked Queue:" + this.GeneralBlocked);
			}
			System.out.println("------------------------------------------------------------------------");
			System.out.println("Memory: " + this.mem);
			System.out.println("------------------------------------------------------------------------");
			SystemCall call = new SystemCall(10);
			String disk[]=call.readFile("Disk.txt").split("\\r?\\n");
			System.out.print("Disk: [ ");
			for(int i=0;i<disk.length;i++) {
				if(i!=disk.length-1)
				System.out.print(disk[i]+", ");
				else
					System.out.print(disk[i]+" ");
				if(i%10==0&&i!=0)
					System.out.println();
				}
			System.out.println("]");
			System.out.println("__________________________________________________________________________________________________");


		}
	}

	public void swap(int proccessId) {
		
		SystemCall call = new SystemCall(proccessId);
		call.setMem(mem);
		String temp = call.readFile("Disk.txt");
		boolean firstPartition = true;
		if (proccessId != (Integer) mem.getMemory()[0])
			firstPartition = false;

		if (firstPartition) {
			deparse(0);
			load(temp, 0);
			System.out.println("Swaping here");
			System.out.println("----------------------------------------------------");
			System.out.println("Process "+proccessId+" sent to disk and Process "+temp.charAt(0)+" sent to memory" );
			System.out.println("----------------------------------------------------");
		} else {
			deparse(20);
			load(temp, 20);
			System.out.println("Swaping here");
			System.out.println("----------------------------------------------------");
			System.out.println("Process "+proccessId+" sent to disk and Process "+temp.charAt(0)+" sent to memory" );
			System.out.println("----------------------------------------------------");
		}
		
	}

	public void deparse(int idx) {
		SystemCall call = new SystemCall((Integer) mem.getMemory()[idx]);
		call.setMem(mem);
		String pfile = "";
		for (int l = 0; l < 20; l++) {
			pfile += mem.getMemory()[l + idx] + "\n";

		}
		call.writeFile("Disk.txt", pfile);
	}

	public void load(String unparsed, int idx) {
		String[] partition = unparsed.split("\\r?\\n");
		for (int i = 0; i < 20; i++) {
			if (i < partition.length) {
				if (i < 5 & i != 1) {
					mem.writeTomem((int) (Integer.parseInt(partition[i])), i + idx);
				} else if (i > 4 && i < 8) {
					if (partition[i].equals("null") || partition[i] == null)
						mem.writeTomem("null", i + idx);
					else {
						String[] l = partition[i].split(" : ");
						Variable v = null;
						String dataType;
						String y = l[1];
						if (y.matches("\\d+")) {
							dataType = "Integer";
						} else {
							dataType = "String";
						}
						if (dataType.equals("Integer")) {
							int x = (int) Integer.parseInt(y);
							v = new Variable(l[0], x);
							mem.writeTomem(v, i + idx);
						} else {
							v = new Variable(l[0], y);

							mem.writeTomem(v, i + idx);
						}
					}
				} else
					mem.writeTomem(partition[i], i + idx);
			} else
				mem.writeTomem("null", i + idx);

		}
		int pc = (int) Integer.parseInt(partition[2]);
		if(pc>20)
			pc=pc-20;
		
		mem.writeTomem(pc + idx, idx + 2);
		mem.writeTomem(idx, idx + 3);
		mem.writeTomem(idx + 19, idx + 4);
	
	}

	public int swapIfne(int pid) {
		int ex = exists(pid);
		if (ex == -1) {
			if (firstproccess == (int) Integer.parseInt("" + mem.readFrommem(0))) {
				swap(firstproccess);
				firstproccess = (int) Integer.parseInt("" + mem.readFrommem(20));
				return 0;
			} else {
				swap(firstproccess);
				firstproccess = (int) Integer.parseInt("" + mem.readFrommem(0));
				return 20;
			}

		} else {

			return ex;
		}
	}

	public int exists(int pid) {
		if ((int) Integer.parseInt("" + mem.readFrommem(0)) == pid)
			return 0;
		else if ((int) Integer.parseInt("" + mem.readFrommem(20)) == pid)
			return 20;
		else
			return -1;

	}


}

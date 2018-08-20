package model;

public class RunStatus {
	
	private String status;
	private long time_taken,time_limit,memory_taken,memory_used;
	private String output;
	
	public RunStatus() {}
	public RunStatus(String status, long time_taken, long time_limit, String output) {
		this.status = status;
		this.time_taken = time_taken;
		this.time_limit = time_limit;
		this.output = output;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTime_taken() {
		return time_taken;
	}
	public void setTime_taken(long time_taken) {
		this.time_taken = time_taken;
	}
	public long getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(long time_limit) {
		this.time_limit = time_limit;
	}
	public long getMemory_taken() {
		return memory_taken;
	}
	public void setMemory_taken(long memory_taken) {
		this.memory_taken = memory_taken;
	}
	public long getMemory_used() {
		return memory_used;
	}
	public void setMemory_used(long memory_used) {
		this.memory_used = memory_used;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	
}

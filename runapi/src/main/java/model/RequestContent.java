package model;

public class RequestContent {
	
	private String id;
	private String lan;
	private long time_limit,memory_limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public long getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(long time_limit) {
		this.time_limit = time_limit;
	}
	public long getMemory_limit() {
		return memory_limit;
	}
	public void setMemory_limit(long memory_limit) {
		this.memory_limit = memory_limit;
	}
	
	
}

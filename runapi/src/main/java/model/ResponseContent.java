package model;

public class ResponseContent {

	private String accessId;
	private String errors;
	private String messeges;
	private String compile_status;
	private RunStatus run_status;
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getMesseges() {
		return messeges;
	}
	public void setMesseges(String messeges) {
		this.messeges = messeges;
	}
	public String getCompile_status() {
		return compile_status;
	}
	public void setCompile_status(String compile_status) {
		this.compile_status = compile_status;
	}
	public RunStatus getRun_status() {
		return run_status;
	}
	public void setRun_status(RunStatus run_status) {
		this.run_status = run_status;
	}	
}

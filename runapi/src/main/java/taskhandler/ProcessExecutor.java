package taskhandler;

import com.api.runapi.RunapiApplication;

import model.RunStatus;

public abstract class ProcessExecutor {
 
    protected String codeId;
    protected ProcessExecutor nextExecutor;
    protected String lan;
    protected String path=RunapiApplication.path;
    public ProcessExecutor(String codeId, ProcessExecutor nextExecutor, String lan) {
        this.codeId = codeId;
        this.nextExecutor = nextExecutor;
        this.lan = lan;
    }

    
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }
    
    
    public void setNextExecutor(ProcessExecutor nextExecutor) {
        this.nextExecutor = nextExecutor;
    }
    public String doCompile(String ulan)
    {
        String res="Language not supported";
    	if(ulan.equals(lan))
        {
            res=compile();

        }
        else if(nextExecutor != null)
        {
            res=nextExecutor.doCompile(ulan);
        }
        
        return res;
    }
    public RunStatus doExecute(String ulan,long tlimit,long mlimit)
    {
    	RunStatus rs=new RunStatus();
    	if(ulan.equals(lan))
        {
            rs=execute(tlimit,mlimit);
        }
        else if(nextExecutor != null)
        {
            rs=nextExecutor.doExecute(ulan,tlimit,mlimit);
        }
    	return rs;
    }
    abstract public String compile();
    abstract public RunStatus execute(long tlimit,long mlimit);
}

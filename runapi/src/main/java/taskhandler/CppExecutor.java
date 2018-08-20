package taskhandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import model.RunStatus;

public class CppExecutor extends ProcessExecutor {
    
    public CppExecutor(String codeId, ProcessExecutor nextExecutor, String lan) {
        super(codeId, nextExecutor, lan);
    }

    
    @Override
    public String compile() {
        
        String done="Error !";
        try {
        	String code=path + "upload/" + codeId + "." + lan;
        	String exfile=path+"outputs/"+codeId+".out";
            String cmd="g++ "+ code + " -o " + exfile;
            ProcessBuilder pb = new ProcessBuilder("bash" ,"-c",cmd);
            
            File error = new File(path + "outputs/" + codeId + "_error.txt");
            
            pb.redirectError(error);
            Process com=pb.start();
            com.waitFor();
            if(error.length() != 0)
            done=new String(Files.readAllBytes(Paths.get(path + "outputs/" + codeId + "_error.txt")));
            else 
            done="OK";
            error.delete();
            
        } catch (IOException ex) {
           System.out.println("cpp IO ERROR......"+ex);
        } catch (InterruptedException ex) {
           System.out.println("cpp TH ERROR......"+ex);
        }
    
        return done;
    }

    @Override
    public RunStatus execute(long tlimit,long mlimit) {
        
        RunStatus res=new RunStatus();
        String exfile=path+"outputs/"+codeId+".out";
        String infile=path+"upload/"+codeId+"_input.txt";
        ProcessBuilder run = new ProcessBuilder("bash","-c",exfile);
        
        File error = new File(path + "outputs/" + codeId + "_error.txt");
        File output = new File(path + "outputs/" + codeId + "_output.txt");
                
        try {
        	File commands = new File(infile);
        	run.redirectInput(commands);
        	run.redirectOutput(output);
        	run.redirectError(error);
        	
        	long stime=System.currentTimeMillis();
        	Process time=run.start();
        	boolean finished = time.waitFor(tlimit, TimeUnit.MILLISECONDS);
        	long etime=System.currentTimeMillis();
        	long runtime=(etime - stime);
        	if(runtime > tlimit)
        		runtime=tlimit + 1;
        	if (!finished ) 
        	{
        		time.destroy();
        		time.waitFor();
                res=new RunStatus("TLE",runtime,tlimit,null);
                    //   continue;
            }
        	else if(time.exitValue() != 0)
        	{
        		res=new RunStatus("RTE",runtime,tlimit,null);
        	}
        	else
        	{
        		String str=new String(Files.readAllBytes(Paths.get(path + "outputs/" + codeId + "_output.txt")));
        		res=new RunStatus("DONE",runtime,tlimit,str);
            } 
            } catch (IOException ex) {
               
            } catch (InterruptedException ex) {
               
            }
        
          error.delete(); 
          return res;
        }
}

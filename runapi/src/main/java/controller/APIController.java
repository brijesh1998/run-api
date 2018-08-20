package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.runapi.RunapiApplication;

import model.RequestContent;
import model.ResponseContent;
import model.RunStatus;
import taskhandler.CExecutor;
import taskhandler.CppExecutor;
import taskhandler.ProcessExecutor;

@RestController
@RequestMapping("api")
public class APIController {
	
	
	@PostMapping("compile")
	public ResponseContent compileCode(@RequestParam("file")MultipartFile file,RequestContent req)
	{
		ResponseContent res=new ResponseContent();
		
		String id=UUID.randomUUID().toString();
		saveFile(file,id+"."+req.getLan());
		
		ProcessExecutor clan=new CExecutor(id,null,"c");
		ProcessExecutor cpp=new CppExecutor(id,clan,"cpp");
		
		String done=cpp.doCompile(req.getLan());
		if(!done.equals("OK"))
		{
			res.setErrors(done);
			res.setCompile_status("Compilation Error !");
		}
		else
		{
			res.setCompile_status("OK");
			res.setAccessId(id);
		}
		return res;
	}
	
	@PostMapping("run")
	public ResponseContent runCode(@RequestParam("file")MultipartFile file,RequestContent req)
	{
		ResponseContent res=new ResponseContent();
		String id=req.getId();
		saveFile(file,id+"_input.txt");
		
		ProcessExecutor clan=new CExecutor(id,null,"c");
		ProcessExecutor cpp=new CppExecutor(id,clan,"cpp");
		
		RunStatus rs=cpp.doExecute(req.getLan(), req.getTime_limit(), req.getMemory_limit());
		res.setRun_status(rs);
		return res;
	}
	
	private void saveFile(MultipartFile file,String fname)
	{
		try 
		{
			byte[] bytes=file.getBytes();
			Path p=(Path)Paths.get(RunapiApplication.path+"upload/"+fname);
			Files.write(p, bytes);
		}
		catch (IOException e) 
		{}
	}
	@GetMapping("check")
	public String check() {
		return "API is running.... :)";
	}

}

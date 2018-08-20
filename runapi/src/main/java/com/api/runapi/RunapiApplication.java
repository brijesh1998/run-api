package com.api.runapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller"})
public class RunapiApplication {

	public static final String path="/home/brizz/eclipse-workspace/runapi/store/";
	public static void main(String[] args) {
		SpringApplication.run(RunapiApplication.class, args);
	}
}

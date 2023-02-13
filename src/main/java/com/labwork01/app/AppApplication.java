package com.labwork01.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@GetMapping("/numconc")
	public String numberConcat(@RequestParam(value="n1", defaultValue = "0") Integer num_1, @RequestParam(value="n2", defaultValue = "0") Integer num_2){
		return num_1.toString() + num_2.toString();
	}

}

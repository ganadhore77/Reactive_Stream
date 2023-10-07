package com.reactive.controller;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.service.MyService;

@RestController
public class MyController {

	@GetMapping("/get")
	public ResponseEntity<String> get(){
		 List<String> items = List.of("JAVA", "SPRING-BOOT", "MICROSERVICES", "RABBIT-MQ", "KAFKA", "MYSQL", "MONGODB", "ANGULAR", "DOCEKR", "JENKINS");
	      SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
	      MyService<Object> myService = new MyService<>();
	      publisher.subscribe(myService);
	      items.forEach(s -> {
	         try {
	            Thread.sleep(1000);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	         publisher.submit(s);
	      });
	      publisher.close();
		return new ResponseEntity<String>("DONE",HttpStatus.OK);
	}
	
}

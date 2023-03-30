package com.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private WebClient.Builder webClient;
	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseData> getAllStudentData(@PathVariable("studentId") Long studentId){
		
		
		ResponseData response=new ResponseData();
		
		
		Student s1=new Student();
		s1.getStudentId(1L);
		s1.setStudentName("RaseethAnwer");
		s1.setAddress("VILUPPURAM-606 107");
		
		s1.setCollegeId(1L);

		
		response.setStudent(s1);
		
		
		Long collegeId=s1.getCollegeId();
		
		
		

		College c1=webClient.build()//webclient
				.get() 
				.uri("http://localhost:9090/college/"+collegeId)
				.retrieve()
				.bodyToMono(College.class)
				.block();//
			response.setCollege(c1);
		return new  ResponseEntity<ResponseData>(response,HttpStatus.OK);
		
		
	}

}

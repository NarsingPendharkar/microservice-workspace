package org.school.studentms.controller;

import java.util.List;

import org.school.studentms.config.StudentConfig;
import org.school.studentms.dto.AddressDTO;
import org.school.studentms.dto.StudentDTO;
import org.school.studentms.feignClient.AddressFeignClient;
import org.school.studentms.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	


    private StudentService service;
    private StudentConfig config;
    private WebClient webClient;
    private AddressFeignClient addFeignClient;
    

	public StudentController(StudentService service, StudentConfig config, WebClient webClient,AddressFeignClient addFeignClient) {
		super();
		this.service = service;
		this.config = config;
		this.webClient = webClient;
		this.addFeignClient=addFeignClient;
	}

	@PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(service.createStudent(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(service.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
    
	/* call address api without using eureka service */ 
    @GetMapping("/stdaddusingrest/{id}")
    public ResponseEntity<AddressDTO> getStdAddress(@PathVariable long id){
    	 String url = "http://localhost:8081/api/addresses" ;
    	AddressDTO forObject = config.getTemplate().getForObject(url+"/"+id, AddressDTO.class);
    	return ResponseEntity.ok(forObject);
    	
    }
    
    /*call adress api using webclient*/
    
    @GetMapping("/stdaddusingwebclient/{id}")
    public ResponseEntity<AddressDTO> getStdAddressusingWebclient(@PathVariable long id){
    	Mono<AddressDTO> bodyToMono = webClient.get().uri("/"+id).retrieve().bodyToMono(AddressDTO.class);
		return ResponseEntity.ok(bodyToMono.block());
    	 
    }
    
    // call address api using feign client
    @GetMapping("/stdaddusingfeigncleint/{id}")
    public ResponseEntity<AddressDTO> getStdAddressusingFeign(@PathVariable long id){
     AddressDTO byId = addFeignClient.getById(id);
		return ResponseEntity.ok(byId);
    	 
    }
    
    
}

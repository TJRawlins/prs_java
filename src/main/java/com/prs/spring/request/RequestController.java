package com.prs.spring.request;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestController {
	
	@Autowired
	private RequestRepository requestRepo;

	// GET REVIEWS
	@GetMapping("/reviews/{userId}")
	public ResponseEntity<Iterable<Request>> getReviews(@PathVariable int userId) {
		Iterable<Request> requests = requestRepo.findRequestByStatusAndUserIdNot("REVIEW", userId);
		return new ResponseEntity<Iterable<Request>>(requests, HttpStatus.OK);
	}
	
	// GET ALL
	@GetMapping
	public ResponseEntity<Iterable<Request>> getRequests() {
		Iterable<Request> request = requestRepo.findAll();
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	// GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<Request> getRequest(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// JOIN USERS, REQUESTLINES AND PRODUCSTS - JOINED IN REQUEST CLASS @MANYTOONE
		Optional<Request> request = requestRepo.findById(id);
		if(request.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Request>(request.get(), HttpStatus.OK);
	}
	
	// POST 
	@PostMapping
	public ResponseEntity<Request> postRequest(@RequestBody Request request) {
		if(request.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		requestRepo.save(request);
		return new ResponseEntity<Request>(request, HttpStatus.CREATED);
	}
	
	// PUT
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putRequest(@RequestBody Request request) {
		if(request.getId() <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		requestRepo.save(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// PUT REVIEW
	@SuppressWarnings("rawtypes")
	@PutMapping("/review/{id}")
	public ResponseEntity review(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Request request = requestRepo.findRequestById(id);
		if(request.getTotal() <= 50 ) {
			request.setStatus("APPROVED");
		} else {			
			request.setStatus("REVIEW");
		}
		requestRepo.save(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// PUT APPROVE
	@SuppressWarnings("rawtypes")
	@PutMapping("/approve/{id}")
	public ResponseEntity approve(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Request request = requestRepo.findRequestById(id);
		request.setStatus("APPROVED");
		requestRepo.save(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// PUT REJECT
	@SuppressWarnings("rawtypes")
	@PutMapping("/reject/{id}")
	public ResponseEntity reject(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Request request = requestRepo.findRequestById(id);
		request.setStatus("REJECTED");
		requestRepo.save(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// DELETE
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeleteRequest(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		requestRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

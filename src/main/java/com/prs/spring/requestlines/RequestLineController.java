package com.prs.spring.requestlines;

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

import com.prs.spring.request.Request;
import com.prs.spring.request.RequestRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestLineController {
	
	@Autowired
	private RequestLineRepository rlRepo;
	
	@Autowired
	private RequestRepository reqRepo;
	
	// RECALCULATE TOTAL
	private void recalculateRequestTotal(int requestId) {
		double total = 0;
		Request request = reqRepo.findRequestById(requestId);
		Iterable<RequestLine> requestlines = rlRepo.findAllRequestlineByRequestId(requestId);
		// loop through requestlines
		for (RequestLine rl : requestlines ) {
			total += rl.getQuantity() * rl.getProduct().getPrice();
		}
		/*
		 * CANNOT UPDATE TOTAL USING FOREACH
		requestlines.forEach(rl -> {
			// get requestLine.Quantity
			// get product.Price
			// Total = requestLine.Quantity * product.Price
			total += rl.getQuantity() * rl.getProduct().getPrice();
			});
		*/
		request.setTotal(total);
		reqRepo.save(request);
	}

	
	// GET ALL
	@GetMapping
	public ResponseEntity<Iterable<RequestLine>> getRequestLines() {
		Iterable<RequestLine> requestlines = rlRepo.findAll();
		return new ResponseEntity<>(requestlines, HttpStatus.OK);
	}
	
	// GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<RequestLine> getRequestLine(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<RequestLine> requestline = rlRepo.findById(id);
		if(requestline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RequestLine>(requestline.get(), HttpStatus.OK);
	}
	
	// POST
	@PostMapping
	public ResponseEntity<RequestLine> postRequestLine(@RequestBody RequestLine requestline) {
		if(requestline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rlRepo.save(requestline);
		recalculateRequestTotal(requestline.getRequest().getId());
		return new ResponseEntity<RequestLine>(requestline, HttpStatus.CREATED);
	}
	
	// PUT
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putRequestLine(@RequestBody RequestLine requestline) {
		if(requestline.getId() <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rlRepo.save(requestline);
		recalculateRequestTotal(requestline.getRequest().getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity DeleteRequestLine(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			int requestId = rlRepo.findById(id).get().getRequest().getId();
			rlRepo.deleteById(id);
			recalculateRequestTotal(requestId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

}

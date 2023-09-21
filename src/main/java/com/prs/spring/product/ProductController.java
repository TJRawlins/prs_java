package com.prs.spring.product;

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
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductRepository prodRepo;
	
	// GET ALL
	@GetMapping
	public ResponseEntity<Iterable<Product>> getProducts() {
		Iterable<Product> prod = prodRepo.findAll();
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	// GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Product> prod = prodRepo.findById(id);
		if(prod.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(prod.get(), HttpStatus.OK);
	}
	
	// POST
	@PostMapping
	public ResponseEntity<Product> postProduct(@RequestBody Product prod) {
		if(prod.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		prodRepo.save(prod);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}
	
	// PUT
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putProduct(@RequestBody Product prod) {
		if(prod.getId() <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		prodRepo.save(prod);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// DELETE
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity DeleteProduct(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			prodRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

}

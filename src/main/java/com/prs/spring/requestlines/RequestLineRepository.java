package com.prs.spring.requestlines;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.prs.spring.request.Request;

public interface RequestLineRepository extends CrudRepository<RequestLine, Integer>{
	Optional<RequestLine> findAccountById(int id);
    // Need request
	Request findRequestById(int id);
	// Need product
	
}

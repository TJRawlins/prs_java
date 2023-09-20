package com.prs.spring.requestlines;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RequestLineRepository extends CrudRepository<RequestLine, Integer>{
	Optional<RequestLine> findAccountById(int id);
}

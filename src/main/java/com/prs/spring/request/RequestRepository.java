package com.prs.spring.request;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer>{
	Optional<Request> findAccountById(int id);
}

package com.prs.spring.request;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer>{
	Request findRequestById(int id);
	Iterable<Request> findRequestByStatusAndUserIdNot(String status, int userId);

}

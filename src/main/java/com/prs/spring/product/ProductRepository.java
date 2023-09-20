package com.prs.spring.product;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	Optional<Product> findAccountById(int id);

}

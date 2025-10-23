package com.xco.spactshop.repository;

import com.xco.spactshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{'name': {$regex: ?0, $options: 'i'}, 'category': {$regex: ?1, $options: 'i'}}")
    Page<Product> findAll(String q, String category, Pageable pageable);
}

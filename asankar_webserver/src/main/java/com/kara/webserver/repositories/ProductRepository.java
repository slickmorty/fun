package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}

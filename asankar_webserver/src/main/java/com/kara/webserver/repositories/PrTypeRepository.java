package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.ProductType;

import org.springframework.data.repository.CrudRepository;

public interface PrTypeRepository extends CrudRepository<ProductType , Integer> {
}
